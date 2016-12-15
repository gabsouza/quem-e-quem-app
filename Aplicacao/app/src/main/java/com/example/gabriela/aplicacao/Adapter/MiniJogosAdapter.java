package com.example.gabriela.aplicacao.Adapter;

/**
 * Created by PC-CASA on 23/11/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gabriela.aplicacao.MiniJogo;
import com.example.gabriela.aplicacao.R;
import com.example.gabriela.aplicacao.TelaJogoFantasia;
import com.example.gabriela.aplicacao.TelaJogoProfissao;

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
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final MiniJogo miniJogo = miniJogoList.get(position);
        holder.nomeMiniJogo.setText(miniJogo.getNomeMiniJogo());
        holder.descricao.setText(miniJogo.getDescricao());

        holder.itemView.setTag(miniJogo);
        // loading album cover using Glide library
        Glide.with(mContext).load(miniJogo.getImagem()).into(holder.imagem);


        holder.imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, "posicao: " + position, Toast.LENGTH_LONG).show();
                if(position == 0){
                Intent profissao = new Intent(mContext, TelaJogoProfissao.class);

                mContext.startActivity(profissao);
            }else{
                    Intent fantasia = new Intent(mContext, TelaJogoFantasia.class);
                    mContext.startActivity(fantasia);
                }
                }
        });
    }



    @Override
    public int getItemCount() {
        return miniJogoList.size();
    }
}