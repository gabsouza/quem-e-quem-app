package com.example.gabriela.aplicacao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pojo.MiniJogo;

/**
 * Created by Gabriela on 08/10/2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private List<MiniJogo> mList;
    private LayoutInflater mLayoutInflater;

    public CardAdapter(Context ctx, List<MiniJogo> miniJogoList) {
        mList = miniJogoList;
        mLayoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("DEBUG", "oncreateViewHolder");
        View v = mLayoutInflater.inflate(R.layout.activity_principal_crianca, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.ivJogo.setImageResource(mList.get(position).getPhoto());
        myViewHolder.tvNomeJogo.setText(mList.get(position).getNomeMiniJogo());
        myViewHolder.tvIntroducao.setText((mList.get(position).getIntroducao()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(MiniJogo mj, int position){
        mList.add(mj);
        notifyItemInserted(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivJogo;
        public TextView tvNomeJogo;
        public TextView tvIntroducao;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivJogo = (ImageView) itemView.findViewById(R.id.iv_jogo);
            tvNomeJogo = (TextView) itemView.findViewById(R.id.tv_nome_mini_jogo);
            tvIntroducao = (TextView) itemView.findViewById(R.id.tv_introducao_mini_jogo);
        }
    }
}
