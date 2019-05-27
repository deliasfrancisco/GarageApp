package com.example.garage.Service;

import com.example.garage.Models.Categoria;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriaService {

    @GET("categoria")
    Call<List<Categoria>> listaCategorias();
}
