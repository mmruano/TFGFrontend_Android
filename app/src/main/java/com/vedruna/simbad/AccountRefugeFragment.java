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
import com.vedruna.simbad.API.Interface.RefugeService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountRefugeFragment extends Fragment {

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

    EditText nameRefugeEdit;
    Spinner spinnerTypeRefuge;
    EditText cifEdit;
    Spinner spinnerProvinceRefuge;
    EditText streetEditRefuge;
    EditText codPostEditRefuge;
    EditText phoneEditRefuge;
    private Button goBackRefugeAccountEditButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_refuge, container, false);

        nameRefugeEdit = view.findViewById(R.id.name_refuge_edit);
        spinnerTypeRefuge = view.findViewById(R.id.spinner_edit_refuge_type_refuge);
        cifEdit = view.findViewById(R.id.cif_edit_refuge);
        spinnerProvinceRefuge = view.findViewById(R.id.spinner_edit_province_refuge);
        streetEditRefuge = view.findViewById(R.id.street_edit_refuge);
        codPostEditRefuge = view.findViewById(R.id.post_address_edit_refuge);
        phoneEditRefuge = view.findViewById(R.id.phone_edit_refuge);
        goBackRefugeAccountEditButton = view.findViewById(R.id.goBackRefugeAccountEditButton);

        Button editRefugeButton = view.findViewById(R.id.editRefugeButton);

        // Configurar adaptador para spinnerTypeRefuge
        ArrayAdapter<String> typeRefugeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, typeRefugeArray);
        typeRefugeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeRefuge.setAdapter(typeRefugeAdapter);

        // Configurar adaptador para spinnerProvinceRefuge
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, provinceArray);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvinceRefuge.setAdapter(provinceAdapter);

        goBackRefugeAccountEditButton.setOnClickListener(v -> goBackRefugeAccountEdit());

        editRefugeButton.setOnClickListener(v -> {
            String name = nameRefugeEdit.getText().toString();
            String typeRefuge = spinnerTypeRefuge.getSelectedItem().toString();
            String cif = cifEdit.getText().toString();
            String province = spinnerProvinceRefuge.getSelectedItem().toString();
            String street = streetEditRefuge.getText().toString();
            String codPost = codPostEditRefuge.getText().toString();
            String phone = phoneEditRefuge.getText().toString();

            if (name.isEmpty() && cif.isEmpty() && province.isEmpty() && street.isEmpty() && codPost.isEmpty() && phone.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, rellena al menos un campo", Toast.LENGTH_SHORT).show();
                return;
            }

            // Realizar la llamada al método de edición del refugio
            editRefuge(name.isEmpty() ? null : name,
                    typeRefuge.isEmpty() ? null : typeRefuge,
                    cif.isEmpty() ? null : cif,
                    province.isEmpty() ? null : province,
                    street.isEmpty() ? null : street,
                    codPost.isEmpty() ? null : codPost,
                    phone.isEmpty() ? null : phone);
        });

        return view;
    }

    private void goBackRefugeAccountEdit() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
    }

    private void editRefuge(String name, String typeRefuge, String cif,
                            String province, String street, String codPost, String phone) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(requireContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
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
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RefugeService refugeService = retrofit.create(RefugeService.class);
        Call<Void> call = refugeService.editRefuge(name, typeRefuge, cif, province, street, codPost, phone);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Refugio editado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Error al editar el refugio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Fallo en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
