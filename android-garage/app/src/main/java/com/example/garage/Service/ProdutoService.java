package com.example.garage.Service;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import com.example.garage.Models.Produto;

public interface ProdutoService {

    @GET("produto")
    Call<List<Produto>> getProdutos();

}
