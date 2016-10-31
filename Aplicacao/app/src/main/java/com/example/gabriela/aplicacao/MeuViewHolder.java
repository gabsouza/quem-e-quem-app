package com.example.gabriela.aplicacao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MeuViewHolder extends RecyclerView.ViewHolder {
    TextView txtNomeMiniJogo;
    ImageView imgMiniJogo;

    public MeuViewHolder(View itemView) {
        super(itemView);

        txtNomeMiniJogo = (TextView) itemView.findViewById(R.id.txtNomeMiniJogo);
        imgMiniJogo = (ImageView) itemView.findViewById(R.id.imgMiniJogo);
    }
}
