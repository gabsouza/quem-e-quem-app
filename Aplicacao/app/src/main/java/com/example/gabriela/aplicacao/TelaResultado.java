package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Gabriela on 01/12/2016.
 */
public class TelaResultado extends Activity {

    private TextView tvParabens;
    private TextView tvVoceFez;
    private TextView tvPontos;
    private TextView tvSomaPontos;
    private Button btFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        inicializaComponentes();

//        Intent intent = getIntent();
//
//        Bundle bundle = intent.getExtras();
//        String count = bundle.getString("count");
////        int count = bundle.getInt("count");
//
//        tvSomaPontos.setText(count);


//        btFechar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chamaTelaMain();
//                finish();
//            }
//        });

    }


    public void chamaTelaMain(){
        Intent itTelaMain = new Intent(this, MainActivity.class);
        startActivity(itTelaMain);
        finish();
    }


    public void inicializaComponentes(){
        tvParabens = (TextView)findViewById(R.id.tv_parabens);
        tvVoceFez = (TextView)findViewById(R.id.tv_vc_fez);
        tvPontos = (TextView)findViewById(R.id.tv_pontos);
        tvSomaPontos = (TextView) findViewById(R.id.tv_soma_pontos);
        btFechar = (Button)findViewById(R.id.bt_fechar_dialog);
    }
}
