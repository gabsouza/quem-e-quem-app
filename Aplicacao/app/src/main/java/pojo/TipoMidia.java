package pojo;

/**
 * Created by Gabriela on 19/09/2016.
 */
public enum TipoMidia {

    AUDIO("Audio"),
    FIGURA("Figura"),
    VIDEO("Video"),
    PERSONAGEM("Character");

    private String code;

    private TipoMidia(String code){
        this.code = code;
    }

    public String getTipoMidia(){
        return this.code;
    }
}
