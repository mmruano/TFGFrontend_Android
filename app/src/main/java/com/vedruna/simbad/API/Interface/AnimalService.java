package com.vedruna.simbad.API.Interface;

import com.vedruna.simbad.API.DTO.AnimalDTO;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnimalService {
    @GET("SimbadAPI/v1/Animal/getAnimal/{animalId}")
    Call<AnimalDTO> getAnimal(@Path("animalId") Long animalId);

    @GET("SimbadAPI/v1/Animal/getAnimals")
    Call<List<AnimalDTO>> getAnimals(
            @Query("species") String species,
            @Query("size") String size,
            @Query("energyLevel") String energyLevel,
            @Query("province") String province
    );

    @GET("SimbadAPI/v1/Animal/getAnimalsByRefugeId")
    Call<List<AnimalDTO>> getAnimalsByRefugeId();

    @Multipart
    @POST("SimbadAPI/v1/Animal/createAnimal")
    Call<Void> createAnimal(
            @Part("name") String name,
            @Part("gender") String gender,
            @Part("age") String age,
            @Part("birthDate") String birthDate,
            @Part("species") String species,
            @Part("size") String size,
            @Part("energyLevel") String energyLevel,
            @Part("health") String health,
            @Part("description") String description,
            @Part MultipartBody.Part photo
    );

    @Multipart
    @PUT("SimbadAPI/v1/Animal/editAnimal/{animalId}")
    Call<Void> editAnimal(
            @Path("animalId") Long animalId,
            @Part("name") String name,
            @Part("gender") String gender,
            @Part("age") String age,
            @Part("birthDate") String birthDate,
            @Part("species") String species,
            @Part("size") String size,
            @Part("energyLevel") String energyLevel,
            @Part("health") String health,
            @Part("description") String description,
            @Part MultipartBody.Part photo
    );

    @DELETE("SimbadAPI/v1/Animal/delete/{animalId}")
    Call<Void> deleteAnimal(@Path("animalId") Long animalId);
}
