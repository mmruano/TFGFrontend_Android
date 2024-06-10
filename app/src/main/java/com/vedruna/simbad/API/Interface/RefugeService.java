package com.vedruna.simbad.API.Interface;

import com.vedruna.simbad.API.DTO.RefugeDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RefugeService {
    @GET("SimbadAPI/v1/Refuge/getRefuge/{refugeId}")
    Call<RefugeDTO> getRefuge(@Path("refugeId") Long refugeId);

    @PUT("SimbadAPI/v1/Refuge/editRefuge")
    Call<Void> editRefuge(@Query("name") String name,
                          @Query("typeRefuge") String typeRefuge,
                          @Query("cif") String cif,
                          @Query("province") String province,
                          @Query("street") String street,
                          @Query("codPost") String codPost,
                          @Query("phone") String phone);

    @DELETE("SimbadAPI/v1/Refuge/delete/{refugeId}")
    Call<Void> deleteRefuge(@Path("refugeId") Long refugeId);
}
