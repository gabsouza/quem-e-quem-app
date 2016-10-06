package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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

        //ADICIONAR EVENTO BOTÃO AUTENTICAR
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

    public void inicializaComponentes() {
       // mToolbar = (Toolbar) findViewById(R.id.tb_main);
       // setSupportActionBar(mToolbar);
       // mToolbarSelo = (Toolbar) findViewById(R.id.);
        btConf = (Button) findViewById(R.id.bt_conf);

    }
}

