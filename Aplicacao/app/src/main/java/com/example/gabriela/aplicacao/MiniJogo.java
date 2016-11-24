package com.example.gabriela.aplicacao;

/**
 * Created by PC-CASA on 23/11/2016.
 */

public class MiniJogo {

    private String nomeMiniJogo;
    private String descricao;
    private int imagem;

    public MiniJogo(){

    }

    public MiniJogo(String nomeMiniJogo, String descricao, int imagem){
        this.nomeMiniJogo = nomeMiniJogo;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public String getNomeMiniJogo() {
        return nomeMiniJogo;
    }

    public void setNomeMiniJogo(String nomeMiniJogo) {
        this.nomeMiniJogo = nomeMiniJogo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
