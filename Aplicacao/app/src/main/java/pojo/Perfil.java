package pojo;

/**
 * Created by Gabriela on 16/09/2016.
 */
public class Perfil {

    private int id;
    private String nome;
    private Responsavel responsavel;

    public Perfil(String nome){
        this.nome = nome;
    }

    public Perfil(){

        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}
