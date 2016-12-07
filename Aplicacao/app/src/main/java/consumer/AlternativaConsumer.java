package consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import pojo.Alternativa;

/**
 * Created by Gabriela on 07/12/2016.
 */
public class AlternativaConsumer {

    RestTemplate restTemplate;
    public static final String URL_BASE = "http://192.168.0.105:8080/ServidorQuem/rest/alternativa/";

    public AlternativaConsumer() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    // FAZ UM GET RETORNANDO UM JSON
    public Alternativa chamaConsultarPorId(int id) {
        String URL = URL_BASE+"/"+id;
        Alternativa alternativa = restTemplate.getForObject(URL, Alternativa.class);
        return alternativa;
    }

    // FAZER UM POST ENVIANDO UM JSON
    public Alternativa chamaCadastrar(Alternativa alternativa) {
        ResponseEntity<Alternativa> response = restTemplate.postForEntity(URL_BASE, alternativa, Alternativa.class);
        alternativa = response.getBody();
        return alternativa;
    }
}
