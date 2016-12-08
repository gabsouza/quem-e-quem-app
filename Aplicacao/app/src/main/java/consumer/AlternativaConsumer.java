package consumer;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.Alternativa;

/**
 * Created by PC-CASA on 08/12/2016.
 */

public class AlternativaConsumer {

    RestTemplate restTemplate;

    //Su
    // public static final String URL_BASE = "http://192.168.1.4:8080/ServidorQuem/rest/alternativa/";

    //Gab
    public static final String URL_BASE = "http://192.168.0.105:8080/ServidorQuem/rest/alternativa/";

    public AlternativaConsumer() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    // FAZ UM GET RETORNANDO UM JSON
    public Alternativa chamaConsultarPorId(int id) {
        String URL = URL_BASE+id;
        Alternativa alternativa = restTemplate.getForObject(URL, Alternativa.class);
        return alternativa;
    }

    public List<Alternativa> chamaListar(int idPergunta) {

        String URL = URL_BASE+"pergunta/"+idPergunta;

        Alternativa[] vetorAlternativa = restTemplate.getForObject(URL_BASE, Alternativa[].class);

        ArrayList<Alternativa> listaAlternativa = new ArrayList<Alternativa>(Arrays.asList(vetorAlternativa));

        return listaAlternativa;
    }
}
