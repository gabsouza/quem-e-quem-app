package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pojo.*;

public class UsuarioMiniJogo implements Serializable{

	private static final long serialVersionUID = 3705851221202931220L;
	private int idUsuarioMiniJogo;
	private List<Resposta> respostas = new ArrayList<Resposta>();
	private List<Perfil> perfis = new ArrayList<Perfil>();
	private List<pojo.MiniJogo> miniJogos = new ArrayList<pojo.MiniJogo>();
	
	public UsuarioMiniJogo(){
		
	}

	public int getIdUsuarioMiniJogo() {
		return idUsuarioMiniJogo;
	}

	public void setIdUsuarioMiniJogo(int idUsuarioMiniJogo) {
		this.idUsuarioMiniJogo = idUsuarioMiniJogo;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<pojo.MiniJogo> getMiniJogos() {
		return miniJogos;
	}

	public void setMiniJogos(List<pojo.MiniJogo> miniJogos) {
		this.miniJogos = miniJogos;
	}
	
}
