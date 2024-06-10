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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AdoptionDTO;
import com.vedruna.simbad.API.Interface.AdoptionService;
import com.vedruna.simbad.ListAdapters.ListAdoptionsRefugeAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListAdoptionsRefugeFragment extends Fragment {
    private ListView adoptionListViewRefuge;
    private ListAdoptionsRefugeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_adoptions_refuge, container, false);
        adoptionListViewRefuge = rootView.findViewById(R.id.adoptionAnimalListViewRefuge);
        adapter = new ListAdoptionsRefugeAdapter(getContext(), new ArrayList<>());
        adoptionListViewRefuge.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchAdoptionsFromServer();
    }

    private void fetchAdoptionsFromServer() {
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

        // Hacer la llamada al servidor para obtener las adopciones por el ID del refugio
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        AdoptionService adoptionService = retrofit.create(AdoptionService.class);
        Call<List<AdoptionDTO>> call = adoptionService.getAdoptionsByRefugeId();
        call.enqueue(new Callback<List<AdoptionDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<AdoptionDTO>> call, @NonNull Response<List<AdoptionDTO>> response) {
                if (response.isSuccessful()) {
                    List<AdoptionDTO> adoptions = response.body();
                    if (adoptions != null && !adoptions.isEmpty()) {
                        adapter.addAll(adoptions);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "No se encontraron adopciones", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error al obtener adopciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<AdoptionDTO>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error de red al obtener adopciones", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
