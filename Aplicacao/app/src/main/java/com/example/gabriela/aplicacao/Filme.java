package com.example.gabriela.aplicacao;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Filme implements Serializable {
    private int id;
    private String titulo;
    private String resumo;
    private String lancadoEm;
    private String poster;
    private String tituloOriginal;

    public Filme(int id, String titulo, String resumo, String lancadoEm, String poster, String tituloOriginal) {
        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.lancadoEm = lancadoEm;
        this.poster = poster;
        this.tituloOriginal = tituloOriginal;
    }

    public static Filme createFromJSONObject(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        String title = jsonObject.getString("title");
        String overview = jsonObject.getString("overview");
        String releaseDate = jsonObject.getString("release_date");
        String posterPath = jsonObject.getString("poster_path");
        String originalTitle = jsonObject.getString("original_title");
        return new Filme(id, title, overview, releaseDate, posterPath, originalTitle);
    }

    public int getID() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo != null && resumo.length() > 0 ? resumo : "Não Preenchido";
    }

    public String getLancadoEm() {
        return lancadoEm;
    }

    public String getPoster() {
        return poster;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }
}
