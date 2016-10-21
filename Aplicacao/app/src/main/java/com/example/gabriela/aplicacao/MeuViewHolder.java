package com.example.gabriela.aplicacao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MeuViewHolder extends RecyclerView.ViewHolder {
    TextView txtNome;
    ImageView imgAtor;

    public MeuViewHolder(View itemView) {
        super(itemView);

        txtNome = (TextView) itemView.findViewById(R.id.txtNome);
        imgAtor = (ImageView) itemView.findViewById(R.id.imgAtor);
    }
}
