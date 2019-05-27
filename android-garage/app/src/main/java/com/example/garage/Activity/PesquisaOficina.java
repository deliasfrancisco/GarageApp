package com.example.garage.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.garage.R;

public class PesquisaOficina extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_oficina);

        TextView textView = (TextView) findViewById(R.id.txtOficinaUnica);

        /*Bundle bundleEnd = getIntent().getExtras();
        String pesquisa = bundleEnd.getString("Pesquisa");*/

        Bundle bundleId = getIntent().getExtras();
        String id = bundleId.getString("id");

        textView.setText(id);

        /*// API - OFICINA

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OficinaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OficinaService service = retrofit.create(OficinaService.class);
        Call<List<Oficina>> requestOficina = service.PesquisaOficina(id);

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
        });*/



    }


    /*public void montarOficinas(List<Oficina> oficina) {

        List<Oficina> listData = new ArrayList<>();
        for (Oficina o : oficina) {
            listData.add(o);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recPesquisaOficina);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerOficina adapter = new RecyclerOficina(this, listData);
        recyclerView.setAdapter(adapter);

    }*/

}
