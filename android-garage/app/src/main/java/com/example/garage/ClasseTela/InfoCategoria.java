package com.example.garage.ClasseTela;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.garage.R;

public class InfoCategoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_categoria);

        Bundle bundle = getIntent().getExtras();
        String nome = bundle.getString("nome");
        TextView textView = (TextView) findViewById(R.id.txtNomeCat);
        textView.setText(nome);

    }
}
