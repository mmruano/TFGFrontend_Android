package com.vedruna.simbad;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AdoptionDTO;
import com.vedruna.simbad.API.Interface.AdoptionService;
import com.vedruna.simbad.ListAdapters.ListAdoptionsUserAdapter;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListAdoptionsUserFragment extends Fragment {

    private ListView adoptionListViewUser;

    public ListAdoptionsUserFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_adoptions_user, container, false);
        adoptionListViewUser = view.findViewById(R.id.adoptionAnimalListViewUser);
        getAdoptionsByUserId(); // Llamar al método para obtener las adopciones por usuario
        return view;
    }

    private void getAdoptionsByUserId() {
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

        // Crear una instancia de la interfaz AdoptionService
        AdoptionService adoptionService = retrofit.create(AdoptionService.class);

        // Hacer la llamada al servidor para obtener la lista de adopciones por usuario
        adoptionService.getAdoptionsByUserId().enqueue(new Callback<List<AdoptionDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<AdoptionDTO>> call, @NonNull Response<List<AdoptionDTO>> response) {
                if (response.isSuccessful()) {
                    List<AdoptionDTO> responseList = response.body();
                    if (responseList != null && !responseList.isEmpty()) {
                        // Llenar la lista de adopciones
                        ArrayList<AdoptionDTO> adoptionList = new ArrayList<>(responseList);
                        // Asignar el adaptador al ListView
                        ListAdoptionsUserAdapter adapter = new ListAdoptionsUserAdapter(getActivity(), adoptionList);
                        adoptionListViewUser.setAdapter(adapter);
                    } else {
                        // Mostrar un mensaje si no hay adopciones
                        Toast.makeText(getContext(), "No tienes adopciones", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Mostrar un mensaje si no se pudo obtener la lista de adopciones
                    Toast.makeText(getContext(), "No se pudo obtener la lista de adopciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<AdoptionDTO>> call, @NonNull Throwable t) {
                // Mostrar un mensaje en caso de error de red
                Toast.makeText(getContext(), "Error de red al obtener la lista de adopciones", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
