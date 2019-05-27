package com.example.garage.Service;

import com.example.garage.Models.DefaultResponse;
import com.example.garage.Models.Login;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LoginService {

    @GET("usuario/login")
    Call<Login> logar();

    @DELETE("usuario/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);

}
