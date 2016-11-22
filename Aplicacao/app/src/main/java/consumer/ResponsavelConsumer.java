package consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import pojo.Responsavel;

/**
 * Created by Gabriela on 14/10/2016.
 */

public class ResponsavelConsumer {

    RestTemplate restTemplate;
    public static final String URL_BASE = "http://192.168.0.105/ServidorQuem/rest/responsavel/";

    public ResponsavelConsumer() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    // FAZ UM GET RETORNANDO UM JSON
    public Responsavel chamaConsultarPorId(int id) {
        String URL = URL_BASE+"/"+id;
        Responsavel responsavel = restTemplate.getForObject(URL, Responsavel.class);
        return responsavel;
    }

    // FAZER UM POST ENVIANDO UM JSON
    public Responsavel chamaCadastrar(Responsavel responsavel) {
        ResponseEntity<Responsavel> response = restTemplate.postForEntity(URL_BASE, responsavel, Responsavel.class);
        responsavel = response.getBody();
        return responsavel;
    }

//    public void chamaDeletar(int id){
//        String URL = URL_BASE + "{id}";
//        Map map = new HashMap();
//        map.put("id", id);
//        restTemplate.delete(URL, map);
//    }
}