package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pergunta implements Serializable {

	private static final long serialVersionUID = 4886271165487014032L;

	private int idPergunta;
	private int quantTentativas;
	private String descricao;
	private List<Midia> midias = new ArrayList<Midia>();
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();
	private List<Dica> dicas = new ArrayList<Dica>();
	private MiniJogo miniJogo;

	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UsuarioMiniJogo.class)
	//private List<UsuarioMiniJogo> usuario_mini_jogo = new ArrayList<UsuarioMiniJogo>();

//	@OneToMany(mappedBy = "pergunta", targetEntity = Resposta.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Resposta> respostas = new ArrayList<Resposta>();


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

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public List<Dica> getDicas() {
		return dicas;
	}

	public void setDicas(List<Dica> dicas) {
		this.dicas = dicas;
	}

	public MiniJogo getMiniJogo() {
		return miniJogo;
	}

	public void setMiniJogo(MiniJogo miniJogo) {
		this.miniJogo = miniJogo;
	}


}
