package com.vedruna.simbad;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AnimalDTO;
import com.vedruna.simbad.API.DTO.UserDTO;
import com.vedruna.simbad.API.Interface.AdoptionService;
import com.vedruna.simbad.API.Interface.UserService;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalInfoUserFragment extends Fragment {

    private TextView animalNameInfoUser;
    private TextView animalProvinceInfoUser;
    private TextView animalGenderInfoUser;
    private TextView animalSizeInfoUser;
    private TextView animalAgeInfoUser;
    private TextView animalBirtdateInfoUser;
    private TextView animalEnergyLevelInfoUser;
    private TextView animalSpeciesInfoUser;
    private TextView animalEntryDateInfoUser;
    private TextView animalHealthInfoUser;
    private TextView animalDescriptionInfoUser;
    private TextView animalInfoRefugeNameUser;
    private TextView animalInfoRefugeStreetUser;
    private TextView animalInfoRefugeEmailUser;
    private TextView animalInfoRefugeProvinceUser;
    private TextView animalInfoRefugePhoneUser;
    private TextView animalInfoRefugePostCodeUser;
    private Button createAdoptionPopupButton;
    private ImageView animalInfoImageViewUser;
    UserDTO userDTO;
    AnimalDTO animalDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_animal_info_user, container, false);

        createAdoptionPopupButton = rootView.findViewById(R.id.createAdoptionPopupButton);
        createAdoptionPopupButton.setOnClickListener(v -> {
            // Mostrar el diálogo
            createAdoptionPopup();
        });

        // Inicializar los elementos TextView e ImageView
        animalNameInfoUser = rootView.findViewById(R.id.animalNameInfoUser);
        animalProvinceInfoUser = rootView.findViewById(R.id.animalProvinceInfoUser);
        animalGenderInfoUser = rootView.findViewById(R.id.animalGenderInfoUser);
        animalSizeInfoUser = rootView.findViewById(R.id.animalSizeInfoUser);
        animalAgeInfoUser = rootView.findViewById(R.id.animalAgeInfoUser);
        animalBirtdateInfoUser = rootView.findViewById(R.id.animalBirtdateInfoUser);
        animalEnergyLevelInfoUser = rootView.findViewById(R.id.animalEnergyLevelInfoUser);
        animalSpeciesInfoUser = rootView.findViewById(R.id.animalSpeciesInfoUser);
        animalEntryDateInfoUser = rootView.findViewById(R.id.animalEntryDateInfoUser);
        animalHealthInfoUser = rootView.findViewById(R.id.animalHealthInfoUser);
        animalDescriptionInfoUser = rootView.findViewById(R.id.animalDescriptionInfoUser);
        animalInfoRefugeNameUser = rootView.findViewById(R.id.animalInfoRefugeNameUser);
        animalInfoRefugeStreetUser = rootView.findViewById(R.id.animalInfoRefugeStreetUser);
        animalInfoRefugeEmailUser = rootView.findViewById(R.id.animalInfoRefugeEmailUser);
        animalInfoRefugeProvinceUser = rootView.findViewById(R.id.animalInfoRefugeProvinceUser);
        animalInfoRefugePhoneUser = rootView.findViewById(R.id.animalInfoRefugePhoneUser);
        animalInfoRefugePostCodeUser = rootView.findViewById(R.id.animalInfoRefugePostCodeUser);
        animalInfoImageViewUser = rootView.findViewById(R.id.animalInfoImageViewUser);

        // Obtener los argumentos del fragmento
        Bundle bundle = getArguments();
        if (bundle != null) {
            animalDTO = (AnimalDTO) bundle.getSerializable("animalDTO");
            if (animalDTO != null) {
                // Rellenar los TextView con la información del AnimalDTO
                animalNameInfoUser.setText(animalDTO.getName());
                animalProvinceInfoUser.setText(animalDTO.getProvince());
                animalGenderInfoUser.setText(animalDTO.getGender());
                animalSizeInfoUser.setText(animalDTO.getSize());
                animalAgeInfoUser.setText(animalDTO.getAge());
                animalBirtdateInfoUser.setText(animalDTO.getBirthDate());
                animalEnergyLevelInfoUser.setText(animalDTO.getEnergyLevel());
                animalSpeciesInfoUser.setText(animalDTO.getSpecies());
                animalEntryDateInfoUser.setText(animalDTO.getEntryDate());
                animalHealthInfoUser.setText(animalDTO.getHealth());
                animalDescriptionInfoUser.setText(animalDTO.getDescription());
                animalInfoRefugeNameUser.setText(animalDTO.getRefugeName());
                animalInfoRefugeStreetUser.setText(animalDTO.getRefugeStreet());
                animalInfoRefugeEmailUser.setText(animalDTO.getRefugeEmail());
                animalInfoRefugeProvinceUser.setText(animalDTO.getRefugeProvince());
                animalInfoRefugePhoneUser.setText(animalDTO.getRefugePhone());
                animalInfoRefugePostCodeUser.setText(animalDTO.getRefugeCodePost());

                // Cargar la imagen usando Glide
                Glide.with(this)
                        .load(Constans.BASE_URL + animalDTO.getPhoto()) // Asume que AnimalDTO tiene un método getImageUrl()
                        .into(animalInfoImageViewUser);
            }
        }

        return rootView;
    }

    private void createAdoptionPopup() {

        // Obtener el token de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(getContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
            return;
        }

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

        UserService userService = retrofit.create(UserService.class);

        // Hacer la llamada asincrónica para obtener la información del usuario
        Call<UserDTO> call = userService.getUserInfoByToken();
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {

                if (response.isSuccessful() && response.body() != null) {
                    userDTO = response.body();
                    // Crear el diálogo y mostrarlo
                    createAdoptionPopupWithUserInfo(userDTO);
                } else {
                    Toast.makeText(getContext(), "Error al obtener la información del usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error de red al obtener la información del usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAdoptionPopupWithUserInfo(UserDTO userDTO) {
        // Crear un nuevo Dialog
        Dialog dialog = new Dialog(requireContext());

        // Establecer el layout del Dialog
        dialog.setContentView(R.layout.create_adoption_popup);

        // Configurar el tamaño del Dialog
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);

        // Obtener referencias a los elementos de la interfaz de usuario dentro del diálogo
        TextView createAdoptionNameUser = dialog.findViewById(R.id.createAdoptionNameUser);
        TextView createAdoptionSurnameUser = dialog.findViewById(R.id.createAdoptionSurnameUser);
        TextView createAdoptionProvinceUser = dialog.findViewById(R.id.createAdoptionProvinceUser);
        TextView createAdoptionEmailUser = dialog.findViewById(R.id.createAdoptionEmailUser);
        TextView createAdoptionPhoneUser = dialog.findViewById(R.id.createAdoptionPhoneUser);
        TextView createPostCodNameUser = dialog.findViewById(R.id.createPostCodNameUser);

        // Actualizar los elementos de la interfaz de usuario con la información del usuario
        createAdoptionNameUser.setText(userDTO.getName());
        createAdoptionSurnameUser.setText(userDTO.getSurname());
        createAdoptionProvinceUser.setText(userDTO.getProvince());
        createAdoptionEmailUser.setText(userDTO.getEmail());
        createAdoptionPhoneUser.setText(userDTO.getPhone());
        createPostCodNameUser.setText(userDTO.getPostCode());

        // Configurar el botón para crear la adopción
        Button createAdoptionButton = dialog.findViewById(R.id.createAdoptionButton);
        createAdoptionButton.setOnClickListener(v -> createAdoption(dialog)); // Pasa el dialog como parámetro

        // Configurar el botón para volver a la búsqueda
        Button goBackSearchButton = dialog.findViewById(R.id.goBackSearchButton);
        goBackSearchButton.setOnClickListener(v -> dialog.dismiss());

        // Mostrar el Dialog
        dialog.show();
    }

    private void createAdoption(Dialog dialog) { // Recibe el dialog como parámetro
        // Obtener el token de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(getContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
            return;
        }

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

        adoptionService.createAdoption(animalDTO.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "¡Adopcion creada!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss(); // Cerrar el diálogo al crear la adopción exitosamente
                } else if (response.code() == 409) {
                    Toast.makeText(getContext(), "Ya has enviado una solicitud.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Error al crear la adopción", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error del servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
