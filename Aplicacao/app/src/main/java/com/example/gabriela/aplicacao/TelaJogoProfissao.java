package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabriela.aplicacao.Adapter.AlternativasAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

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
    private ImageButton btFalar;
    private Context context;

    private PerguntaConsumer perguntaConsumer;
    private Pergunta perguntaAtual;
    private List<Pergunta> perguntas = new ArrayList<>();

    private AlternativaConsumer alternativaConsumer;
    private List<Alternativa> alternativasCorretas, alternativasIncorretas, alternativasPorIdPergunta;
    private ArrayList<Alternativa> alternativasMescladas;
    private String genero;

    private Resposta resposta;
    private int count;

    RecyclerView recyclerView;
    AlternativasAdapter alternativasAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_profissao);
        inicializaComponentes();

        new HttpRequestTaskPergunta().execute();
//        obterPerguntasAleatorias();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new
                        RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                if (alternativasMescladas.get(position).getPergunta().getIdPergunta() == perguntaAtual.getIdPergunta()) {

                                    int pontuacaoAtual = resposta.getPontuacao();

                                    resposta.setPontuacao(pontuacaoAtual + 10);

                                    obterPersonagensAleatorios();
                                    obterPerguntasAleatorias();
                                    new HttpRequestTaskAlternativaPorIdPergunta().execute();
                                    obterAlternativasCorretasAleatorias();

                                    Toast.makeText(TelaJogoProfissao.this, "acertou, " + resposta.getPontuacao() + " pontos," +
                                            " faltam " + perguntas.size() + " questões", Toast.LENGTH_LONG).show();
                                } else {

                                    //AQUI
                                    if (alternativasMescladas.get(position).getPergunta().getIdPergunta() != perguntaAtual.getIdPergunta()) {

                                        int pontuacaoAtual = resposta.getPontuacao();

                                        resposta.setPontuacao(pontuacaoAtual - 5);

                                        obterPersonagensAleatorios();
                                        obterPerguntasAleatorias();
                                        new HttpRequestTaskAlternativaPorIdPergunta().execute();
                                        obterAlternativasCorretasAleatorias();

                                        Toast.makeText(TelaJogoProfissao.this, "errou, " + resposta.getPontuacao() + " pontos," +
                                                " faltam " + perguntas.size() + " questões", Toast.LENGTH_LONG).show();
                                    }

                                }
//                                count = resposta.getPontuacao();
                            }
                        })

        );

        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Button btn = (Button) v.findViewById(R.id.bt_opcao);
                String falar = btn.getText().toString();
                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);

                return false;
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

        if (cards[n] == R.drawable.personagem1 || cards[n] == R.drawable.personagem2 || cards[n] == R.drawable.personagem4 || cards[n] == R.drawable.personagem9) {
            genero = "masculino";
        } else {
            genero = "feminino";
        }
    }

    public void obterPerguntasAleatorias() {
        Random ran = new Random();

        if (perguntas.size() > 0) {

            int ranNum = ran.nextInt(this.perguntas.size());

            perguntaAtual = perguntas.get(ranNum);
            Log.i("DEBUG", perguntaAtual.getDescricao());
            tvPergunta.setText(perguntaAtual.getDescricao());
            perguntas.remove(ranNum);
            perguntas.size();


        } else {
            chamaTelaResultado();
        }
    }

    public void obterAlternativasCorretasAleatorias() {

        alternativasMescladas = new ArrayList<>(alternativasCorretas);
        alternativasMescladas.addAll(alternativasIncorretas);

        Collections.shuffle(alternativasMescladas);
        alternativasAdapter = new AlternativasAdapter(alternativasMescladas, this);
        recyclerView.setAdapter(alternativasAdapter);
        alternativasAdapter.notifyDataSetChanged();
    }

    public void chamaTelaResultado() {
        Intent intent = new Intent(TelaJogoProfissao.this, TelaResultado.class);

//        Bundle bundle = new Bundle();
//        bundle.putSerializable("count", String.valueOf(count));
//        Log.i("DEBUG", String.valueOf(count));
//        intent.putExtras(bundle);
        startActivity(intent);
//        Log.i("DEBUG", "estartou" + count);
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

        btFalar = (ImageButton) findViewById(R.id.bt_falar_pergunta);

        perguntaConsumer = new PerguntaConsumer();
        perguntas = new ArrayList<>();

        alternativaConsumer = new AlternativaConsumer();

        alternativasCorretas = new ArrayList<>();
        alternativasIncorretas = new ArrayList<>();

        alternativasPorIdPergunta = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_alternativas);

        resposta = new Resposta();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("TelaJogoProfissao Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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

    private class HttpRequestTaskAlternativasIncorretas extends AsyncTask<Void, Void, List<Alternativa>> {

        @Override
        protected List<Alternativa> doInBackground(Void... params) {

            if (alternativasCorretas.size() == 2) {
                alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretas(alternativasCorretas.get(0).getIdAlternativa(),
                        alternativasCorretas.get(1).getIdAlternativa(), 3, genero);
            } else {
                if (alternativasCorretas.size() == 1) {
                    alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretas(alternativasCorretas.get(0).getIdAlternativa(), 0, 4, genero);
                } else {

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