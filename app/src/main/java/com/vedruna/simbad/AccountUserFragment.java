package com.vedruna.simbad;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.Interface.UserService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountUserFragment extends Fragment {

    String[] genderArray = {"", "Hombre", "Mujer"};
    String[] provinceArray = {
            "", "Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila",
            "Badajoz", "Baleares", "Barcelona", "Burgos", "Cáceres", "Cádiz",
            "Cantabria", "Castellón", "Ciudad Real", "Córdoba", "Cuenca",
            "Gerona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca",
            "Jaén", "La Coruña", "La Rioja", "Las Palmas", "León", "Lérida",
            "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Palencia",
            "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia",
            "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia",
            "Valladolid", "Vizcaya", "Zamora", "Zaragoza"
    };

    // Declarar las variables como miembros de la clase
    EditText nameEditUser;
    EditText surnameEditUser;
    Spinner spinnerEditUserGender;
    Spinner spinnerEditUserProvince;
    EditText codPostEditUser;
    EditText phoneEditUser;
    Button goBackAccountEditUserButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_user, container, false);

        // Inicializar las variables con las vistas necesarias
        nameEditUser = view.findViewById(R.id.name_edit_user);
        surnameEditUser = view.findViewById(R.id.surname_edit_user);
        spinnerEditUserGender = view.findViewById(R.id.spinner_edit_user_gender);
        spinnerEditUserProvince = view.findViewById(R.id.spinner_edit_user_province);
        codPostEditUser = view.findViewById(R.id.codPost_edit_user);
        phoneEditUser = view.findViewById(R.id.phone_edit_user);

        Button goBackAccountEditUserButton = view.findViewById(R.id.goBackAccountEditUserButton);
        Button editUserButton = view.findViewById(R.id.editUserButton);

        // Configurar adaptador para spinnerEditUserGender
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, genderArray);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditUserGender.setAdapter(genderAdapter);

        // Configurar adaptador para spinnerEditUserProvince
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, provinceArray);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditUserProvince.setAdapter(provinceAdapter);

        goBackAccountEditUserButton.setOnClickListener(v -> goBackAccountEditUser());

        editUserButton.setOnClickListener(v -> {
            // Obtener los valores de los campos
            String name = nameEditUser.getText().toString();
            String surname = surnameEditUser.getText().toString();
            String gender = spinnerEditUserGender.getSelectedItem().toString();
            String province = spinnerEditUserProvince.getSelectedItem().toString();
            String codPost = codPostEditUser.getText().toString();
            String phone = phoneEditUser.getText().toString();

            // Verificar si al menos uno de los campos obligatorios está rellenado
            if (name.isEmpty() && surname.isEmpty() && gender.isEmpty() && province.isEmpty() && codPost.isEmpty() && phone.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, rellena al menos uno de los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar si los campos están vacíos y enviar null en su lugar
            if (name.isEmpty()) name = null;
            if (surname.isEmpty()) surname = null;
            if (gender.isEmpty()) gender = null;
            if (province.isEmpty()) province = null;
            if (codPost.isEmpty()) codPost = null;
            if (phone.isEmpty()) phone = null;

            // Realizar la llamada al método de edición del usuario
            editUser(name, surname, gender, province, codPost, phone);
        });

        return view;
    }

    private void goBackAccountEditUser() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
    }

    private void editUser(String name, String surname, String gender, String province,
                          String codPost, String phone) {
        // Obtener el token de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(requireContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
            return;
        }

        // Realizar una llamada a la API para editar el usuario
        // Configurar OkHttpClient con el interceptor para agregar el token de autorización
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        // Crear una instancia de Retrofit con el cliente OkHttp configurado
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL) // Reemplaza con la URL de tuAPI
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        // Realizar la llamada para editar el usuario
        Call<Void> call = userService.editUser(name, surname, gender, province, codPost, phone);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    // La edición del usuario fue exitosa
                    // Puedes manejar la respuesta de la API aquí, por ejemplo, mostrar un mensaje de éxito
                    Toast.makeText(requireContext(), "Usuario editado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    // La llamada a la API fue exitosa pero ocurrió un error al editar el usuario
                    // Puedes manejar el error aquí, por ejemplo, mostrar un mensaje de error
                    Toast.makeText(requireContext(), "Error al editar el usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Ocurrió un error durante la llamada a la API
                // Puedes manejar el error aquí, por ejemplo, mostrar un mensaje de error de conexión
                Toast.makeText(requireContext(), "Fallo en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}