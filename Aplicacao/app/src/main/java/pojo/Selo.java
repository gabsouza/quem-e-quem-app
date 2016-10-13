package pojo;

import java.io.Serializable;
import java.util.Date;

public class Selo implements Serializable{

	private static final long serialVersionUID = 892980676576219329L;
	private int idSelo;
	private String nomeSelo;
	private Date dataHora;
	private Midia midia;
	
	public Selo(){
		
	}

	public int getIdSelo() {
		return idSelo;
	}

	public void setIdSelo(int idSelo) {
		this.idSelo = idSelo;
	}
	
	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public String getNomeSelo() {
		return nomeSelo;
	}

	public void setNomeSelo(String nomeSelo) {
		this.nomeSelo = nomeSelo;
	}
}
