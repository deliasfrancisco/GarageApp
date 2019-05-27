package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.garage.Activity.CadastroUsuario;
import com.example.garage.Activity.TelaPrincipal;
import com.example.garage.Models.Login;
import com.example.garage.Service.LoginService;
import com.example.garage.util.Constants;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogin;
    private Button btn_cadastrar_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edt_email);
        edtSenha = findViewById(R.id.edt_senha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btn_cadastrar_usuario = (Button) findViewById(R.id.btn_cadastrar_usuario);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerLogin();
                if(fazerLogin() == true){
                    startActivity(new Intent(com.example.garage.MainActivity.this, TelaPrincipal.class));
                    finish();
                }
            }
        });

        btn_cadastrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.garage.MainActivity.this, CadastroUsuario.class));
                finish();
            }
        });
    }

    public boolean fazerLogin() {

        boolean resposta;

        if (userLogin() == false) {
            resposta = false;
        }
        else {
            try {
                Login login = new Login();
                login.setEmail(edtEmail.getText().toString());
                login.setSenha(edtSenha.getText().toString());

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", login.getEmail());
                jsonObject.put("senha", login.getSenha());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LoginService loginService = retrofit.create(LoginService.class);
                Call<Login> requestLogin = loginService.logar();
                requestLogin.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if(!response.isSuccessful()){
                            Log.i(TAG, "===============================================");
                            Log.i("TAG", "Erro"+response.code());
                        }
                        else{
                            Login log = response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.e(TAG, "Erro" + t.getMessage());
                    }
                });
                Toast.makeText(this.getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                resposta = true;
            }
            catch (Exception jsEx) {
                Toast.makeText(this.getApplicationContext(), "Erro no login", Toast.LENGTH_SHORT).show();
                resposta =  false;
            }
        }
        return  resposta;
    }

    private boolean userLogin() {

        String email = edtEmail.getText().toString().trim();
        String password = edtSenha.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Informe um e-mail válido");
            edtEmail.requestFocus();
            return false;
        } else {
            if (password.isEmpty() || password.length() < 6) {
                edtSenha.setError("Informe a senha");
                edtSenha.requestFocus();
                return false;
            } else {
                return true;
            }
        }
    }
}
