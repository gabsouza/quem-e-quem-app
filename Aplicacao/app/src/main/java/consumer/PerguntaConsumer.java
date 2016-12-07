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

       //Su
       // public static final String URL_BASE = "http://192.168.1.4:8080/ServidorQuem/rest/pergunta/";

      //Gab
       public static final String URL_BASE = "http://192.168.0.105:8080/ServidorQuem/rest/pergunta/";

        public PerguntaConsumer() {
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        }

        // FAZ UM GET RETORNANDO UM JSON
        public Pergunta chamaConsultarPorId(int id) {
            String URL = URL_BASE+id;
            Pergunta pergunta = restTemplate.getForObject(URL, Pergunta.class);
            return pergunta;
        }

    public List<Pergunta> chamaListar() {
        Pergunta[] vetorPergunta = restTemplate.getForObject(URL_BASE, Pergunta[].class);

        ArrayList<Pergunta> listaPergunta = new ArrayList<Pergunta>(Arrays.asList(vetorPergunta));

        return listaPergunta;
    }

}
