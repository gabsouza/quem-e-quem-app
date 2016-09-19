package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import pojo.Perfil;

/**
 * Created by Gabriela on 24/08/2016.
 */
public class TelaConfiguracoesCrianca extends Activity {
    private ViewPager viewPager;
    private CustomSwip customSwip;
    private EditText etNome;
    private ImageButton ibMicrofone;
    private Button btSalvar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitivity_configuracoes_crianca);

        inicializaComponentes();

        customSwip = new CustomSwip(this);
        viewPager.setAdapter(customSwip);

        this.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perfil perfil = new Perfil();
                perfil.setNome(etNome.getText().toString());
                //perfil.setMidia(viewPager.get());

                // PerfilDao.Cadastrar(perfil);

                Toast.makeText(TelaConfiguracoesCrianca.this, "Salvo", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inicializaComponentes(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        etNome = (EditText)findViewById(R.id.et_nome);
        ibMicrofone = (ImageButton) findViewById(R.id.im_microfone);
        btSalvar = (Button) findViewById(R.id.bt_salvar);
    }
}
