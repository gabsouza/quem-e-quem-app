package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Relatorio implements Serializable{
	
	private static final long serialVersionUID = 3841517645962686311L;
	private int idRelatorio;
	private String informacoes;
	private Date dataHora;
	private List<MiniJogo> miniJogos = new ArrayList<MiniJogo>();
	
	public Relatorio(String informacoes){
		this.informacoes = informacoes;
	}
	
	public Relatorio(){
		
	}

	public int getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(int idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public List<MiniJogo> getMiniJogos() {
		return miniJogos;
	}

	public void setMiniJogos(List<MiniJogo> miniJogos) {
		this.miniJogos = miniJogos;
	}

}
