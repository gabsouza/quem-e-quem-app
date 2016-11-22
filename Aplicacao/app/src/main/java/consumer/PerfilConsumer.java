package consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import pojo.Perfil;

/**
 * Created by Gabriela on 29/09/2016.
 */
public class PerfilConsumer {

    RestTemplate restTemplate;
    public static final String URL_BASE = "http:// 192.168.1.6:8080/ServidorQuem/rest/perfil/";

    public PerfilConsumer() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    // FAZ UM GET RETORNANDO UM JSON
    public Perfil chamaConsultarPorId(int id) {
        String URL = URL_BASE+"/"+id;
        Perfil perfil = restTemplate.getForObject(URL, Perfil.class);
        return perfil;
    }

    // FAZER UM POST ENVIANDO UM JSON
    public Perfil chamaCadastrar(Perfil perfil) {
        ResponseEntity<Perfil> response = restTemplate.postForEntity(URL_BASE, perfil, Perfil.class);
        perfil = response.getBody();
        return perfil;
    }

//    public void chamaDeletar(int id){
//        String URL = URL_BASE + "{id}";
//        Map map = new HashMap();
//        map.put("id", id);
//        restTemplate.delete(URL, map);
//    }
}
