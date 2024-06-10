package com.vedruna.simbad.API.Security;

import com.vedruna.simbad.API.Model.Refuge;
import com.vedruna.simbad.API.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("SimbadAPI/v1/auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    @POST("SimbadAPI/v1/auth/createUser")
    Call<AuthResponse> createUser(@Body User user);

    @POST("SimbadAPI/v1/auth/createRefuge")
    Call<AuthResponse> createRefuge(@Body Refuge request);
}
