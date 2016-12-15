package com.example.gabriela.aplicacao.Adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gabriela.aplicacao.R;

import java.util.List;
import java.util.Locale;

import pojo.Alternativa;

/**
 * Created by Gabriela on 15/12/2016.
 */

public class AlternativasAdapter extends RecyclerView.Adapter<AlternativasAdapter.ViewHolder> {

    private List<Alternativa> alternativas;
    private Context ctx, context;
    private TextToSpeech textToSpeech;


    public AlternativasAdapter(List<Alternativa> alternativas, Context ctx) {
        this.alternativas = alternativas;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View view = layoutInflater.inflate(R.layout.card_alternativas, parent, false);

//        context = getApplicationContext();

        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.getDefault());
                }
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Alternativa alternativa = alternativas.get(position);
        holder.btOpcao.setText(alternativa.getDescricao());

        holder.btAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String falar = holder.btOpcao.getText().toString();

                textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null);
            }

        });
        //holder.btAudio.setText("Audio");
    }

    @Override
    public int getItemCount() {
        return alternativas.size();
    }

    public List<Alternativa> getItemList() {
        return alternativas;
    }

    public Alternativa getItem(int pos) {
        return alternativas.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return alternativas.get(pos).getIdAlternativa();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btOpcao, btAudio;

        public ViewHolder(View itemView) {
            super(itemView);
            btOpcao = (Button) itemView.findViewById(R.id.bt_opcao);
            //btAudio = (Button) itemView.findViewById(R.id.bt_audio);
        }
    }
}
