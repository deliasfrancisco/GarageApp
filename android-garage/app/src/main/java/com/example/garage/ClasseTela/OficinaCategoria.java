package com.example.garage.ClasseTela;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.garage.Adapter.RecyclerOficina;
import com.example.garage.Service.OficinaService;
import com.example.garage.Models.Oficina;
import com.example.garage.R;
import com.example.garage.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class OficinaCategoria extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficina_categoria);

        Bundle bundleId = getIntent().getExtras();
        int id = bundleId.getInt("id");

        Bundle bundleNome = getIntent().getExtras();
        String nome = bundleNome.getString("nome");
        TextView txtNomeOficinaCategoria = (TextView) findViewById(R.id.txtNomeOficinaCategoria);
        txtNomeOficinaCategoria.setText(nome);

        // API - OFICINA

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OficinaService service = retrofit.create(OficinaService.class);
        Call<List<Oficina>> requestOficina = service.OficinaCategoria(id);

        requestOficina.enqueue(new Callback<List<Oficina>>() {
            @Override
            public void onResponse(Call<List<Oficina>> call, Response<List<Oficina>> response) {
                if(!response.isSuccessful()) {
                    Log.i(TAG, "===============================================");
                    Log.i("TAG", "Erropp"+response.code());
                } else {

                    List<Oficina> oficinas = response.body();
                    for (Oficina o : oficinas) {
                        Log.i("TAG", "RRRRRRRRRRRRRRRRRRRRRRRRRRRRR: "+o.nome);
                    }
                    montarOficinas(oficinas);
                }
            }
            @Override
            public void onFailure(Call<List<Oficina>> call, Throwable t) {
                Log.e(TAG, "Errojj" + t.getMessage());
            }
        });

    }

    public void montarOficinas(List<Oficina> oficina) {
        //recyclerView = (RecyclerView) findViewById(R.id.recOficina);

        List<Oficina> listData = new ArrayList<>();
        for (Oficina o : oficina) {
            listData.add(o);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recOficinaCategoria);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerOficina adapter = new RecyclerOficina(this, listData);
        recyclerView.setAdapter(adapter);

    }
}
