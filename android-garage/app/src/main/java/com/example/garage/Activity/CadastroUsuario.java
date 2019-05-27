package com.example.garage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.garage.MainActivity;
import com.example.garage.Models.Usuario;
import com.example.garage.Service.UsuarioService;
import com.example.garage.util.Constants;
import com.example.garage.util.Retorno;
import com.google.gson.Gson;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import com.example.garage.R;

public class CadastroUsuario extends AppCompatActivity {

    private Button btn_finalizar_cadastrar_usuario, btn_login;
    private EditText edtEmail, edtNome, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cadastro);

        btn_finalizar_cadastrar_usuario = (Button) findViewById(R.id.btn_cadastrar_usuario);
        btn_login = (Button) findViewById(R.id.btn_voltar_login);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtNome = (EditText) findViewById(R.id.edt_nome);
        edtSenha = (EditText) findViewById(R.id.edt_senha);

        edtNome.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        edtSenha.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        btn_finalizar_cadastrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirUsuario();
                if(inserirUsuario() == true){
                    startActivity(new Intent(CadastroUsuario.this, TelaPrincipal.class));
                    finish();
               }
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroUsuario.this, MainActivity.class));
                finish();
            }
        });

    }

    public boolean inserirUsuario() {
        if (loginUsuario() == false) {
            Toast.makeText(this.getApplicationContext(), "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            try {

                Usuario usuario = new Usuario();
                usuario.setEmail(edtEmail.getText().toString());
                usuario.setNome(edtNome.getText().toString());
                usuario.setSenha(edtSenha.getText().toString());

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", usuario.getEmail());
                jsonObject.put("nome", usuario.getNome());
                jsonObject.put("senha", usuario.getSenha());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UsuarioService usuarioService = retrofit.create(UsuarioService.class);
                Call<String> chamada = usuarioService.inserirUsuario(jsonObject.toString());

                chamada.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String strRetorno = response.body();
                        Gson gson = new Gson();
                        Retorno retorno = gson.fromJson(strRetorno, Retorno.class);
                        Toast.makeText(getApplicationContext(), retorno.getTxtRetorno(), Toast.LENGTH_SHORT).show();
                        edtEmail.setText(null);
                        edtNome.setText(null);
                        edtSenha.setText(null);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.getCause();
                    }
                });
                Toast.makeText(this.getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                return true;
            } catch (Exception jsEx) {
                Toast.makeText(this.getApplicationContext(), "Erro no cadastro", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
    private boolean loginUsuario() {
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();
        String nome = edtNome.getText().toString().trim();

        if (email.isEmpty()) {
            edtEmail.setError("Email requerido");
            edtEmail.requestFocus();
            return false;
        }
        else{
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.setError("Informe um endereço de e-mail valido");
                edtEmail.requestFocus();
                return false;
            }
            else{
                if (senha.isEmpty()) {
                    edtSenha.setError("Senha requerida");
                    edtSenha.requestFocus();
                    return false;
                }
                else{
                    if (senha.length() < 6) {
                        edtSenha.setError("A senha deve ter pelo menos 6 caracteres");
                        edtSenha.requestFocus();
                        return false;
                    }
                    else{
                        if (nome.isEmpty()) {
                            edtNome.setError("Nome requerido");
                            edtNome.requestFocus();
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }
            }
        }
    }
}
