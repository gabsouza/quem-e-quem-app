package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Perfil implements Serializable {

	private static final long serialVersionUID = -5858562772172889269L;
	private int idPerfil;
	private String nomePerfil;
	private Responsavel responsavel;
	private List<Selo> selos = new ArrayList<Selo>();
	private List<UsuarioMiniJogo> usuariosMiniJogos = new ArrayList<UsuarioMiniJogo>();
	private Midia midia;
	
	public Perfil(String nomePerfil){
		this.nomePerfil = nomePerfil;
	}
	
	public Perfil(){
		
	}

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Selo> getSelos() {
		return selos;
	}

	public void setSelos(List<Selo> selos) {
		this.selos = selos;
	}

	public List<UsuarioMiniJogo> getUsuariosMiniJogos() {
		return usuariosMiniJogos;
	}

	public void setUsuariosMiniJogos(List<UsuarioMiniJogo> usuariosMiniJogos) {
		this.usuariosMiniJogos = usuariosMiniJogos;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

}
