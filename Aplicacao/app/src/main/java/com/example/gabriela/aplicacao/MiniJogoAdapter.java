package com.example.gabriela.aplicacao;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MiniJogoAdapter extends RecyclerView.Adapter<MeuViewHolder> {

    private List<MiniJogo> mMiniJogos;

    public MiniJogoAdapter(List<MiniJogo> miniJogos) {
        this.mMiniJogos = miniJogos;
    }

    @Override
    public MeuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_jogo, parent, false);
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MeuViewHolder holder, int position) {
        MiniJogo miniJogo = mMiniJogos.get(position);

        holder.txtNomeMiniJogo.setText(miniJogo.getNomeMiniJogo());

        LoadURLIntoImageView task = new LoadURLIntoImageView(holder.imgMiniJogo);
        task.execute("https://image.tmdb.org/t/p/w45" + miniJogo.getPhoto());
    }

    @Override
    public int getItemCount() {
        return mMiniJogos.size();
    }
}
