package com.example.gabriela.aplicacao;

import java.io.Serializable;

import pojo.TipoMidia;

public class Midia implements Serializable {

	private static final long serialVersionUID = -2212393770193958580L;
	private int idMidia;
	private TipoMidia tipoMidia;
	private String caminho;
	private String descricao;
	
	public Midia(TipoMidia tipoMidia, String caminho, String descricao){
		this.tipoMidia = tipoMidia;
		this.caminho = caminho;
		this.descricao = descricao;
	}
	
	public Midia(){
	
	}

	public int getIdMidia() {
		return idMidia;
	}

	public void setIdMidia(int idMidia) {
		this.idMidia = idMidia;
	}

	public TipoMidia getTipoMidia() {
		return tipoMidia;
	}

	public void setTipoMidia(TipoMidia tipoMidia) {
		this.tipoMidia = tipoMidia;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	}