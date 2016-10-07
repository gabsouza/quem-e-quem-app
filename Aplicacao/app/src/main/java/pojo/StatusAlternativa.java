package pojo;

public enum StatusAlternativa {
	
	ERRADA("Errada"),
	CORRETA("Correta");
	
	private String code;
	
	private StatusAlternativa(String code){
		this.code = code;
	}
	
	public String getStatusAlterntiva(){
		return this.code;
	}

}

