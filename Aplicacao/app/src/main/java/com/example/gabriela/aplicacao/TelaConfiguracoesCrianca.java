package com.example.gabriela.aplicacao;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Gabriela on 24/08/2016.
 */
// remor
public class TelaConfiguracoesCrianca extends ActionBarActivity {
    private ViewPager viewPager;
    private CustomSwip customSwip;
    private Toolbar toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitivity_configuracoes_crianca);
        inicializaComponentes();
    }

    private void toolbarsetters(){
        toolbar.setTitle("Teste");
        toolbar.setSubtitle("teste2");

        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }


    private void inicializaComponentes(){

        toolbar = (Toolbar) findViewById(R.id.tb_principal);
        viewPager = (ViewPager) findViewById (R.id.viewPager);
        customSwip = new customSwip(this);

    }
}
