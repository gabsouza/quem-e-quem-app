package com.example.gabriela.aplicacao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by Gabriela on 19/08/2016.
 */
public class TelaPrincipal extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        this.inicializaComponentes();

    }

    private void toolbarsets(){
        toolbar.setTitle("Teste");
        setSupportActionBar(toolbar);
    }


    private void inicializaComponentes(){
        toolbar = (Toolbar) findViewById(R.id.tb_principal);

    }


}
