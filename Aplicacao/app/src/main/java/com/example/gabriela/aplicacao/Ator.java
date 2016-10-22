package com.example.claquete;

public class Ator {
    private int id;
    private String nome;
    private String imagem;

    public Ator(int id, String nome, String imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }
}
