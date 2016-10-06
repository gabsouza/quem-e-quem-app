package consumer;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import pojo.Perfil;

/**
 * Created by Gabriela on 29/09/2016.
 */
public class PerfilConsumer {

    RestTemplate restTemplate = new RestTemplate();
    public static final String URL_BASE = "http://10.0.2.2:8080/ServeQeQ/rest/perfil/";

    public PerfilConsumer() {

    }

    // FAZ UM GET RETORNANDO UM JSON
    public Perfil chamaConsultarPorId(int id) {
        String URL = URL_BASE+"/"+id;
        Perfil perfil = restTemplate.getForObject(URL, Perfil.class);
        return perfil;
    }

    // FAZER UM POST ENVIANDO UM JSON
    public Perfil chamaCadastrar(Perfil perfil) {
        perfil = restTemplate.postForObject(URL_BASE, perfil, Perfil.class);
        return perfil;
    }

    public void chamaDeletar(int id){
        String URL = URL_BASE + "{id}";
        Map map = new HashMap();
        map.put("id", id);
        restTemplate.delete(URL, map);

    }

}
