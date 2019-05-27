package com.example.garage.Activity;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.garage.Adapter.RecyclerCategoria;
import com.example.garage.Adapter.RecyclerOficina;
import com.example.garage.Service.CategoriaService;
import com.example.garage.Service.OficinaService;
import com.example.garage.Models.Categoria;
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

public class TelaPrincipal extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    List<Oficina> listData;
    List<Categoria> listDataCategoria;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    // REcycler Oficinas
    RecyclerView recyclerViewOficina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtPesquisar = (EditText) findViewById(R.id.txtPesquisar);
        Button button = (Button) findViewById(R.id.btnPesquisa);

        final String pesquisa = txtPesquisar.getText().toString();

        // API - OFICINA

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OficinaService service = retrofit.create(OficinaService.class);
        Call<List<Oficina>> requestOficina = service.listaOficina();

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

        // API - Categoria

        Retrofit r = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriaService cservice = retrofit.create(CategoriaService.class);
        Call<List<Categoria>> requestCategoria = cservice.listaCategorias();
        requestCategoria.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if(!response.isSuccessful()) {
                    Log.i(TAG, "===============================================");
                    Log.i("TAG", "Erropp"+response.code());
                } else {
                    List<Categoria> categorias = response.body();
                    Log.i("TAG", "Erropp"+response.body());

                    montarCategoria(categorias);
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.e(TAG, "Errojj" + t.getMessage());
            }
        });

        /*txtPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPesquisar.setText("");
            }
        });*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(TelaPrincipal.this, OficinaPesquisa.class);
                it.putExtra("Pesquisa", pesquisa);
                startActivity(it);
            }
        });

    }


    public void montarCategoria(List<Categoria> categoria) {

        List<Categoria> listDataCategoria = new ArrayList<>();
        for (Categoria c : categoria) {
            listDataCategoria.add(c);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recCategoria);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerCategoria adapter = new RecyclerCategoria(this, listDataCategoria);
        recyclerView.setAdapter(adapter);

    }

    public void montarOficinas(List<Oficina> oficina) {
        //recyclerView = (RecyclerView) findViewById(R.id.recOficina);

        List<Oficina> listData = new ArrayList<>();
        for (Oficina o : oficina) {
            listData.add(o);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recOficina);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerOficina adapter = new RecyclerOficina(this, listData);
        recyclerView.setAdapter(adapter);

    }

    /*public void ApagarCaixaTexto(){
        txtPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPesquisar.setText("");
            }
        });
    }*/
}
