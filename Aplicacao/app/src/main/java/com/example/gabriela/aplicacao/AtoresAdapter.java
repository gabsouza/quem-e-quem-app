package com.example.gabriela.aplicacao;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AtoresAdapter extends RecyclerView.Adapter<MeuViewHolder> {

    private List<Ator> mAtores;

    public AtoresAdapter(List<Ator> atores) {
        this.mAtores = atores;
    }

    @Override
    public MeuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_ator, parent, false);
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MeuViewHolder holder, int position) {
        Ator ator = mAtores.get(position);

        holder.txtNome.setText(ator.getNome());

        LoadURLIntoImageView task = new LoadURLIntoImageView(holder.imgAtor);
        task.execute("https://image.tmdb.org/t/p/w45" + ator.getImagem());
    }

    @Override
    public int getItemCount() {
        return mAtores.size();
    }
}
