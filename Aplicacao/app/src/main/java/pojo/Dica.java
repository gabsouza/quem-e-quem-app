package pojo;

import com.example.gabriela.aplicacao.Midia;
import com.example.gabriela.aplicacao.Pergunta;

import java.io.Serializable;

public class Dica implements Serializable{

	private static final long serialVersionUID = -8281546625370983369L;
	private int idDica;
	private Midia midia;
	private Pergunta pergunta;
	
	// Dois construtores sem nada?

	public Dica(){
		
	}

	public int getIdDica() {
		return idDica;
	}

	public void setIdDica(int idDica) {
		this.idDica = idDica;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
		
}
