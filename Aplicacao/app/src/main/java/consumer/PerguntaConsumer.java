package consumer;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import pojo.Pergunta;

/**
 * Created by PC-CASA on 27/11/2016.
 */

public class PerguntaConsumer {

        RestTemplate restTemplate;
        public static final String URL_BASE = "http://192.168.1.6:8080/ServidorQuem/rest/pergunta/";

        public PerguntaConsumer() {
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        }

        // FAZ UM GET RETORNANDO UM JSON
        public Pergunta chamaConsultarPorId(int id) {
            String URL = URL_BASE+"/"+id;
            Pergunta pergunta = restTemplate.getForObject(URL, Pergunta.class);
            return pergunta;
        }
}
