package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import pojo.MiniJogo;

/**
 * Created by Gabriela on 19/08/2016.
 */
public class TelaPrincipalCrianca extends Activity {

    private Toolbar mToolbar, mToolbarSelo;
    private Button btConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_crianca);
       inicializaComponentes();

       this.btConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaConfiguracoesCrianca();
            }
        });
    }

    private void chamaTelaConfiguracoesCrianca(){
        Intent itTelaConfiguracoesCrianca = new Intent(this, TelaConfiguracoesCrianca.class);
        startActivity(itTelaConfiguracoesCrianca);
        finish();
    }

    public List<MiniJogo> getSetMiniJogoList(int qtd){
        String[] nomeMiniJogo = new String[]{"Festa a Fantasia", "Profissões", "Teste1", "Teste2"};
        String[] introducao = new String[]{ "É um jogo que fala sobre fantasias", "É um jogo que fala sobre profissões", "É um teste1", "É um teste2"};
        int[] photos = new int[]{R.drawable.profissao,R.drawable.profissao, R.drawable.profissao, R.drawable.profissao};
        List<MiniJogo> listAux = new ArrayList<>();

        for (int i = 0; i<qtd; i++){
            MiniJogo mj = new MiniJogo(nomeMiniJogo[i % nomeMiniJogo.length], introducao[i % introducao.length], photos[i % nomeMiniJogo.length]);
            listAux.add(mj);
        }
        return (listAux);
    }

    public void inicializaComponentes() {
        btConf = (Button) findViewById(R.id.bt_conf);
    }
}

