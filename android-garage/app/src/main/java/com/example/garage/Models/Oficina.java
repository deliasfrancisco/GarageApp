package com.example.garage.Models;

import com.google.gson.annotations.SerializedName;

public class Oficina {

    @SerializedName("id")
    public int id;
    @SerializedName("nome")
    public String nome;
    @SerializedName("telefone")
    public String telefone;
    @SerializedName("endereco")
    public String endereco;
    @SerializedName("horarioAbertura")
    public String horarioAbertura;
    public String cnpj;
    @SerializedName("horarioFechamento")
    public String horarioFechamento;
    @SerializedName("image")
    public String image;
    public int imaget;


}
