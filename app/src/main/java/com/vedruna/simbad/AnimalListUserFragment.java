package com.vedruna.simbad;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.Interface.AnimalService;
import com.vedruna.simbad.ListAdapters.ListAnimalsUserAdapter;
import com.vedruna.simbad.API.DTO.AnimalDTO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalListUserFragment extends Fragment {
    private ListView animalListViewUser;
    private ListAnimalsUserAdapter adapter;
    private String[] speciesArray = {"", "Perro", "Gato"};
    private String[] sizeArray = {"", "Grande", "Medio", "Pequeño"};
    private String[] energyLevelArray = {"", "Alta", "Media", "Baja"};
    private String[] provinceArray = {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animal_list_user, container, false);

        animalListViewUser = rootView.findViewById(R.id.animalListViewUser);

        // Crear una lista de ejemplo de animales
        adapter = new ListAnimalsUserAdapter(getContext(), new ArrayList<>());
        animalListViewUser.setAdapter(adapter);

        // Configurar el botón para abrir el diálogo de búsqueda
        Button searchButton = rootView.findViewById(R.id.showSearchListAnimalPopupButton);
        searchButton.setOnClickListener(v -> createSearchAnimalListDialog());

        getAnimalList(null, null, null, null);

        return rootView;
    }

    private void createSearchAnimalListDialog() {
        // Crear un nuevo Dialog
        Dialog dialog = new Dialog(requireContext());

        // Establecer el layout del Dialog
        dialog.setContentView(R.layout.search_list_animal_popup);

        // Configurar el tamaño del Dialog
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);

        // Obtener referencias a los elementos del layout
        Spinner speciesSpinner = dialog.findViewById(R.id.spinner_species_search_filter);
        Spinner sizeSpinner = dialog.findViewById(R.id.spinner_size_search_filter);
        Spinner energyLevelSpinner = dialog.findViewById(R.id.spinner_energyLevel_search_filter);
        Spinner provinceSpinner = dialog.findViewById(R.id.spinner_province_search_filter);
        Button searchButton = dialog.findViewById(R.id.searchListAnimalButton);
        Button goBackSearchButton = dialog.findViewById(R.id.goBackSearchButton);

        // Configurar adaptadores para los Spinners
        ArrayAdapter<String> speciesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, speciesArray);
        speciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciesSpinner.setAdapter(speciesAdapter);

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, sizeArray);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        ArrayAdapter<String> energyLevelAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, energyLevelArray);
        energyLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        energyLevelSpinner.setAdapter(energyLevelAdapter);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, provinceArray);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);

        // Configurar el botón de búsqueda
        searchButton.setOnClickListener(v -> {
            // Obtener valores seleccionados de los Spinners y enviar null si está vacío
            String selectedSpecies = speciesSpinner.getSelectedItem().toString();
            String species = selectedSpecies.isEmpty() ? null : selectedSpecies;

            String selectedSize = sizeSpinner.getSelectedItem().toString();
            String size = selectedSize.isEmpty() ? null : selectedSize;

            String selectedEnergyLevel = energyLevelSpinner.getSelectedItem().toString();
            String energyLevel = selectedEnergyLevel.isEmpty() ? null : selectedEnergyLevel;

            String selectedProvince = provinceSpinner.getSelectedItem().toString();
            String province = selectedProvince.isEmpty() ? null : selectedProvince;

            // Llamar a getAnimalList con los parámetros seleccionados
            getAnimalList(species, size, energyLevel, province);

            dialog.dismiss();
        });

        // Configurar el botón para regresar
        goBackSearchButton.setOnClickListener(v -> dialog.dismiss());

        // Mostrar el Dialog
        dialog.show();
    }

    private void getAnimalList(String species, String size, String energyLevel, String province) {
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

        // Crear una instancia de la interfaz AnimalService
        AnimalService animalService = retrofit.create(AnimalService.class);

        // Hacer la llamada al servidor para obtener la lista de animales con los parámetros de búsqueda
        animalService.getAnimals(species, size, energyLevel, province).enqueue(new Callback<List<AnimalDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<AnimalDTO>> call, @NonNull Response<List<AnimalDTO>> response) {
                if (response.isSuccessful()) {
                    // Actualizar el adaptador con la lista de animales obtenida
                    List<AnimalDTO> animalList = response.body();
                    if (animalList != null) {
                        adapter.clear(); // Limpiar la lista actual del adaptador
                        adapter.addAll(animalList); // Agregar los nuevos elementos
                        adapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
                    } else {
                        Toast.makeText(getContext(), "La lista de animales es nula", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "No hay animales disponibles según esas especificaciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<AnimalDTO>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error de red al obtener la lista de animales", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
