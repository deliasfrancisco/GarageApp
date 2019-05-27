package com.example.garage.Models;

import com.google.gson.annotations.SerializedName;

public class Categoria {
    @SerializedName("id")
    public int id;
    @SerializedName("nome")
    public String nome;
    @SerializedName("image")
    public String image;
}
