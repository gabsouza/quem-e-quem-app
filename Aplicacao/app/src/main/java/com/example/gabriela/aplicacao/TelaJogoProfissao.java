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

    private List<Alternativa> alternativasCorretas, alternativasIncorretas, alternativasPorIdPergunta;

    private List<List<Alternativa>> alternativas;

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
                try {
                obterPersonagensAleatorios();
                obterPerguntasAleatorias();

                // BUSCA AS ALTERNATIVAS
                    new HttpRequestTaskAlternativaPorIdPergunta().execute();
                    new HttpRequestTaskAlternativasIncorretas().execute();
                obterAlternativasCorretasAleatorias();
                } catch(Exception e) {
                    e.printStackTrace();
                }
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

        ArrayList<Alternativa> alternativasMescladas = new ArrayList<>(alternativasCorretas);
        alternativasMescladas.addAll(alternativasIncorretas);

        Random ran1 = new Random();
        Random ran2 = new Random();
        Random ran3 = new Random();
        Random ran4 = new Random();
        Random ran5 = new Random();

        int tam = alternativasMescladas.size();

       if (tam > 0) {
           int ranNum = ran1.nextInt(tam);
           opcao1.setText(alternativasMescladas.get(ranNum).getDescricao());
           alternativasMescladas.remove(ranNum);

           int ranNum2 = ran2.nextInt(tam);
           opcao2.setText(alternativasMescladas.get(ranNum2).getDescricao());
           alternativasMescladas.remove(ranNum2);

           int ranNum3 = ran3.nextInt(tam);
           opcao3.setText(alternativasMescladas.get(ranNum3).getDescricao());
           alternativasMescladas.remove(ranNum3);

           int ranNum4 = ran4.nextInt(tam);
           opcao4.setText(alternativasMescladas.get(ranNum4).getDescricao());
           alternativasMescladas.remove(ranNum4);

           int ranNum5 = ran5.nextInt(tam);
           opcao5.setText(alternativasMescladas.get(ranNum5).getDescricao());
           alternativasMescladas.remove(ranNum5);
        }
        else{
           Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
        }
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

        alternativas = new ArrayList<>();
        alternativasCorretas = new ArrayList<>();
        alternativasIncorretas = new ArrayList<>();

        alternativasPorIdPergunta = new ArrayList<>();
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
        }
    }

//    private class HttpRequestTaskAlternativa extends AsyncTask<Void, Void, List<Alternativa>> {
//
//        @Override
//        protected List<Alternativa> doInBackground(Void... params) {
//            alternativasCorretas = alternativaConsumer.chamaListarTodas();
//            return alternativasCorretas;
//        }
//
//        // é executado quando o webservice retorna
//        @Override
//        protected void onPostExecute(List<Alternativa> alts) {
//            super.onPostExecute(alternativasCorretas);
//            alternativasCorretas = alts;
//        }
//    }

    private class HttpRequestTaskAlternativaPorIdPergunta extends AsyncTask<Void, Void, List<Alternativa>> {

        @Override
        protected List<Alternativa> doInBackground(Void... params) {
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

    private class HttpRequestTaskAlternativasIncorretas extends AsyncTask<Void, Void, List<Alternativa>> {

        @Override
        protected List<Alternativa> doInBackground(Void... params) {
            if (alternativasCorretas.size() == 1) {
                alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretas(alternativasCorretas.get(0).getIdAlternativa(), 0, 4);
            } else {
            }
            if (alternativasCorretas.size() == 2) {
                alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretas(alternativasCorretas.get(0).getIdAlternativa(),
                        alternativasCorretas.get(1).getIdAlternativa(), 3);
            } else {
            }
            return alternativasIncorretas;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(List<Alternativa> alts) {
            super.onPostExecute(alternativasIncorretas);
            alternativasIncorretas = alts;
        }
    }
}
