package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabriela.aplicacao.Adapter.AlternativasAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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

    private TextToSpeech textToSpeech;
    private ImageView ivPersonagem;
    private Button btPassar;
    private TextView tvPergunta;
    private Button opcao1, opcao2, opcao3, opcao4, opcao5, btFalar, btFalar1, btFalar2, btFalar3, btFalar4, btFalar5;
    private Context context;

    private PerguntaConsumer perguntaConsumer;
    private Pergunta perguntaAtual;
    private List<Pergunta> perguntas;

    private AlternativaConsumer alternativaConsumer;
    private List<Alternativa> alternativasCorretas, alternativasIncorretas, alternativasPorIdPergunta;
    ArrayList<Alternativa> alternativasMescladas;

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
                    obterPersonagensAleatorios();
                    obterPerguntasAleatorias();

                    // BUSCA AS ALTERNATIVAS
                    new HttpRequestTaskAlternativaPorIdPergunta().execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }

//                opcao1.setOnClickListener(new View.OnClickListener(){
//
//                    @Override
//                    public void onClick(View v) {
//                        for (Alternativa a : alternativasMescladas) {
//                            if (a.getDescricao().equalsIgnoreCase(opcao1.getText().toString())) {
//                                Toast.makeText(getApplicationContext(), "certo", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(getApplicationContext(), "errado", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//
//                opcao2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (Alternativa a : alternativasMescladas) {
//                            if (a.getDescricao().equalsIgnoreCase(opcao2.getText().toString())) {
//                                Toast.makeText(getApplicationContext(), "certo", Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(getApplicationContext(), "errado", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//
//                opcao3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (Alternativa a : alternativasMescladas) {
//                            if (a.getDescricao().equalsIgnoreCase(opcao3.getText().toString())) {
//                                Toast.makeText(getApplicationContext(), "certo", Toast.LENGTH_SHORT).show();
//                            } else{
//                                Toast.makeText(getApplicationContext(), "errado", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//
//                opcao4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (Alternativa a : alternativasMescladas) {
//                            if (a.getDescricao().equalsIgnoreCase(opcao4.getText().toString())) {
//                                Toast.makeText(getApplicationContext(), "certo", Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(getApplicationContext(), "errado", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    }
//                });
//
//                opcao5.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        for (Alternativa a : alternativasMescladas) {
//                            if (a.getDescricao().equalsIgnoreCase(opcao5.getText().toString())) {
//                                Toast.makeText(getApplicationContext(), "certo", Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(getApplicationContext(), "errado", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    }
//                });
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

//        btFalar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String falar = tvPergunta.getText().toString();
//
//                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
//
//        btFalar1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String falar = opcao1.getText().toString();
//
//                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
//
//        btFalar2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String falar = opcao2.getText().toString();
//
//                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
//
//        btFalar3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String falar = opcao3.getText().toString();
//
//                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
//
//        btFalar4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String falar = opcao4.getText().toString();
//
//                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
//
//        btFalar5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String falar = opcao5.getText().toString();
//
//                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });

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

        Random ran1 = new Random();
        Random ran2 = new Random();
        Random ran3 = new Random();
        Random ran4 = new Random();
        Random ran5 = new Random();

        int tam = alternativasMescladas.size();

        if (tam > 0) {

//            int ranNum1 = ran1.nextInt(tam);
//            opcao1.setText(alternativasMescladas.get(ranNum1).getDescricao());
//            alternativasMescladas.remove(ranNum1);
//            tam = tam - 1;
//
//            int ranNum2 = ran2.nextInt(tam);
//            opcao2.setText(alternativasMescladas.get(ranNum2).getDescricao());
//            alternativasMescladas.remove(ranNum2);
//            tam = tam - 1;
//
//            int ranNum3 = ran3.nextInt(tam);
//            opcao3.setText(alternativasMescladas.get(ranNum3).getDescricao());
//            alternativasMescladas.remove(ranNum3);
//            tam = tam - 1;
//
//            int ranNum4 = ran4.nextInt(tam);
//            opcao4.setText(alternativasMescladas.get(ranNum4).getDescricao());
//            alternativasMescladas.remove(ranNum4);
//            tam = tam - 1;
//
//            int ranNum5 = ran5.nextInt(tam);
//            opcao5.setText(alternativasMescladas.get(ranNum5).getDescricao());
//            alternativasMescladas.remove(ranNum5);
//            tam = tam - 1;
        } else {
            Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
        }
    }

    public void chamaTelaResultado() {
        Intent itTelaResultado = new Intent(this, TelaResultado.class);
        startActivity(itTelaResultado);
        finish();
    }

    public void inicializarRecyclerView() {
//        List<Alternativa> alternativas = new ArrayList<>(Arrays.asList(new Alternativa[]{
//                new Alternativa("Babá"),
//                new Alternativa("Policial"),
//                new Alternativa("Florista"),
//                new Alternativa("Médica"),
//                new Alternativa("Top")
//        }));

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
//        opcao1 = (Button) findViewById(R.id.tv_opcao1);
//        opcao2 = (Button) findViewById(R.id.tv_opcao2);
//        opcao3 = (Button) findViewById(R.id.tv_opcao3);
//        opcao4 = (Button) findViewById(R.id.tv_opcao4);
//        opcao5 = (Button) findViewById(R.id.tv_opcao5);
//
//        btFalar = (Button) findViewById(R.id.bt_falar_pergunta);
//        btFalar1 = (Button) findViewById(R.id.bt_falar_opcao1);
//        btFalar2 = (Button) findViewById(R.id.bt_falar_opcao2);
//        btFalar3 = (Button) findViewById(R.id.bt_falar_opcao3);
//        btFalar4 = (Button) findViewById(R.id.bt_falar_opcao4);
//        btFalar5 = (Button) findViewById(R.id.bt_falar_opcao5);

        perguntaConsumer = new PerguntaConsumer();
        perguntas = new ArrayList<>();

        alternativaConsumer = new AlternativaConsumer();

        alternativasCorretas = new ArrayList<>();
        alternativasIncorretas = new ArrayList<>();

        alternativasPorIdPergunta = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_alternativas);
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

    private class HttpRequestTaskAlternativaPorIdPergunta extends AsyncTask<Void, Void, List<Alternativa>> {

        @Override
        protected List<Alternativa> doInBackground(Void... params) {
            alternativasPorIdPergunta = alternativaConsumer.chamalistarAlternativasPorIdPergunta(perguntaAtual.getIdPergunta());
            Log.i("debug", "alternativasCorretas doIn " + alternativasPorIdPergunta.size());
            return alternativasPorIdPergunta;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(List<Alternativa> alts) {
            super.onPostExecute(alts);
            Log.i("debug", "Alternativas corretas onPost " + alts.size());
            alternativasCorretas = alts;
            new HttpRequestTaskAlternativasIncorretas().execute();
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

            Log.i("debug", "AlternativasIncorretas " + alternativasIncorretas.size());
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

//    private class HttpRequestTaskAlternativasIncorretasFemininas extends AsyncTask<Void, Void, List<Alternativa>> {
//
//        @Override
//        protected List<Alternativa> doInBackground(Void... params) {
//
//            if (alternativasCorretas.size() == 2) {
//                alternativasIncorretas = alternativaConsumer.buscarAlternativasIncorretasFemininas(alternativasCorretas.get(0).getIdAlternativa(),
//                        alternativasCorretas.get(1).getIdAlternativa(), 3, );
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
//        }
//    }

}