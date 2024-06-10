package com.vedruna.simbad.ListAdapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AnimalDTO;
import com.vedruna.simbad.API.Interface.AnimalService;
import com.vedruna.simbad.API.Tools.LocalDateDeserializer;
import com.vedruna.simbad.AnimalInfoEditRefugeFragment;
import com.vedruna.simbad.R;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListAnimalsRefugeAdapter extends ArrayAdapter<AnimalDTO> {
    private AlertDialog alertDialog;
    private Context mContext;
    private ArrayList<AnimalDTO> mDataArrayList; // Agrega una variable para almacenar la lista de datos
    private Gson gson;

    public ListAnimalsRefugeAdapter(Context context, ArrayList<AnimalDTO> dataArrayList) {
        super(context, R.layout.animal_list_refuge, dataArrayList);
        mContext = context;
        mDataArrayList = dataArrayList; // Guarda la lista de datos
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        AnimalDTO listAnimalDTO = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.animal_list_refuge, parent, false);
        }

        ImageView animalListPhotoRefuge = view.findViewById(R.id.animalListPhotoRefuge);
        TextView animalListNameRefuge = view.findViewById(R.id.animalListNameRefuge);
        TextView animalListProvinceRefuge = view.findViewById(R.id.animalListProvinceRefuge);
        ImageButton deleteAnimalPopupButton = view.findViewById(R.id.deleteAnimalPopupButton);
        ImageButton editAnimalPopupButton = view.findViewById(R.id.editAnimalPopupButton);

        if (listAnimalDTO != null) {
            Glide.with(getContext())
                    .load(Constans.BASE_URL + listAnimalDTO.getPhoto())
                    .placeholder(R.drawable.animal_image_placeholder)
                    .error(R.drawable.error_image)
                    .into(animalListPhotoRefuge);
            animalListNameRefuge.setText(listAnimalDTO.getName());
            animalListProvinceRefuge.setText(listAnimalDTO.getProvince());

            deleteAnimalPopupButton.setOnClickListener(v -> showDeleteAnimalPopup(listAnimalDTO));

            editAnimalPopupButton.setOnClickListener(v -> navigateToEditFragment(listAnimalDTO));
        }

        return view;
    }

    private void showDeleteAnimalPopup(AnimalDTO animalDTO) {
        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.delete_animal_popup, null);

        // Construir el Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(popupView);

        // Configurar los botones del popup
        Button cancelDeleteButton = popupView.findViewById(R.id.cancelDeleteAnimalButton);
        Button confirmDeleteButton = popupView.findViewById(R.id.confirmDeleteAnimalButton);

        // Obtener el ID del animalDTO
        long animalId = animalDTO.getId();

        // Configurar el clic del botón Cancelar
        cancelDeleteButton.setOnClickListener(v -> {
            // Cerrar el Dialog sin hacer nada
            alertDialog.dismiss();
        });

        // Configurar el clic del botón Borrar
        confirmDeleteButton.setOnClickListener(v -> {
            // Lógica para borrar el animal
            deleteAnimal(animalId);
            alertDialog.dismiss();
        });

        // Crear y mostrar el Dialog
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteAnimal(long animalId) {
        // Obtener el token de SharedPreferences
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(mContext, "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        AnimalService animalService = retrofit.create(AnimalService.class);

        // Imprimir la solicitud para depurar
        Log.d("Request", "Delete Animal Request: " + animalId);

        // Realizar la llamada a la API para eliminar el animal
        Call<Void> call = animalService.deleteAnimal(animalId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Animal eliminado exitosamente", Toast.LENGTH_SHORT).show();
                    // Actualizar la lista de animales después de eliminar el animal
                    mDataArrayList.removeIf(animalDTO -> animalDTO.getId() == animalId);
                    notifyDataSetChanged();
                    Log.d("Response", "Animal deleted successfully");
                } else {
                    Toast.makeText(mContext, "Error al eliminar el animal", Toast.LENGTH_SHORT).show();
                    try {
                        assert response.errorBody() != null;
                        Log.d("Response", "Error Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(mContext, "Error de red", Toast.LENGTH_SHORT).show();
                Log.e("Error", "Error: " + t.getMessage(), t);
            }
        });
    }

    private void navigateToEditFragment(AnimalDTO animalDTO) {
        // Crear un Bundle para pasar datos al nuevo fragmento
        Bundle bundle = new Bundle();
        bundle.putSerializable("animalDTO", animalDTO); // Puedes usar otro método según el tipo de datos de AnimalDTO
        bundle.putLong("animalId", animalDTO.getId()); // Aquí agregamos el ID del animal al Bundle

        // Crear una instancia del fragmento de edición del animal y establecer los argumentos
        AnimalInfoEditRefugeFragment fragment = new AnimalInfoEditRefugeFragment();
        fragment.setArguments(bundle);

        // Obtener el FragmentManager de la actividad
        FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();

        // Iniciar una transacción de fragmentos
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Reemplazar el fragmento actual con el nuevo fragmento
        transaction.replace(R.id.fragment_container, fragment);

        // Agregar la transacción a la pila de retroceso
        transaction.addToBackStack(null);

        // Confirmar la transacción
        transaction.commit();
    }
}
