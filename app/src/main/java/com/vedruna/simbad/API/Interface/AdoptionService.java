package com.vedruna.simbad.API.Interface;

import com.vedruna.simbad.API.DTO.AdoptionDTO;
import com.vedruna.simbad.API.Model.Adoption;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdoptionService {
    @GET("/SimbadAPI/v1/Adoption/getAdoption/{adoptionId}")
    Call<AdoptionDTO> getAdoptionById(@Path("adoptionId") Long adoptionId);

    @GET("/SimbadAPI/v1/Adoption/getAdoptionsByUserId")
    Call<List<AdoptionDTO>> getAdoptionsByUserId();
    @GET("/SimbadAPI/v1/Adoption/getAdoptionsByRefugeId")
    Call<List<AdoptionDTO>> getAdoptionsByRefugeId();

    @POST("/SimbadAPI/v1/Adoption/createAdoption/{animalId}")
    Call<Void> createAdoption(@Path("animalId") Long animalId);

    @PUT("/SimbadAPI/v1/Adoption/updateStatusAdoption/{adoptionId}")
    Call<Void> updateStatusAdoption(@Path("adoptionId") Long adoptionId);

    @PUT("/SimbadAPI/v1/Adoption/cancelStatusAdoption/{adoptionId}")
    Call<Void> cancelStatusAdoption(@Path("adoptionId") Long adoptionId);
}