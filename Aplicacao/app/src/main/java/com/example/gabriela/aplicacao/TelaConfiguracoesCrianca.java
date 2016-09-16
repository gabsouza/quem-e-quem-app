package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Gabriela on 24/08/2016.
 */
public class TelaConfiguracoesCrianca extends Activity {
    private ViewPager viewPager;
    private CustomSwip customSwip;
    private EditText et_nome;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitivity_configuracoes_crianca);
        inicializaComponentes();
        customSwip = new CustomSwip(this);
        viewPager.setAdapter(customSwip);
    }

    /*private void toolbarsetters(){
        toolbar.setTitle("Teste");
        toolbar.setSubtitle("teste2");

        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }*/

    private void inicializaComponentes(){
       // toolbar = (Toolbar) findViewById(R.id.tb_principal);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        et_nome = (EditText)findViewById(R.id.et_nome);
    }
}
