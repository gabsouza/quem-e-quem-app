package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MiniJogo implements Serializable {

	private static final long serialVersionUID = 3547848248102446726L;

	private int idMiniJogo;
	private String nomeMiniJogo;
	private String introducao;
	private int foto;
	private List<Midia> midias = new ArrayList<Midia>();
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
	private List<UsuarioMiniJogo> usuariosMiniJogos = new ArrayList<UsuarioMiniJogo>();

	public MiniJogo(){}
	
	public MiniJogo(String nomeMiniJogo, String introducao, int foto){
		this.nomeMiniJogo = nomeMiniJogo;
		this.introducao = introducao;
		this.foto = foto;
	}
	
	public MiniJogo(int idMiniJogo, String nomeMiniJogo, int photo, String introducao){
		
	}

	public int getPhoto(){
		return foto;
	}

	public void setFoto(int foto){
		this.foto = foto;
	}

	public int getIdMiniJogo() {
		return idMiniJogo;
	}

	public void setIdMiniJogo(int idMiniJogo) {
		this.idMiniJogo = idMiniJogo;
	}

	public String getNomeMiniJogo() {
		return nomeMiniJogo;
	}

	public void setNomeMiniJogo(String nomeMiniJogo) {
		this.nomeMiniJogo = nomeMiniJogo;
	}

	public String getIntroducao() {
		return introducao;
	}

	public void setIntroducao(String introducao) {
		this.introducao = introducao;
	}

	public List<Midia> getMidias() {
		return midias;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	public List<UsuarioMiniJogo> getUsuariosMiniJogos() {
		return usuariosMiniJogos;
	}

	public void setUsuariosMiniJogos(List<UsuarioMiniJogo> usuariosMiniJogos) {
		this.usuariosMiniJogos = usuariosMiniJogos;
	}
}
