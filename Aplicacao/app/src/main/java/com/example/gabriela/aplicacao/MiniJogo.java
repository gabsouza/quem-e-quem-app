package com.example.gabriela.aplicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MiniJogo implements Serializable {

    private static final long serialVersionUID = 3547848248102446726L;

    private int idMiniJogo;
    private String nomeMiniJogo;
    private String introducao;
    private String photo;
    private List<Midia> midias = new ArrayList<Midia>();
    private List<Pergunta> perguntas = new ArrayList<Pergunta>();
    private List<UsuarioMiniJogo> usuariosMiniJogos = new ArrayList<UsuarioMiniJogo>();

    public MiniJogo(int idMiniJogo, String nomeMiniJogo, String introducao, String photo){
        this.idMiniJogo = idMiniJogo;
        this.nomeMiniJogo = nomeMiniJogo;
        this.introducao = introducao;
        this.photo = photo;
    }

    public MiniJogo(){

    }



    public String getPhoto(){
        return photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public int getIdMiniJogo() {
        return idMiniJogo;
    }

    public void setIdMiniJogo(int idMiniJogo) {
        this.idMiniJogo = idMiniJogo;
    }

    public String getNomeMiniJogo() {
        return nomeMiniJogo;
    }

    public void setNomeMiniJogo(String nomeMiniJogo) {
        this.nomeMiniJogo = nomeMiniJogo;
    }

    public String getIntroducao() {
        return introducao;
    }

    public void setIntroducao(String introducao) {
        this.introducao = introducao;
    }

    public List<Midia> getMidias() {
        return midias;
    }

    public void setMidias(List<Midia> midias) {
        this.midias = midias;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public List<UsuarioMiniJogo> getUsuariosMiniJogos() {
        return usuariosMiniJogos;
    }

    public void setUsuariosMiniJogos(List<UsuarioMiniJogo> usuariosMiniJogos) {
        this.usuariosMiniJogos = usuariosMiniJogos;
    }
}
