package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabriela.aplicacao.Adapter.AlternativasAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import consumer.AlternativaConsumer;
import consumer.PerguntaConsumer;
import pojo.Alternativa;
import pojo.Pergunta;
import pojo.Resposta;

/**
 * Created by Gabriela on 18/11/2016.
 */
public class TelaJogoProfissao extends Activity {

    private TextToSpeech textToSpeech;
    private ImageView ivPersonagem;
    private TextView tvPergunta;
    private Button btPassar, btFalar, btFalar1, btFalar2, btFalar3, btFalar4, btFalar5;
    private Context context;

    private PerguntaConsumer perguntaConsumer;
    private Pergunta perguntaAtual;
    private List<Pergunta> perguntas;

    private AlternativaConsumer alternativaConsumer;
    private List<Alternativa> alternativasCorretas, alternativasIncorretas, alternativasPorIdPergunta;
    private ArrayList<Alternativa> alternativasMescladas;

    private Resposta resposta;

    RecyclerView recyclerView;
    AlternativasAdapter alternativasAdapter;


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
                    new HttpRequestTaskPergunta().execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        context = getApplicationContext();

        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.getDefault());
                }
            }
        });

        btFalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String falar = tvPergunta.getText().toString();

                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btFalar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String falar = alternativasAdapter.getItem(0).getDescricao().toString();
                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btFalar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String falar = alternativasAdapter.getItem(1).getDescricao().toString();
                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btFalar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String falar = alternativasAdapter.getItem(2).getDescricao().toString();
                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btFalar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String falar = alternativasAdapter.getItem(3).getDescricao().toString();
                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btFalar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String falar = alternativasAdapter.getItem(4).getDescricao().toString();
                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new
                        RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {

//                                Log.i("debug", "perguntaId" + alternativasMescladas.get(position).getPergunta().getIdPergunta());
//
//                                if(alternativasMescladas.get(position).getPergunta().getIdPergunta() == perguntaAtual.getIdPergunta()){
//                                    resposta.setPontuacao(100);
//                                    Toast.makeText(context, "acertou", Toast.LENGTH_LONG).show();
//                                } else{
//                                    Toast.makeText(context, "errou", Toast.LENGTH_LONG).show();
//                                }
                            }
                        })
        );
    }


    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    public void obterPersonagensAleatorios() {
        int[] cards = {R.drawable.personagem1, R.drawable.personagem2, R.drawable.personagem3, R.drawable.personagem4, R.drawable.personagem5, R.drawable.personagem6, R.drawable.personagem7, R.drawable.personagem8, R.drawable.personagem9};
        Random r = new Random();
        int n = r.nextInt(9);
        ivPersonagem.setImageResource(cards[n]);

//        if(cards[n] == R.drawable.personagem1 || cards[n] == R.drawable.personagem2 || cards[n] == R.drawable.personagem4 || cards[n] == R.drawable.personagem9){
//            //CHAMA ALTERNATIVAS TALS
//        }
    }

    public void obterPerguntasAleatorias() {
        Random ran = new Random();

        if (perguntas.size() > 0) {

            int ranNum = ran.nextInt(this.perguntas.size());

            perguntaAtual = perguntas.get(ranNum);
            Log.i("DEBUG", perguntaAtual.getDescricao());
            tvPergunta.setText(perguntaAtual.getDescricao());
            perguntas.remove(ranNum);
        } else {
            chamaTelaResultado();
        }
    }

    public void obterAlternativasCorretasAleatorias() {

        alternativasMescladas = new ArrayList<>(alternativasCorretas);
        alternativasMescladas.addAll(alternativasIncorretas);

        Collections.shuffle(alternativasMescladas, new Random());
    }

    public void chamaTelaResultado() {
        Intent itTelaResultado = new Intent(this, TelaResultado.class);
        startActivity(itTelaResultado);
        finish();
    }

    public void inicializarRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        alternativasAdapter = new AlternativasAdapter(alternativasMescladas, this);
        recyclerView.setAdapter(alternativasAdapter);
        alternativasAdapter.notifyDataSetChanged();
    }

    public void inicializaComponentes() {
        ivPersonagem = (ImageView) findViewById(R.id.iv_personagem);
        tvPergunta = (TextView) findViewById(R.id.tv_pergunta);

        btPassar = (Button) findViewById(R.id.bt_randon);

        btFalar = (Button) findViewById(R.id.bt_falar_pergunta);
        btFalar1 = (Button) findViewById(R.id.bt_falar_opcao1);
        btFalar2 = (Button) findViewById(R.id.bt_falar_opcao2);
        btFalar3 = (Button) findViewById(R.id.bt_falar_opcao3);
        btFalar4 = (Button) findViewById(R.id.bt_falar_opcao4);
        btFalar5 = (Button) findViewById(R.id.bt_falar_opcao5);

        perguntaConsumer = new PerguntaConsumer();
        perguntas = new ArrayList<>();

        alternativaConsumer = new AlternativaConsumer();

        alternativasCorretas = new ArrayList<>();
        alternativasIncorretas = new ArrayList<>();

        alternativasPorIdPergunta = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_alternativas);

        resposta = new Resposta();
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
            super.onPostExecute(pergs);
            perguntas = pergs;
            obterPersonagensAleatorios();
            obterPerguntasAleatorias();
            new HttpRequestTaskAlternativaPorIdPergunta().execute();
        }
    }

    private class HttpRequestTaskAlternativaPorIdPergunta extends AsyncTask<Void, Void, List<Alternativa>> {

        @Override
        protected List<Alternativa> doInBackground(Void... params) {
            if (perguntaAtual != null) {
                alternativasPorIdPergunta = alternativaConsumer.chamalistarAlternativasPorIdPergunta(perguntaAtual.getIdPergunta());
            }
            return alternativasPorIdPergunta;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(List<Alternativa> alts) {
            super.onPostExecute(alts);
            alternativasCorretas = alts;
            new HttpRequestTaskAlternativasIncorretas().execute();
        }
    }

//    private class HttpRequestTaskAlternativasIncorretas extends AsyncTask<Void, Void, List<Alternativa>> {
//
//        @Override
//        protected List<Alternativa> doInBackground(Void... params) {
//            if (alternativasCorretas.size() == 1) {
//                alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretas(alternativasCorretas.get(0).getIdAlternativa(), 0, 4, alternativasCorretas.get(0).getGeneroPersonagem().getGeneroPersonagem());
//            } else {
//            }
//            if (alternativasCorretas.size() == 2) {
//                alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretas(alternativasCorretas.get(0).getIdAlternativa(), alternativasCorretas.get(1).getIdAlternativa(), 3, alternativasCorretas.get(0).getGeneroPersonagem().getGeneroPersonagem());
//            } else {
//            }
//
//            Log.i("debug", "AlternativasIncorretas " + alternativasIncorretas.size());
//            return alternativasIncorretas;
//        }
//
//        // é executado quando o webservice retorna
//        @Override
//        protected void onPostExecute(List<Alternativa> alts) {
//            super.onPostExecute(alternativasIncorretas);
//            alternativasIncorretas = alts;
//            obterAlternativasCorretasAleatorias();
//            inicializarRecyclerView();
//            alternativasAdapter.notifyDataSetChanged();
//        }

    private class HttpRequestTaskAlternativasIncorretas extends AsyncTask<Void, Void, List<Alternativa>> {

        @Override
        protected List<Alternativa> doInBackground(Void... params) {

            if (alternativasCorretas.size() == 2) {
                alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretas(alternativasCorretas.get(0).getIdAlternativa(),
                        alternativasCorretas.get(1).getIdAlternativa(), 3);
            } else {
                if(alternativasCorretas.size() == 1){
                    alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretas(alternativasCorretas.get(0).getIdAlternativa(), 0, 4);
                }
            }

            return alternativasIncorretas;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(List<Alternativa> alts) {
            super.onPostExecute(alternativasIncorretas);
            alternativasIncorretas = alts;
            obterAlternativasCorretasAleatorias();
            inicializarRecyclerView();
            alternativasAdapter.notifyDataSetChanged();
        }
    }

}