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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import consumer.PerguntaConsumer;
import pojo.Perfil;
import pojo.Pergunta;

/**
 * Created by Gabriela on 18/11/2016.
 */
public class TelaJogoProfissao extends Activity {

    private ImageView ivPersonagem;
    private Button btPassar;
    private TextView tvPergunta;
    private Button opcao1, opcao2, opcao3, opcao4, opcao5;
    private PerguntaConsumer perguntaConsumer;
    private Pergunta pergunta;
    private List<Pergunta> perguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_profissao);
        inicializaComponentes();

        new HttpRequestTask().execute();

        btPassar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterPersonagensAleatorios();
                obterPerguntasAleatorias();
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

        if (perguntas.size() >0){

        int ranNum = ran.nextInt(this.perguntas.size());
        tvPergunta.setText(perguntas.get(ranNum).getDescricao());
        perguntas.remove(ranNum);

        }else{
            chamaTelaResultado();
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
        pergunta = new Pergunta();
        perguntas = new ArrayList<Pergunta>();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, List<Pergunta>> {

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
}
