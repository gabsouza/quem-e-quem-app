package com.example.gabriela.aplicacao.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabriela.aplicacao.R;

import java.util.ArrayList;
import java.util.List;

import com.example.gabriela.aplicacao.Interface.RecyclerViewOnClickListenerHack;
import com.example.gabriela.aplicacao.TelaJogoProfissao;

import pojo.MiniJogo;

/**
 * Created by Gabriela on 08/10/2016.
 */
public class MiniJogoAdapter extends RecyclerView.Adapter<MiniJogoAdapter.MyViewHolder> {
    private List<MiniJogo> miniJogos;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width, height;

    public MiniJogoAdapter(Context ctx, List<MiniJogo> l) {
        miniJogos = l;
        mLayoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MiniJogoAdapter(List<MiniJogo> l) {
        miniJogos = l;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_jogo_card, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        MiniJogo miniJogo = miniJogos.get(position);
        myViewHolder.ivJogo.setImageResource(miniJogos.get(position).getPhoto());
        myViewHolder.tvNomeMiniJogo.setText(miniJogos.get(position).getNomeMiniJogo());
        myViewHolder.tvIntroducao.setText((miniJogos.get(position).getIntroducao()));
        myViewHolder.id = miniJogo.getIdMiniJogo();
    }

    @Override
    public int getItemCount() {
        return miniJogos.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;

    }

    public void addListItem(MiniJogo mj, int position){
        miniJogos.add(mj);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        miniJogos.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivJogo;
        public TextView tvNomeMiniJogo;
        public TextView tvIntroducao;
        public int id;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivJogo = (ImageView) itemView.findViewById(R.id.iv_jogo);
            tvNomeMiniJogo = (TextView) itemView.findViewById(R.id.tv_nome_mini_jogo);
            tvIntroducao = (TextView) itemView.findViewById(R.id.tv_introducao_mini_jogo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), TelaJogoProfissao.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id_mini_jogo", id);
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {
        if(mRecyclerViewOnClickListenerHack != null){
            mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
        }
        }
    }

    static class PagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}