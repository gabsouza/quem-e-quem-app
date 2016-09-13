package com.example.gabriela.aplicacao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by Gabriela on 24/08/2016.
 */
public class TelaConfiguracoesCrianca extends ActionBarActivity {

    private Toolbar toolbar;

    private void toolbarsetters(){
        toolbar.setTitle("Teste");
        toolbar.setSubtitle("teste2");

        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }


    private void inicializaComponentes(){
        toolbar = (Toolbar) findViewById(R.id.tb_principal);
    }
}
