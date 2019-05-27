package com.example.garage.Service;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import com.example.garage.Models.Usuario;

public interface UsuarioService {

    @GET("usuario")
    Call<List<Usuario>> getUsuarios();

    @Headers("Content-Type: application/json")
    @POST("usuario")
    Call<String> inserirUsuario(@Body String content);
}
