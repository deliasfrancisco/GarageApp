package com.example.garage.Service;

import com.example.garage.Models.Oficina;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OficinaService {

    @GET("oficinabusca?q={pesquisa}")
    Call<List<Oficina>> PesquisaOficina(@Path("pesquisa") String pesquisa);

    @GET("oficinaservicos/{id}")
    Call<List<Oficina>> OficinaCategoria(@Path("id") int id);

    @GET("oficina")
    Call<List<Oficina>> listaOficina();
}
