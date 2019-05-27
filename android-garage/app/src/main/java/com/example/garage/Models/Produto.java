package com.example.garage.Models;

public class Produto {

    private String nome;
    private String descricao;
    private double valor;
    private String imagem;
    private int quantidade;

    public Produto(){

    }

    public Produto(String nome, String descricao, double valor){
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produto(String nome, String descricao, double valor, int quantidade){
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome;}

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }

    public void setValor(double valor) { this.valor = valor; }

    public String getImagem() { return imagem; }

    public void setImagem(String imagem) { this.imagem = imagem; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "Nome: ".concat(this.nome)
                .concat("\nDescrição: ").concat(this.descricao)
                .concat("\nValor: ").concat(String.valueOf(this.valor))
                .concat("\nQuantidade").concat(String.valueOf(this.quantidade));
    }
}
