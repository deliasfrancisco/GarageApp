package com.example.garage.Models;

public class Usuario {

    private String email, nome, senha;

    public Usuario() {
    }

    public Usuario(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }


    public Usuario(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Nome: ".concat(this.nome).concat("\nE-mail: ").concat(
                this.email);
    }
}
