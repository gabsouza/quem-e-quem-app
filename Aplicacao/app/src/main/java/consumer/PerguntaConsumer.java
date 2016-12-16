package consumer;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.Pergunta;

/**
 * Created by PC-CASA on 27/11/2016.
 */

public class PerguntaConsumer {

    RestTemplate restTemplate;

<<<<<<< HEAD
       public static final String URL_BASE = "http://192.168.0.105:8080/ServidorQuem/rest/pergunta/";
=======
       public static final String URL_BASE = "http://192.168.0.108:8080/ServidorQuem/rest/pergunta/";
>>>>>>> 5d31cab9b073007e407961ccfbbc1075d0a00e63

    public PerguntaConsumer() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    // FAZ UM GET RETORNANDO UM JSON
    public Pergunta chamaConsultarPorId(int id) {
        String URL = URL_BASE + id;
        Pergunta pergunta = restTemplate.getForObject(URL, Pergunta.class);
        return pergunta;
    }

    public List<Pergunta> chamaListar(int idMiniJogo) {
        try {

            String URL = URL_BASE + "miniJogo/" + idMiniJogo;

            Pergunta[] vetorPergunta = restTemplate.getForObject(URL, Pergunta[].class);

            List<Pergunta> listaPergunta = new ArrayList<>(Arrays.asList(vetorPergunta));

            return listaPergunta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
