package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

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
    private static final int reconheceVoz = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitivity_configuracoes_crianca);

        inicializaComponentes();

        customSwip = new CustomSwip(this);
        viewPager.setAdapter(customSwip);


        ibMicrofone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                entradaVoz();
            }
        });


        /*this.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perfil perfil = new Perfil();
                perfil.setNome(etNome.getText().toString());
                //perfil.setMidia(viewPager.get());

                // PerfilDao.Cadastrar(perfil);
              //  Toast.makeText(TelaConfiguracoesCrianca.this, "Salvo", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void entradaVoz() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                //aqui tem que pegar o audio depois
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, reconheceVoz);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    // recebe entradaVoz
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case reconheceVoz: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    etNome.setText(result.get(0));
                }
                break;
            }

        }
    }

    private void inicializaComponentes(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        etNome = (EditText)findViewById(R.id.et_nome);
        ibMicrofone = (ImageButton) findViewById(R.id.im_microfone);
        btSalvar = (Button) findViewById(R.id.bt_salvar);
    }
}
