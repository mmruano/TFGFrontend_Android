package com.vedruna.simbad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.Model.User;
import com.vedruna.simbad.API.Security.AuthResponse;
import com.vedruna.simbad.API.Security.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRegisterFragment extends Fragment {

    private EditText nameEditText;
    private EditText surnameEditText;
    private Spinner genderSpinner;
    private Spinner provinceSpinner;
    private EditText postalCodeEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private Button createUserButton;
    private Button goBackRegisterUserButton;

    public UserRegisterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_register, container, false);

        nameEditText = view.findViewById(R.id.name_user_register);
        surnameEditText = view.findViewById(R.id.surname_register_user);
        genderSpinner = view.findViewById(R.id.spinner_register_user_gender);
        provinceSpinner = view.findViewById(R.id.spinner_register_user_province);
        postalCodeEditText = view.findViewById(R.id.codPost_register_user);
        emailEditText = view.findViewById(R.id.email_register_user);
        phoneEditText = view.findViewById(R.id.phone_register_user);
        passwordEditText = view.findViewById(R.id.password_register_user);
        createUserButton = view.findViewById(R.id.createUserButton);
        goBackRegisterUserButton = view.findViewById(R.id.goBackRegisterUserButton);

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

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, genderArray);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, provinceArray);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);

        createUserButton.setOnClickListener(v -> createUser());
        goBackRegisterUserButton.setOnClickListener(v -> goBackRegisterUser());

        return view;
    }

    private void goBackRegisterUser() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
    }

    private void createUser() {
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        String gender = genderSpinner.getSelectedItem().toString();
        String province = provinceSpinner.getSelectedItem().toString();
        String postalCode = postalCodeEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Validar que todos los campos están llenos
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(gender) ||
                TextUtils.isEmpty(province) || TextUtils.isEmpty(postalCode) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(gender) || TextUtils.isEmpty(province)) {
            Toast.makeText(requireContext(), "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@") || email.contains(" ")) {
            Toast.makeText(requireContext(), "Por favor, introduzca un correo electrónico válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(null, name, surname, gender, province, postalCode, email, phone, password, null, null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthService authService = retrofit.create(AuthService.class);
        Call<AuthResponse> call = authService.createUser(user);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Usuario creado con éxito.", Toast.LENGTH_SHORT).show();
                    // Navegar a la actividad de login
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    requireActivity().finish(); // Finaliza la actividad actual para evitar regresar a ella
                } else {
                    Toast.makeText(requireContext(), "Error al crear el usuario.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Fallo en la conexión.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
