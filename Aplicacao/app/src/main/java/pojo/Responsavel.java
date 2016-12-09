package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Responsavel implements Serializable{

	private static final long serialVersionUID = -6118938763597816669L;
	private int idResponsavel;
	private String nomeResponsavel;
	private String emailResponsavel;
	private List<Perfil> perfis = new ArrayList<Perfil>();
	private List<Relatorio> relatorios = new ArrayList<Relatorio>();
	
	public Responsavel(String nomeResponsavel, String emailResponsavel){
		this.nomeResponsavel = nomeResponsavel;
		this.emailResponsavel = emailResponsavel;
	}
	
	public Responsavel(){
		
	}

	public int getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(int idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<Relatorio> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}


}
