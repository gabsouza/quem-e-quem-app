package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import consumer.PerguntaConsumer;
import pojo.Perfil;
import pojo.Pergunta;

/**
 * Created by Gabriela on 18/11/2016.
 */
public class TelaJogoProfissao extends Activity{

    private ImageView ivPersonagem;
    private Button btPassar;
    private TextView tvPergunta;
    private Button opcao1, opcao2, opcao3, opcao4, opcao5;
    private PerguntaConsumer perguntaConsumer;
    private Pergunta pergunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_profissao);
        inicializaComponentes();

        btPassar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] cards = {R.drawable.personagem1, R.drawable.personagem2, R.drawable.personagem3, R.drawable.personagem4, R.drawable.personagem5, R.drawable.personagem6, R.drawable.personagem7, R.drawable.personagem8, R.drawable.personagem9};
                Random r = new Random();
                int n = r.nextInt(7);
                ivPersonagem.setImageResource(cards[n]);
            }
        });

        tvPergunta.setText(perguntaConsumer.chamaConsultarPorId(1).getDescricao());
    }


    private class HttpRequestTask extends AsyncTask<Pergunta, Void, Pergunta> {

        // EXECUTA A TAREFA QUE DEVE SER REALIZADA

        @Override
        protected Pergunta doInBackground(Pergunta... params) {
            Log.i("DEBUG", (params[0].getDescricao()));
            params[0] = perguntaConsumer.chamaConsultarPorId(1);
            Log.i("DEBUG",params[0].getDescricao());
            return params[0];
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(Pergunta pergunta) {
            super.onPostExecute(pergunta);
            Log.i("DEBUG",pergunta.getDescricao());

        }
    }

    public void inicializaComponentes(){
        ivPersonagem = (ImageView) findViewById(R.id.iv_personagem);
        btPassar = (Button) findViewById(R.id.bt_randon);
        tvPergunta = (TextView) findViewById(R.id.tv_pergunta);
        opcao1 = (Button) findViewById(R.id.tv_opcao1);
        opcao2 = (Button) findViewById(R.id.tv_opcao2);
        opcao3 = (Button) findViewById(R.id.tv_opcao3);
        opcao4 = (Button) findViewById(R.id.tv_opcao4);
        opcao5 = (Button) findViewById(R.id.tv_opcao5);
        perguntaConsumer = new PerguntaConsumer();
        pergunta = new Pergunta();
    }
}
