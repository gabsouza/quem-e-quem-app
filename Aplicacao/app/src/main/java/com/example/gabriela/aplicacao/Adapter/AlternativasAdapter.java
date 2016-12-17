package com.example.gabriela.aplicacao.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gabriela.aplicacao.R;

import java.util.List;
import java.util.Locale;

import pojo.Alternativa;

/**
 * Created by Gabriela on 15/12/2016.
 */

public class AlternativasAdapter extends RecyclerView.Adapter<AlternativasAdapter.ViewHolder>   {

    private List<Alternativa> alternativas;
    private Context ctx, context;
    private TextToSpeech textToSpeech;

        public AlternativasAdapter(List < Alternativa > alternativas, Context ctx) {
            this.alternativas = alternativas;
            this.ctx = ctx;
        }


        @Override
        public ViewHolder onCreateViewHolder ( final ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(ctx);
            View view = layoutInflater.inflate(R.layout.card_alternativas, parent, false);

            return new ViewHolder(view);
        }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Alternativa alternativa = alternativas.get(position);
        holder.btOpcao.setText(alternativa.getDescricao());
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
        Button btOpcao;

        public ViewHolder(View itemView) {
            super(itemView);
            btOpcao = (Button) itemView.findViewById(R.id.bt_opcao);
        }
    }
}
