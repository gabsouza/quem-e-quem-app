package pojo;

import com.example.gabriela.aplicacao.Pergunta;
import com.example.gabriela.aplicacao.UsuarioMiniJogo;

import java.io.Serializable;

public class Resposta implements Serializable{

	private static final long serialVersionUID = -998993369085728865L;
	private int idResposta;
	private int pontuacao;
	private float evolucao;
	private Pergunta pergunta;
	private UsuarioMiniJogo usuarioMiniJogo;
	private Alternativa alternativa;
	
	public Resposta(int pontuacao, float evolucao){
		this.pontuacao = pontuacao;
		this.evolucao = evolucao;
	}
	
	public Resposta(){
		
	}

	public int getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public float getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(float evolucao) {
		this.evolucao = evolucao;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public UsuarioMiniJogo getUsuarioMiniJogo() {
		return usuarioMiniJogo;
	}

	public void setUsuarioMiniJogo(UsuarioMiniJogo usuarioMiniJogo) {
		this.usuarioMiniJogo = usuarioMiniJogo;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}
	
}
