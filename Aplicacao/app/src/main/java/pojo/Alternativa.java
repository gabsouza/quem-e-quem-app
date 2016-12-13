package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Alternativa implements Serializable{

	private static final long serialVersionUID = -37767541869854404L;
	private int idAlternativa;
	private GeneroPersonagem generoPersonagem;
	private String descricao;
	private Pergunta pergunta;

	private List<Resposta> respostas = new ArrayList<Resposta>();
		
	public Alternativa(GeneroPersonagem generoPersonagem, String descricao){
		this.generoPersonagem = generoPersonagem;
		this.descricao = descricao;
	}
	
	public Alternativa(){
		
	}

	public int getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(int idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

	public GeneroPersonagem getGeneroPersonagem() {
		return generoPersonagem;
	}

	public void setGeneroPersonagem(GeneroPersonagem generoPersonagem) {
		this.generoPersonagem = generoPersonagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
}
