package com.vedruna.simbad.API.Interface;

import com.vedruna.simbad.API.DTO.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("SimbadAPI/v1/User/getUser/{userId}")
    Call<UserDTO> getUser(@Path("userId") Long userId);

    @GET("SimbadAPI/v1/User/getUserInfoByToken")
    Call<UserDTO> getUserInfoByToken();

    @PUT("SimbadAPI/v1/User/editUser")
    Call<Void> editUser(@Query("name") String name,
                        @Query("surname") String surname,
                        @Query("gender") String gender,
                        @Query("province") String province,
                        @Query("codPost") String codPost,
                        @Query("phone") String phone);

    @DELETE("SimbadAPI/v1/User/delete/{userId}")
    Call<Void> deleteUser(@Path("userId") Long userId);
}
