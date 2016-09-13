package com.example.gabriela.exemploconsumorest4i;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity{

    private TextView tvId, tvContent;

    public static final String URL = "http://rest-service.guides.spring.io/greeting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();
        new HttpTask().execute();
    }

    private void inicializaComponentes (){
        this.tvId = (TextView) findViewById(R.id.tv_id);
        this.tvContent = (TextView) findViewById(R.id.tv_content);
    }
    private class HttpTask extends AsyncTask<Void, Void, Greeting> {

        // EXECUTA A TAREFA QUE DEVE SER REALIZADA
        @Override
        protected Greeting doInBackground(Void... voids) {

            Greeting greeting =  null;

            //implementando a requisição
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                greeting = restTemplate.getForObject(URL, Greeting.class);

            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(Greeting greeting) {
            super.onPostExecute(greeting);
            tvId.setText(greeting.getId());
            tvContent.setText(greeting.getContent());

        }
    }
}
