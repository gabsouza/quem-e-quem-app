package pojo;

public enum TipoMidia {
	
	AUDIO("Audio"),
	FIGURA("Figura"),
	VIDEO("Video"),
	PERSONAGEM("Personagem");
	
	private String code;
	
	private TipoMidia(String code){
		this.code = code;
	}
	
	public String getTipoMidia(){
		return this.code;
	}

}
