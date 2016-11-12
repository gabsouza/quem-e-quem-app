package com.example.gabriela.aplicacao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabriela.aplicacao.R;

import java.util.List;

import com.example.gabriela.aplicacao.Interface.RecyclerViewOnClickListenerHack;
import pojo.MiniJogo;

/**
 * Created by Gabriela on 08/10/2016.
 */
public class JogoAdapter extends RecyclerView.Adapter<JogoAdapter.MyViewHolder> {
    private Context mContext;
    private List<MiniJogo> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width, height;

    public JogoAdapter(Context ctx, List<MiniJogo> miniJogoList) {
        mContext = ctx;
        mList = miniJogoList;
        mLayoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        height = (width / 16) * 9;
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
        myViewHolder.tvNomeMiniJogo.setText(mList.get(position).getNomeMiniJogo());
        myViewHolder.tvIntroducao.setText((mList.get(position).getIntroducao()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;

    }

    public void addListItem(MiniJogo mj, int position){
        mList.add(mj);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivJogo;
        public TextView tvNomeMiniJogo;
        public TextView tvIntroducao;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivJogo = (ImageView) itemView.findViewById(R.id.iv_jogo);
            tvNomeMiniJogo = (TextView) itemView.findViewById(R.id.tv_nome_mini_jogo);
            tvIntroducao = (TextView) itemView.findViewById(R.id.tv_introducao_mini_jogo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        if(mRecyclerViewOnClickListenerHack != null){
            mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());

        }
        }
    }
}