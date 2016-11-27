package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Gabriela on 18/11/2016.
 */
public class TelaJogoProfissao extends Activity{

    private ImageView ivPersonagem;
    private Button btPassar;
    private TextView pergunta, opcao1, opcao2, opcao3, opcao4, opcao5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_profissao);
        inicializaComponentes();

        btPassar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] cards={R.drawable.personagem1,R.drawable.personagem2,R.drawable.personagem3,R.drawable.personagem4,R.drawable.personagem5,R.drawable.personagem6,R.drawable.personagem7, R.drawable.personagem8, R.drawable.personagem9};
                Random r = new Random();
                int n=r.nextInt(7);
                ivPersonagem.setImageResource(cards[n]);
            }
        });
    }

    public void inicializaComponentes(){
        ivPersonagem = (ImageView) findViewById(R.id.iv_personagem);
        btPassar = (Button) findViewById(R.id.bt_randon);
        pergunta = (TextView) findViewById(R.id.tv_pergunta);
        opcao1 = (TextView) findViewById(R.id.tv_opcao1);
        opcao2 = (TextView) findViewById(R.id.tv_opcao2);
        opcao3 = (TextView) findViewById(R.id.tv_opcao3);
        opcao4 = (TextView) findViewById(R.id.tv_opcao4);
        opcao5 = (TextView) findViewById(R.id.tv_opcao5);
    }
}