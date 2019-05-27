package com.example.garage.Models;

public class Servicos {

    private String descricao;
    private String nome;

    public String getDescricao() { return descricao;}

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getNome() { return nome;}

    public void setNome(String nome) { this.nome = nome;}

    public void Servicos(){

    }

    public void Servicos(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }
}
