package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pojo.*;

public class Pergunta implements Serializable{

	private static final long serialVersionUID = 4886271165487014032L;
	private int idPergunta;
	private int quantTentativas;
	private String descricao;
	private List<Midia> midias = new ArrayList<Midia>();
	private List<Resposta> respostas = new ArrayList<Resposta>();
	private List<Dica> dicas = new ArrayList<Dica>();
	private pojo.MiniJogo miniJogo;
	
	public Pergunta(int quantTentativas, String descricao){
		this.quantTentativas = quantTentativas;
		this.descricao = descricao;
	}
	
	public Pergunta(){
		
	}

	public int getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}

	public int getQuantTentativas() {
		return quantTentativas;
	}

	public void setQuantTentativas(int quantTentativas) {
		this.quantTentativas = quantTentativas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Midia> getMidias() {
		return midias;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public List<Dica> getDicas() {
		return dicas;
	}

	public void setDicas(List<Dica> dicas) {
		this.dicas = dicas;
	}

	public pojo.MiniJogo getMiniJogo() {
		return miniJogo;
	}

	public void setMiniJogo(pojo.MiniJogo miniJogo) {
		this.miniJogo = miniJogo;
	}
}
