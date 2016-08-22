package com.example.gabriela.aplicacao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by Gabriela on 19/08/2016.
 */
public class TelaPrincipal extends ActionBarActivity {

    private Toolbar toolbar;
    private Toolbar toolbarButtom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        this.inicializaComponentes();

    }

    private void toolbarsetters(){
        toolbar.setTitle("Teste");
        toolbar.setSubtitle("teste2");

        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }


    private void inicializaComponentes(){
        toolbar = (Toolbar) findViewById(R.id.tb_principal);
        toolbarButtom = (Toolbar) findViewById(R.id.tb_buttom);
    }


}
