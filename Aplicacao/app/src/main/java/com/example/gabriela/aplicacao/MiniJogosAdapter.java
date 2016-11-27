package com.example.gabriela.aplicacao;

/**
 * Created by PC-CASA on 23/11/2016.
 */

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class MiniJogosAdapter extends RecyclerView.Adapter<MiniJogosAdapter.MyViewHolder> {

    private Context mContext;
    private List<MiniJogo> miniJogoList;
//    private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeMiniJogo, descricao;
        public ImageView imagem;

        public MyViewHolder(View view) {
            super(view);
            nomeMiniJogo = (TextView) view.findViewById(R.id.tv_nome_mini_jogo);
            descricao = (TextView) view.findViewById(R.id.descricao);
            imagem = (ImageView) view.findViewById(R.id.iv_imagem_jogo);
        }
    }


    public MiniJogosAdapter(Context mContext, List<MiniJogo> miniJogoList) {
        this.mContext = mContext;
        this.miniJogoList = miniJogoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jogo_card, parent, false);
//        itemView.setOnClickListener(mOnClickListener);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        MiniJogo miniJogo = miniJogoList.get(position);
        holder.nomeMiniJogo.setText(miniJogo.getNomeMiniJogo());
        holder.descricao.setText(miniJogo.getDescricao());

        // loading album cover using Glide library
        Glide.with(mContext).load(miniJogo.getImagem()).into(holder.imagem);
    }

    @Override
    public int getItemCount() {
        return miniJogoList.size();
    }
}