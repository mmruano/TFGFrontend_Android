package com.vedruna.simbad;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AdoptionDTO;
import com.vedruna.simbad.API.Interface.AdoptionService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdoptionInfoRefugeFragment extends Fragment {

    private Button goBackAdoptionInfoRefugeButton;
    private TextView adoptionAnimalInfoNameRefuge;
    private TextView adoptionAnimalInfoSurnameRefuge;
    private TextView adoptionAnimalInfoProvinceRefuge;
    private TextView adoptionAnimalInfoEmailRefuge;
    private TextView adoptionAnimalInfoPhoneRefuge;
    private TextView adoptionAnimalInfoCodPostRefuge;
    private Button updateAdoptionButton;
    private Button cancelAdoptionButton;
    private TextView adoptionAnimalInfoStatusAdoption;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_adoption_info_refuge, container, false);

        goBackAdoptionInfoRefugeButton = rootView.findViewById(R.id.goBackAdoptionInfoRefugeButton);
        adoptionAnimalInfoNameRefuge = rootView.findViewById(R.id.adoptionAnimalInfoNameRefuge);
        adoptionAnimalInfoSurnameRefuge = rootView.findViewById(R.id.adoptionAnimalInfoSurnameRefuge);
        adoptionAnimalInfoProvinceRefuge = rootView.findViewById(R.id.adoptionAnimalInfoProvinceRefuge);
        adoptionAnimalInfoEmailRefuge = rootView.findViewById(R.id.adoptionAnimalInfoEmailRefuge);
        adoptionAnimalInfoPhoneRefuge = rootView.findViewById(R.id.adoptionAnimalInfoPhoneRefuge);
        adoptionAnimalInfoCodPostRefuge = rootView.findViewById(R.id.adoptionAnimalInfoCodPostRefuge);
        adoptionAnimalInfoStatusAdoption = rootView.findViewById(R.id.adoptionAnimalInfoStatusAdoption);
        updateAdoptionButton = rootView.findViewById(R.id.updateAdoptionButton);
        cancelAdoptionButton = rootView.findViewById(R.id.cancelAdoptionButton);

        // Obtener los datos de la AdoptionDTO pasados a través del Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            AdoptionDTO adoptionDTO = (AdoptionDTO) bundle.getSerializable("adoptionDTO");
            if (adoptionDTO != null) {
                adoptionAnimalInfoNameRefuge.setText(adoptionDTO.getUserName());
                adoptionAnimalInfoSurnameRefuge.setText(adoptionDTO.getSurname());
                adoptionAnimalInfoProvinceRefuge.setText(adoptionDTO.getProvince());
                adoptionAnimalInfoEmailRefuge.setText(adoptionDTO.getEmail());
                adoptionAnimalInfoPhoneRefuge.setText(adoptionDTO.getPhone());
                adoptionAnimalInfoCodPostRefuge.setText(adoptionDTO.getPostCode());
                adoptionAnimalInfoStatusAdoption.setText(adoptionDTO.getStatusAdoption());

                if ("En progreso".equals(adoptionDTO.getStatusAdoption())) {
                    // Si el estado de la adopción es "En progreso", mostrar los botones
                    updateAdoptionButton.setVisibility(View.VISIBLE);
                    cancelAdoptionButton.setVisibility(View.VISIBLE);
                } else {
                    // Si el estado de la adopción no es "En progreso", ocultar los botones
                    updateAdoptionButton.setVisibility(View.GONE);
                    cancelAdoptionButton.setVisibility(View.GONE);
                }

            }
        }

        updateAdoptionButton.setOnClickListener(v -> updateAdoption());

        cancelAdoptionButton.setOnClickListener(v -> cancelAdoption());

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goBackAdoptionInfoRefugeButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
    }

    private void updateAdoption() {
        // Obtener el token de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(getContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el ID de la adopción de los argumentos del fragmento
        Bundle bundle = getArguments();
        if (bundle != null) {
            long adoptionId = bundle.getLong("adoptionId");

            // Crear una instancia de OkHttpClient con un interceptor para agregar el token a las solicitudes
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });

            // Crear una instancia de Retrofit con OkHttpClient personalizado
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constans.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            AdoptionService adoptionService = retrofit.create(AdoptionService.class);

            // Hacer la llamada a la API para actualizar el estado de la adopción
            adoptionService.updateStatusAdoption(adoptionId).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "¡Estado de adopción actualizado!", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack();
                    } else {
                        Toast.makeText(getContext(), "Error al actualizar el estado de adopción", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "No se encontró la información de la adopción", Toast.LENGTH_SHORT).show();
        }
    }

    private void cancelAdoption() {
        // Obtener el token de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(getContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el ID de la adopción de los argumentos del fragmento
        Bundle bundle = getArguments();
        if (bundle != null) {
            long adoptionId = bundle.getLong("adoptionId");

            // Crear una instancia de OkHttpClient con un interceptor para agregar el token a las solicitudes
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });

            // Crear una instancia de Retrofit con OkHttpClient personalizado
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constans.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            AdoptionService adoptionService = retrofit.create(AdoptionService.class);

            // Hacer la llamada a la API para cancelar el estado de la adopción
            adoptionService.cancelStatusAdoption(adoptionId).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "¡Estado de adopción cancelado!", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack();
                    } else {
                        Toast.makeText(getContext(), "Error al cancelar el estado de adopción", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "No se encontró la información de la adopción", Toast.LENGTH_SHORT).show();
        }
    }
}
