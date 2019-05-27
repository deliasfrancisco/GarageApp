package com.example.garage.ClasseTela;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.garage.R;

public class InfoOficina extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_oficina);

        Bundle bundle = getIntent().getExtras();
        String nome = bundle.getString("nome");
        TextView textView = (TextView) findViewById(R.id.txtNome);
        textView.setText(nome);

        Bundle bundleTel = getIntent().getExtras();
        String telefone = bundleTel.getString("telefone");
        TextView txttel = (TextView) findViewById(R.id.txtTelefoneO);
        txttel.setText(telefone);

        Bundle bundleEnd = getIntent().getExtras();
        String endereco = bundleEnd.getString("endereco");
        TextView txtEnd = (TextView) findViewById(R.id.txtEnderecoO);
        txtEnd.setText(endereco);

        Bundle bundleAber = getIntent().getExtras();
        String aberto = bundleAber.getString("aberto");
        TextView txtAber = (TextView) findViewById(R.id.txtAbertoO);
        txtAber.setText(aberto);

        Bundle bundleImage = getIntent().getExtras();
        byte imagem = bundleImage.getByte("imagem");
        ImageView imageView = (ImageView) findViewById(R.id.imgOficina);
        //imageView.setImageBitmap()

    }

}
