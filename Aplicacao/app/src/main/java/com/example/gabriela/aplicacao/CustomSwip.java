package com.example.gabriela.aplicacao;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Gabriela on 13/09/2016.
 */
public class CustomSwip extends PagerAdapter {

    private int [] recursoImagem = {R.drawable.foto_personagem_1, R.drawable.foto_personagem_2, R.drawable.foto_personagem_3, R.drawable.foto_personagem_4, R.drawable.foto_personagem_5, R.drawable.foto_personagem_6};
    private Context ctx;
    private LayoutInflater layoutInflater;
    private View itemView;
    private ImageView imageView;
    private int posicaoCorrente;


    //construtor da classe
    public CustomSwip(Context c){
        ctx = c;
    }

    public int getImagemCorrente() {
        return recursoImagem[posicaoCorrente];
    }
    // Conta quantas imagens tem
    @Override
    public int getCount() {
        return recursoImagem.length;
    }

    public int getPosicaoCorrente() {
        return this.posicaoCorrente;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = layoutInflater.inflate(R.layout.custom_swip, container, false);
        imageView = (ImageView) itemView.findViewById(R.id.swip_image_view);
        imageView.setImageResource(recursoImagem[position]);
        container.addView(itemView);
        this.posicaoCorrente = position;
        Log.i("DEBUG",posicaoCorrente+" POSICAOOO");
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return
                (view == object);
    }
}
