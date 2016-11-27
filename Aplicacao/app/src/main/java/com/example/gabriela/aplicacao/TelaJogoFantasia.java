package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.gabriela.aplicacao.R;

/**
 * Created by Gabriela on 26/11/2016.
 */
public class TelaJogoFantasia extends Activity {

    private Button btFantasia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jogo_fantasia);

        btFantasia = (Button)findViewById(R.id.button2);
    }
}
