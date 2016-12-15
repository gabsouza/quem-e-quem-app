package consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.Alternativa;
import pojo.Pergunta;

/**
 * Created by Gabriela on 07/12/2016.
 */
public class AlternativaConsumer {

    RestTemplate restTemplate;

    public static final String URL_BASE = "http://192.168.241.222:8080/ServidorQuem/rest/alternativa/";

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

    public List<Alternativa> chamalistarAlternativasPorIdPergunta(int idPergunta) {

        String URL = URL_BASE+"pergunta/"+idPergunta;

        Alternativa[] vetorAlternativa = restTemplate.getForObject(URL, Alternativa[].class);

        ArrayList<Alternativa> listaAlternativa = new ArrayList<Alternativa>(Arrays.asList(vetorAlternativa));

        return listaAlternativa;
    }

    public List<Alternativa> chamaListarTodas() {

        String URL = URL_BASE+"alternativas/";

        Alternativa[] vetorAlternativa = restTemplate.getForObject(URL, Alternativa[].class);

        ArrayList<Alternativa> listaAlternativa = new ArrayList<Alternativa>(Arrays.asList(vetorAlternativa));

        return listaAlternativa;
    }

//    public List<Alternativa> buscarAlternativasIncorretas(int idAlternativa1, int idAlternativa2, int numeroDeAlternativas) {
//
//        String URL = URL_BASE+ idAlternativa1 + "/" +idAlternativa2 + "/" + numeroDeAlternativas;
//
//        Alternativa[] vetorAlternativa = restTemplate.getForObject(URL, Alternativa[].class);
//
//        ArrayList<Alternativa> listaAlternativa = new ArrayList<Alternativa>(Arrays.asList(vetorAlternativa));
//
//        return listaAlternativa;
//    }

    public List<Alternativa> buscarAlternativasIncorretas(int idAlternativa1, int idAlternativa2, int numeroDeAlternativas, String generoPersonagem) {

        String URL = URL_BASE+ idAlternativa1 + "/" +idAlternativa2 + "/" + numeroDeAlternativas + "/" + generoPersonagem;

        Alternativa[] vetorAlternativa = restTemplate.getForObject(URL, Alternativa[].class);

        ArrayList<Alternativa> listaAlternativa = new ArrayList<Alternativa>(Arrays.asList(vetorAlternativa));

        return listaAlternativa;
    }
}
