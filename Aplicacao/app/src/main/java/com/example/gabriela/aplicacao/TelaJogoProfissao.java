package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import consumer.AlternativaConsumer;
import consumer.PerguntaConsumer;
import pojo.Alternativa;
import pojo.Perfil;
import pojo.Pergunta;

import static android.R.id.list;

/**
 * Created by Gabriela on 18/11/2016.
 */
public class TelaJogoProfissao extends Activity {

    private ImageView ivPersonagem;
    private Button btPassar;
    private TextView tvPergunta;
    private Button opcao1, opcao2, opcao3, opcao4, opcao5;
    private PerguntaConsumer perguntaConsumer;
    private Pergunta perguntaAtual;
    private List<Pergunta> perguntas;

    private List<Alternativa> alternativasCorretas;
    private List<Alternativa> alternativasPorIdPergunta;
    private List<Alternativa> alternativasJogo;
    private Alternativa alternativa;
    private AlternativaConsumer alternativaConsumer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_profissao);
        inicializaComponentes();
        // BUSCA AS PERGUNTAS
        new HttpRequestTaskPergunta().execute();



        btPassar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterPersonagensAleatorios();
                obterPerguntasAleatorias();

                // BUSCA AS ALTERNATIVAS
                new HttpRequestTaskAlternativaPorIdPergunta().execute();
                obterAlternativasCorretasAleatorias();

            }
        });
    }

    public void obterPersonagensAleatorios(){
        int[] cards = {R.drawable.personagem1, R.drawable.personagem2, R.drawable.personagem3, R.drawable.personagem4, R.drawable.personagem5, R.drawable.personagem6, R.drawable.personagem7, R.drawable.personagem8, R.drawable.personagem9};
        Random r = new Random();
        int n = r.nextInt(9);
        ivPersonagem.setImageResource(cards[n]);
    }

    public void obterPerguntasAleatorias(){
        Random ran = new Random();

        if (perguntas.size() > 0){

             int ranNum = ran.nextInt(this.perguntas.size());

             perguntaAtual = perguntas.get(ranNum);
            Log.i("DEBUG",perguntaAtual.getDescricao());
             tvPergunta.setText(perguntaAtual.getDescricao());
             perguntas.remove(ranNum);

        }else{
            chamaTelaResultado();
        }
    }

    public void obterAlternativasCorretasAleatorias() {
        Log.i("DEBUG",alternativasCorretas.size()+" TAMANHO");
//        Random ran1 = new Random();
//        Random ran2 = new Random();
//        Random ran3 = new Random();
//        Random ran4 = new Random();
//        Random ran5 = new Random();
//
//        if (alternativasCorretas.size() > 0) {
//            int ranNum = ran1.nextInt(this.alternativasCorretas.size());
//            opcao1.setText(alternativasCorretas.get(ranNum).getDescricao());
//            alternativasCorretas.remove(ranNum);
//
//            int ranNum2 = ran2.nextInt(this.alternativasCorretas.size());
//            opcao2.setText(alternativasCorretas.get(ranNum2).getDescricao());
//            alternativasCorretas.remove(ranNum2);
//
//            int ranNum3 = ran3.nextInt(this.alternativasCorretas.size());
//            opcao3.setText(alternativasCorretas.get(ranNum3).getDescricao());
//            alternativasCorretas.remove(ranNum3);
//
//            int ranNum4 = ran4.nextInt(this.alternativasCorretas.size());
//            opcao4.setText(alternativasCorretas.get(ranNum4).getDescricao());
//            alternativasCorretas.remove(ranNum4);
//
//            int ranNum5 = ran5.nextInt(this.alternativasCorretas.size());
//            opcao5.setText(alternativasCorretas.get(ranNum5).getDescricao());
//            alternativasCorretas.remove(ranNum5);

//        }
//        else{
//            Toast.makeText(getApplicationContext(), "Não tem alternativas", Toast.LENGTH_SHORT).show();
//        }
    }

    public void chamaTelaResultado(){
        Intent itTelaResultado = new Intent(this, TelaResultado.class);
        startActivity(itTelaResultado);
        finish();
    }

    public void inicializaComponentes(){
        ivPersonagem = (ImageView) findViewById(R.id.iv_personagem);

        tvPergunta = (TextView) findViewById(R.id.tv_pergunta);

        btPassar = (Button) findViewById(R.id.bt_randon);
        opcao1 = (Button) findViewById(R.id.tv_opcao1);
        opcao2 = (Button) findViewById(R.id.tv_opcao2);
        opcao3 = (Button) findViewById(R.id.tv_opcao3);
        opcao4 = (Button) findViewById(R.id.tv_opcao4);
        opcao5 = (Button) findViewById(R.id.tv_opcao5);

        perguntaConsumer = new PerguntaConsumer();
        perguntas = new ArrayList<>();

        alternativaConsumer = new AlternativaConsumer();
        alternativa = new Alternativa();
        alternativasCorretas = new ArrayList<>();

        alternativasPorIdPergunta = new ArrayList<>();
        alternativasJogo = new ArrayList<>();
    }

    private class HttpRequestTaskPergunta extends AsyncTask<Void, Void, List<Pergunta>> {

        // EXECUTA A TAREFA QUE DEVE SER REALIZADA
        @Override
        protected List<Pergunta> doInBackground(Void... params) {
           perguntas = perguntaConsumer.chamaListar(1);
            return perguntas;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(List<Pergunta> pergs) {
            super.onPostExecute(perguntas);
            perguntas = pergs;
            //obterPerguntasAleatorias();
        }
    }

    private class HttpRequestTaskAlternativa extends AsyncTask<Void, Void, List<Alternativa>> {

        @Override
        protected List<Alternativa> doInBackground(Void... params) {
//            for (int i = 0; i < perguntas.size(); i++) {
            alternativasCorretas = alternativaConsumer.chamaListarTodas();
//            }
            return alternativasCorretas;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(List<Alternativa> alts) {
            super.onPostExecute(alternativasCorretas);
            alternativasCorretas = alts;
        }
    }

    private class HttpRequestTaskAlternativaPorIdPergunta extends AsyncTask<Void, Void, List<Alternativa>> {

        @Override
        protected List<Alternativa> doInBackground(Void... params) {
//            for (int i = 0; i < perguntas.size(); i++) {
//                alternativasPorIdPergunta = alternativaConsumer.chamaListar(perguntas.get(i).getIdPergunta());
//            }
            alternativasPorIdPergunta = alternativaConsumer.chamalistarAlternativasPorIdPergunta(perguntaAtual.getIdPergunta());
            return alternativasPorIdPergunta;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(List<Alternativa> alts) {
            super.onPostExecute(alternativasCorretas);
            alternativasCorretas = alts;
        }
    }
}
