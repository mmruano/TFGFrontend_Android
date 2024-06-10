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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.Model.Refuge;
import com.vedruna.simbad.API.Security.AuthResponse;
import com.vedruna.simbad.API.Security.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RefugeRegisterFragment extends Fragment {

    private EditText nameEditText;
    private EditText emailEditText;
    private Spinner typeRefugeSpinner;
    private EditText cifEditText;
    private Spinner provinceSpinner;
    private EditText streetEditText;
    private EditText postalCodeEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private Button createRefugeButton;
    private Button goBackRegisterRefugeButton;

    public RefugeRegisterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refuge_register, container, false);

        nameEditText = view.findViewById(R.id.name_refuge_register);
        emailEditText = view.findViewById(R.id.email_register_refuge);
        typeRefugeSpinner = view.findViewById(R.id.spinner_register_refuge_type_refuge);
        cifEditText = view.findViewById(R.id.cif_register_refuge);
        provinceSpinner = view.findViewById(R.id.spinner_register_province_refuge);
        streetEditText = view.findViewById(R.id.street_register_refuge);
        postalCodeEditText = view.findViewById(R.id.post_address_register_refuge);
        phoneEditText = view.findViewById(R.id.phone_register_refuge);
        passwordEditText = view.findViewById(R.id.password_register_refuge);
        createRefugeButton = view.findViewById(R.id.createRefugeButton);
        goBackRegisterRefugeButton = view.findViewById(R.id.goBackRegisterRefugeButton);

        String[] typeRefugeArray = {
                "", "Protectora de animales", "Asociación de adopción animal",
                "Refugio de mascotas", "Hogar de acogida animal"
        };
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

        ArrayAdapter<String> typeRefugeAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, typeRefugeArray);
        typeRefugeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeRefugeSpinner.setAdapter(typeRefugeAdapter);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, provinceArray);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);

        createRefugeButton.setOnClickListener(v -> createRefuge());
        goBackRegisterRefugeButton.setOnClickListener(v -> goBackRegisterRefuge());

        return view;
    }

    public void goBackRegisterRefuge() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
    }

    private boolean validarCIF(String cif) {
        // Expresión regular para validar el CIF
        String cifPattern = "^[A-Z]{1}[0-9]{7}[A-J0-9]{1}$";

        // Validar el CIF utilizando la expresión regular
        return cif.matches(cifPattern);
    }

    private void createRefuge() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String typeRefuge = typeRefugeSpinner.getSelectedItem().toString();
        String cif = cifEditText.getText().toString();
        String province = provinceSpinner.getSelectedItem().toString();
        String street = streetEditText.getText().toString();
        String postalCode = postalCodeEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Validar que todos los campos están llenos
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(typeRefuge) ||
                TextUtils.isEmpty(cif) || TextUtils.isEmpty(province) || TextUtils.isEmpty(street) ||
                TextUtils.isEmpty(postalCode) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(typeRefuge) || TextUtils.isEmpty(province)) {
            Toast.makeText(requireContext(), "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        cif = cifEditText.getText().toString();

        // Validar el CIF
        if (!validarCIF(cif)) {
            Toast.makeText(requireContext(), "Por favor, introduzca un CIF válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que el correo electrónico contiene un '@'
        if (!email.contains("@") || email.contains(" ")) {
            Toast.makeText(requireContext(), "Por favor, introduzca un correo electrónico válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        Refuge refuge = new Refuge(null, name, typeRefuge, cif, street, province, postalCode, phone, email, password, null, null, null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthService authService = retrofit.create(AuthService.class);
        Call<AuthResponse> call = authService.createRefuge(refuge);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Refugio creado con éxito.", Toast.LENGTH_SHORT).show();
                    // Navegar a la actividad de inicio de sesión
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    requireActivity().finish(); // Finaliza la actividad actual para evitar regresar a ella
                } else {
                    Toast.makeText(requireContext(), "Error al crear el refugio.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Fallo en la conexión.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
