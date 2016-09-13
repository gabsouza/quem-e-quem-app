package com.example.gabriela.aplicacao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by Gabriela on 19/08/2016.
 */
public class TelaPrincipalCrianca extends ActionBarActivity {

    private Toolbar mToolbar;
    private Toolbar mToolbarSelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_crianca);
        inicializaComponentes();
    }

    public void inicializaComponentes() {
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);
        mToolbarSelo = (Toolbar) findViewById(R.id.);

    }
}
