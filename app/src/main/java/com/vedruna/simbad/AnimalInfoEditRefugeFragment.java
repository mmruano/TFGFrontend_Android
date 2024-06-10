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
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AnimalDTO;
import com.vedruna.simbad.API.Interface.AnimalService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

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

public class AnimalInfoEditRefugeFragment extends Fragment {

    private EditText edit_animal_info_name_refuge;
    private EditText edit_animal_info_age_refuge;
    private EditText edit_animal_info_birthDate_refuge;
    private EditText edit_animal_info_health_refuge;
    private EditText edit_animal_info_description_refuge;
    private Spinner spinner_edit_animal_info_gender_refuge;
    private Spinner spinner_edit_animal_info_species_refuge;
    private Spinner spinner_edit_animal_info_size_refuge;
    private Spinner spinner_edit_animal_info_energyLevel_refuge;
    private ImageButton editAnimalCalendarPickButton;
    private ImageView editAnimalInfoPhotoRefuge;
    private String[] genderArray = {"", "Macho", "Hembra"};
    private String[] speciesArray = {"", "Perro", "Gato"};
    private String[] sizeArray = {"", "Grande", "Medio", "Pequeño"};
    private String[] energyLevelArray = {"", "Alta", "Media", "Baja"};
    private ActivityResultLauncher<String> mGetContent;
    private Uri selectedImageUri;
    private Button goBackEditAnimalButton;

    public AnimalInfoEditRefugeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animal_info_edit_refuge, container, false);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                result -> {
                    if (result != null) {
                        selectedImageUri = result;
                        ImageView imageView = rootView.findViewById(R.id.editAnimalInfoPhotoRefuge);
                        if (imageView != null) {
                            Glide.with(requireContext())
                                    .load(result)
                                    .into(imageView);
                        }
                    }
                });

        // Obtener referencias a los EditText y Spinners
        edit_animal_info_name_refuge = rootView.findViewById(R.id.edit_animal_info_name_refuge);
        edit_animal_info_age_refuge = rootView.findViewById(R.id.edit_animal_info_age_refuge);
        edit_animal_info_birthDate_refuge = rootView.findViewById(R.id.edit_animal_info_birthDate_refuge);
        edit_animal_info_health_refuge = rootView.findViewById(R.id.edit_animal_info_health_refuge);
        edit_animal_info_description_refuge = rootView.findViewById(R.id.edit_animal_info_description_refuge);
        spinner_edit_animal_info_gender_refuge = rootView.findViewById(R.id.spinner_edit_animal_info_gender_refuge);
        spinner_edit_animal_info_species_refuge = rootView.findViewById(R.id.spinner_edit_animal_info_species_refuge);
        spinner_edit_animal_info_size_refuge = rootView.findViewById(R.id.spinner_edit_animal_info_size_refuge);
        spinner_edit_animal_info_energyLevel_refuge = rootView.findViewById(R.id.spinner_edit_animal_info_energyLevel_refuge);
        goBackEditAnimalButton = rootView.findViewById(R.id.goBackEditAnimalButton);

        editAnimalCalendarPickButton = rootView.findViewById(R.id.editAnimalCalendarPickButton);
        editAnimalInfoPhotoRefuge = rootView.findViewById(R.id.editAnimalInfoPhotoRefuge);

        // Establecer un clic de escucha en el ImageView de la foto
        editAnimalInfoPhotoRefuge.setOnClickListener(v -> mGetContent.launch("image/*"));

        // Establecer un clic de escucha en el botón de calendario
        editAnimalCalendarPickButton.setOnClickListener(v -> showDatePickerDialog(edit_animal_info_birthDate_refuge));

        // Configurar adaptadores para los Spinners
        configureSpinnerAdapter(spinner_edit_animal_info_gender_refuge, genderArray);
        configureSpinnerAdapter(spinner_edit_animal_info_species_refuge, speciesArray);
        configureSpinnerAdapter(spinner_edit_animal_info_size_refuge, sizeArray);
        configureSpinnerAdapter(spinner_edit_animal_info_energyLevel_refuge, energyLevelArray);

        Button editAnimalInfoButton = rootView.findViewById(R.id.editAnimalInfoButton);
        editAnimalInfoButton.setOnClickListener(v -> editAnimal());
        goBackEditAnimalButton.setOnClickListener(v -> goBackEditAnimal());
        return rootView;
    }

    private void goBackEditAnimal() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
    }

    private void editAnimal() {
        // Obtener el token de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(requireContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener los valores de los campos
        String name = edit_animal_info_name_refuge.getText().toString();
        String gender = spinner_edit_animal_info_gender_refuge.getSelectedItem().toString();
        String age = edit_animal_info_age_refuge.getText().toString();
        String species = spinner_edit_animal_info_species_refuge.getSelectedItem().toString();
        String size = spinner_edit_animal_info_size_refuge.getSelectedItem().toString();
        String energyLevel = spinner_edit_animal_info_energyLevel_refuge.getSelectedItem().toString();
        String birthDate = edit_animal_info_birthDate_refuge.getText().toString();
        String health = edit_animal_info_health_refuge.getText().toString();
        String description = edit_animal_info_description_refuge.getText().toString();

        if (name.isEmpty() && gender.isEmpty() && age.isEmpty() && species.isEmpty() && size.isEmpty()
                && energyLevel.isEmpty() && birthDate.isEmpty() && health.isEmpty() && description.isEmpty()) {
            Toast.makeText(requireContext(), "Rellene al menos un campo", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear una parte multipart para la foto del animal si hay una foto seleccionada
        MultipartBody.Part photoPart = null;
        if (selectedImageUri != null) {
            byte[] fileBytes = getFileBytesFromUri(selectedImageUri);
            if (fileBytes != null) {
                RequestBody requestBody = RequestBody.create(fileBytes, MediaType.parse("image/*"));
                photoPart = MultipartBody.Part.createFormData("photo", "photo.jpg", requestBody);
            }
        }

        // Obtener el ID del animal de los argumentos del fragmento
        Bundle bundle = getArguments();
        AnimalDTO animalDTO = null;
        Long animalId = null;
        if (bundle != null) {
            animalDTO = (AnimalDTO) bundle.getSerializable("animalDTO");
            animalId = bundle.getLong("animalId");
        }

        if (animalDTO != null) {
            if (name.equals(animalDTO.getName()) || name.isEmpty()) {
                name = null; // No enviar el nombre si es igual al del AnimalDTO o está vacío
            }
            if (gender.equals(animalDTO.getGender()) || gender.isEmpty()) {
                gender = null; // No enviar el género si es igual al del AnimalDTO
            }
            if (age.equals(animalDTO.getAge()) || age.isEmpty()) {
                age = null; // No enviar la edad si es igual a la del AnimalDTO o está vacía
            }
            if (species.equals(animalDTO.getSpecies()) || species.isEmpty()) {
                species = null; // No enviar la especie si es igual a la del AnimalDTO
            }
            if (size.equals(animalDTO.getSize()) || size.isEmpty()) {
                size = null; // No enviar el tamaño si es igual al del AnimalDTO
            }
            if (energyLevel.equals(animalDTO.getEnergyLevel()) || energyLevel.isEmpty()) {
                energyLevel = null; // No enviar el nivel de energía si es igual al del AnimalDTO
            }
            if (birthDate.equals(animalDTO.getBirthDate()) || birthDate.isEmpty()) {
                birthDate = null; // No enviar la fecha de nacimiento si es igual a la del AnimalDTO o está vacía
            }
            if (health.equals(animalDTO.getHealth()) || health.isEmpty()) {
                health = null; // No enviar la salud si es igual a la del AnimalDTO o está vacía
            }
            if (description.equals(animalDTO.getDescription()) || description.isEmpty()) {
                description = null; // No enviar la descripción si es igual a la del AnimalDTO o está vacía
            }
        }

        // Realizar una llamada a la API para editar el animal
        if (animalId != null) {
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
                    .baseUrl(Constans.BASE_URL) // Reemplaza con la URL de tu API
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AnimalService animalService = retrofit.create(AnimalService.class);

            // Realizar la llamada para editar el animal
            Call<Void> call;
            if (photoPart != null) {
                call = animalService.editAnimal(animalId, name, gender, age, birthDate, species, size, energyLevel, health, description, photoPart);
            } else {
                call = animalService.editAnimal(animalId, name, gender, age, birthDate, species, size, energyLevel, health, description, null);
            }

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                        // La edición del animal fue exitosa
                        // Puedes manejar la respuesta de la API aquí, por ejemplo, mostrar un mensaje de éxito
                        Toast.makeText(requireContext(), "Animal editado con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        // La llamada a la API fue exitosa pero ocurrió un error al editar el animal
                        // Puedes manejar el error aquí, por ejemplo, mostrar un mensaje de error
                        Toast.makeText(requireContext(), "Error al editar el animal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    // Ocurrió un error durante la llamada a la API
                    // Puedes manejar el error aquí, por ejemplo, mostrar un mensaje de error de conexión
                    Toast.makeText(requireContext(), "Fallo en la conexión", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Manejar el caso en el que no se pudo obtener el ID del animal
            Toast.makeText(requireContext(), "Error: ID del animal no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private void configureSpinnerAdapter(Spinner spinner, String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void showDatePickerDialog(EditText editText) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view, year, month, dayOfMonth) -> {
            // El usuario seleccionó una fecha, haz algo con ella aquí
            String selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month + 1, dayOfMonth);
            editText.setText(selectedDate);
        }, 2022, 0, 1); // El tercer parámetro (2022) es el año inicial, el cuarto (0) es el mes inicial, el quinto (1) es el día inicial
        datePickerDialog.show();
    }

    private byte[] getFileBytesFromUri(Uri uri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
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
