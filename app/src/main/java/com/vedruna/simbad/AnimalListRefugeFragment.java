package com.vedruna.simbad;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AnimalDTO;
import com.vedruna.simbad.API.Interface.AnimalService;
import com.vedruna.simbad.API.Model.Animal;
import com.vedruna.simbad.ListAdapters.ListAnimalsRefugeAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalListRefugeFragment extends Fragment {

    private Button showCreateAnimalDialogButton;
    private Button goBackCreateAnimalButton;
    private ImageButton createAnimalCalendarPickButton;
    private Dialog createAnimalDialog;
    private ActivityResultLauncher<String> mGetContent;
    private String[] genderArray = {"", "Macho", "Hembra"};
    private String[] speciesArray = {"", "Perro", "Gato"};
    private String[] sizeArray = {"", "Grande", "Medio", "Pequeño"};
    private String[] energyLevelArray = {"", "Alta", "Media", "Baja"};
    private EditText createAnimalBirthDateEditText;

    private ArrayList<AnimalDTO> dataArrayList;
    private Uri selectedImageUri;

    private ListView listView;
    private ListAnimalsRefugeAdapter adapter;

    private TextView emptyListTextView;

    public AnimalListRefugeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_list_refuge, container, false);
        showCreateAnimalDialogButton = view.findViewById(R.id.createAnimalPopupButton);

        showCreateAnimalDialogButton.setOnClickListener(v -> createAnimalDialog());

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                result -> {
                    if (result != null) {
                        selectedImageUri = result;
                        ImageView imageView = createAnimalDialog.findViewById(R.id.createAnimalPhoto);
                        if (imageView != null) {
                            Glide.with(requireContext())
                                    .load(result)
                                    .into(imageView);
                        }
                    }
                });
        emptyListTextView = view.findViewById(R.id.animalListViewRefugeText2);
        listView = view.findViewById(R.id.animalListViewRefuge);
        dataArrayList = new ArrayList<>();
        adapter = new ListAnimalsRefugeAdapter(requireContext(), dataArrayList);
        listView.setAdapter(adapter);

        getAnimalsFromRefuge();

        return view;
    }

    private void getAnimalsFromRefuge() {
        // Obtener el token de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(requireContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear el interceptor para agregar el token al header de la solicitud
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });

        // Construir Retrofit con el interceptor configurado
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        // Crear la instancia de AnimalService
        AnimalService animalService = retrofit.create(AnimalService.class);

        // Realizar la llamada a la API para obtener la lista de animales del refugio
        Call<List<AnimalDTO>> call = animalService.getAnimalsByRefugeId();

        call.enqueue(new Callback<List<AnimalDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<AnimalDTO>> call, @NonNull Response<List<AnimalDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dataArrayList.addAll(response.body());
                    // Verificar si la lista está vacía y establecer la visibilidad del TextView
                    if (dataArrayList.isEmpty()) {
                        emptyListTextView.setVisibility(View.VISIBLE);
                    } else {
                        emptyListTextView.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<AnimalDTO>> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Error de red al obtener la lista de animales del refugio", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAnimalDialog() {
        createAnimalDialog = new Dialog(requireContext());
        createAnimalDialog.setContentView(R.layout.create_animal_refuge_popup);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        Objects.requireNonNull(createAnimalDialog.getWindow()).setLayout(params.width, params.height);

        ImageView imageView = createAnimalDialog.findViewById(R.id.createAnimalPhoto);

        // Crear la variable final para animal
        final Animal animal = new Animal();

        // Establecer el clic de escucha en el ImageView
        imageView.setOnClickListener(v -> {
            mGetContent.launch("image/*");
        });

        Spinner speciesSpinner = createAnimalDialog.findViewById(R.id.spinner_species_create_animal_refuge);
        Spinner sizeSpinner = createAnimalDialog.findViewById(R.id.spinner_size_create_animal_refuge);
        Spinner energyLevelSpinner = createAnimalDialog.findViewById(R.id.spinner_energyLevel_create_animal_refuge);
        EditText createAnimalNameEditText = createAnimalDialog.findViewById(R.id.create_animal_name_refuge);
        Spinner spinnerGender = createAnimalDialog.findViewById(R.id.spinner_gender_create_animal_refuge);
        createAnimalBirthDateEditText = createAnimalDialog.findViewById(R.id.create_animal_birtDate_refuge);
        createAnimalCalendarPickButton = createAnimalDialog.findViewById(R.id.createAnimalCalendarPickButton);

        // Añadir campo para la fecha de entrada
        EditText createAnimalEntryDateEditText = createAnimalDialog.findViewById(R.id.create_animal_birtDate_refuge);

        // Establece un clic de escucha en el EditText de la fecha de nacimiento
        createAnimalCalendarPickButton.setOnClickListener(v -> showDatePickerDialog(createAnimalBirthDateEditText));

        EditText createAnimalAgeEditText = createAnimalDialog.findViewById(R.id.create_animal_age_refuge);
        EditText createAnimalHealthEditText = createAnimalDialog.findViewById(R.id.create_animal_health_refuge);
        EditText createAnimalDescriptionEditText = createAnimalDialog.findViewById(R.id.create_animal_description_refuge);
        Button createAnimalButton = createAnimalDialog.findViewById(R.id.createAnimalButton);
        Button cancelButton = createAnimalDialog.findViewById(R.id.goBackCreateAnimalButton);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, genderArray);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        ArrayAdapter<String> speciesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, speciesArray);
        speciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciesSpinner.setAdapter(speciesAdapter);

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, sizeArray);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        ArrayAdapter<String> energyLevelAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, energyLevelArray);
        energyLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        energyLevelSpinner.setAdapter(energyLevelAdapter);

        createAnimalButton.setOnClickListener(v -> {
            if (validateFields(createAnimalNameEditText, createAnimalBirthDateEditText, createAnimalAgeEditText,
                    createAnimalHealthEditText, createAnimalDescriptionEditText, createAnimalEntryDateEditText)
                    && validateSpinners(speciesSpinner, sizeSpinner, energyLevelSpinner)
                    && selectedImageUri != null) {

                animal.setName(createAnimalNameEditText.getText().toString());
                animal.setGender(spinnerGender.getSelectedItem().toString());
                animal.setSpecies(speciesSpinner.getSelectedItem().toString());

                // Convertir LocalDate a String sin comillas adicionales
                String birthDate = createAnimalBirthDateEditText.getText().toString();
                animal.setBirthDate(LocalDate.parse(birthDate)); // Verificar formato

                animal.setAge(createAnimalAgeEditText.getText().toString());
                animal.setSize(sizeSpinner.getSelectedItem().toString());
                animal.setEnergyLevel(energyLevelSpinner.getSelectedItem().toString());
                animal.setHealth(createAnimalHealthEditText.getText().toString());
                animal.setDescription(createAnimalDescriptionEditText.getText().toString());

                // La fecha de entrada se establece aquí
                String entryDate = LocalDate.now().toString();
                animal.setEntryDate(LocalDate.parse(entryDate)); // Usar la fecha actual

                byte[] fileBytes = getFileBytesFromUri(selectedImageUri);
                RequestBody requestBody = RequestBody.create(fileBytes, MediaType.parse("image/*"));
                MultipartBody.Part photoPart = MultipartBody.Part.createFormData("photo", "photo.jpg", requestBody);

                createNewAnimal(animal, photoPart);

                createAnimalDialog.dismiss();
            } else {
                Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> createAnimalDialog.dismiss());
        createAnimalDialog.show();
    }

    private void createNewAnimal(Animal animal, MultipartBody.Part photoPart) {
        // Obtener el token de SharedPreferences
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
                    .header("Content-Type", "multipart/form-data")
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

        Call<Void> call = animalService.createAnimal(
                animal.getName(),
                animal.getGender(),
                animal.getAge(),
                animal.getBirthDate().toString(),
                animal.getSpecies(),
                animal.getSize(),
                animal.getEnergyLevel(),
                animal.getHealth(),
                animal.getDescription(),
                photoPart
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Animal creado exitosamente", Toast.LENGTH_SHORT).show();
                    dataArrayList.clear();
                    getAnimalsFromRefuge();
                } else {
                    Toast.makeText(requireContext(), "Error al crear el animal", Toast.LENGTH_SHORT).show();
                    assert response.errorBody() != null;
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog(EditText editText) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view, year, month, dayOfMonth) -> {
            // El usuario seleccionó una fecha, haz algo con ella aquí
            String selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month + 1, dayOfMonth);
            editText.setText(selectedDate);
        }, 2022, 0, 1); // El tercer parámetro (2022) es el año inicial, el cuarto (0) es el mes inicial, el quinto (1) es el día inicial
        datePickerDialog.show();
    }

    private boolean validateFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean validateSpinners(Spinner... spinners) {
        for (Spinner spinner : spinners) {
            if (spinner.getSelectedItem().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private byte[] getFileBytesFromUri(Uri uri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while (true) {
                assert inputStream != null;
                if ((length = inputStream.read(buffer)) == -1) break;
                byteArrayOutputStream.write(buffer, 0, length);
            }
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}