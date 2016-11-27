package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Gabriela on 18/11/2016.
 */
public class TelaJogoProfissao extends Activity{

    private ImageView ivPersonagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_profissao);
        inicializaComponentes();

        int[] cards={R.drawable.personagem1,R.drawable.personagem2,R.drawable.personagem3,R.drawable.personagem4,R.drawable.personagem5,R.drawable.personagem6,R.drawable.personagem7};
        Random r = new Random();
        int n=r.nextInt(7);
        ivPersonagem.setImageResource(cards[n]);
    }

    public void inicializaComponentes(){
        ivPersonagem = (ImageView) findViewById(R.id.iv_personagem);
    }
}