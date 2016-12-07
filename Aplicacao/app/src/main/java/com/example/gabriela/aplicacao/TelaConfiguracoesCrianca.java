package com.example.gabriela.aplicacao;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Locale;

import consumer.PerfilConsumer;
import pojo.Perfil;
import pojo.Pergunta;

/**
 * Created by Gabriela on 24/08/2016.
 */
public class TelaConfiguracoesCrianca extends Activity {

    private ViewPager viewPager;
    private CustomSwip customSwip;
    private EditText etNomeAtualiza;
    private ImageButton ibMicrofone, ibCamera;
    private Button btAtualizar;
    private String selectedImagePath;
    private ImageView imgEdit;
    private Bitmap bitmap;
    private PerfilConsumer perfilConsumer;
    private MediaPlayer mp;
    private Uri uri;
    private static final int RECONHECE_VOZ = 30;
    private static final int PICK_IMAGE = 1;
    private Perfil perfil, perf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_crianca);

        inicializaComponentes();

        playAudio(Uri.parse("android.resource://com.example.gabriela.aplicacao/raw/teste"));
        customSwip = new CustomSwip(this);
        viewPager.setAdapter(customSwip);

        etNomeAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });

        ibMicrofone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                entradaVoz();
            }
        });

        ibCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intentCamera();
            }
        });


        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Perfil perfil = new Perfil();
                perfil.setNomePerfil(etNomeAtualiza.getText().toString());

                new HttpRequestTask().execute(perfil);
            // Log.i("DEBUG",((CustomSwip)viewPager.getAdapter()).setImagemCorrente()+"");
               // perfil.setMidia((CustomSwip) viewPager.getAdapter()).setImagemCorrente());
                Toast.makeText(TelaConfiguracoesCrianca.this, "Atualizado", Toast.LENGTH_LONG).show();

                chamaTelaMain();
            }
        });

//        intent();
    }

    private void chamaTelaMain(){
        Intent itTelaMain = new Intent(this, MainActivity.class);
        startActivity(itTelaMain);
        finish();
    }

    private void intent() {
        Intent intent = new Intent(this, TelaConfiguracoesCrianca.class);
        String passaNome = etNomeAtualiza.getText().toString();
//        String passaFoto = imgEdit.toString();
        Bundle bundle = new Bundle();

        bundle.putString("nome", passaNome);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    Log.i("debug", "clicou fora!!");

                    playAudio(Uri.parse("android.resource://com.example.gabriela.aplicacao/raw/teste"));
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    private void entradaVoz() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, RECONHECE_VOZ);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void playAudio(Uri uri){
        try {

                mp.setDataSource(this, uri);
                mp.prepare();
                mp.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //recebe a entrada tanto de voz quando de imagem e faz um switch case pra saber
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECONHECE_VOZ: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    etNomeAtualiza.setText(result.get(0));
                }
                break;
            }
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    Log.i("On onActivityResult", "on Activity Result");
                    Uri selectedImageUri = data.getData();
                    String filePath = null;

                    try {
                        String fileManagerString = selectedImageUri.getPath();

                        if (selectedImagePath != null) {
                            filePath = selectedImagePath;
                        } else if (fileManagerString != null) {
                            filePath = fileManagerString;
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.unknownPath, Toast.LENGTH_LONG).show();
                        }

                        if (filePath != null) {
                            decodeFile(filePath);
                        } else {
                            bitmap = null;
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), R.string.dataException, Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public void intentCamera() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            Log.i("Depois de selecionar", "Pegar imagem");
            startActivityForResult(Intent.createChooser(intent, ""), PICK_IMAGE);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.imageException, Toast.LENGTH_LONG).show();
            Log.e(e.getClass().getName(), e.getMessage(), e);
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    public void decodeFile(String filePath) {
        try {

            File f = new File(filePath);
            ExifInterface exif = new ExifInterface(f.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            int angle = 0;

            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }

            Matrix mat = new Matrix();
            mat.postRotate(angle);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            bitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
            ByteArrayOutputStream outstudentstreamOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outstudentstreamOutputStream);
            imgEdit.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.dataException, Toast.LENGTH_LONG).show();
        }
    }

    private void inicializaComponentes(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        etNomeAtualiza = (EditText)findViewById(R.id.et_nome);
        ibMicrofone = (ImageButton) findViewById(R.id.im_microfone);
        btAtualizar = (Button) findViewById(R.id.bt_atualizar);
        ibCamera = (ImageButton) findViewById(R.id.ib_camera);
        perfilConsumer = new PerfilConsumer();
        mp = new MediaPlayer();
        perfil = (Perfil)getIntent().getExtras().getSerializable("perf");

    }

    private class HttpRequestTask extends AsyncTask<Perfil, Void, Perfil> {

        // EXECUTA A TAREFA QUE DEVE SER REALIZADA

        @Override
        protected Perfil doInBackground(Perfil... params) {
            Log.i("DEBUG",perf.getNomePerfil());
            perfil = perfilConsumer.chamaAtualizar(perf.getIdPerfil(), perfil);
            Log.i("DEBUG",perfil.getNomePerfil());
            return perfil;
        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(Perfil perfil) {
            super.onPostExecute(perfil);
            Log.i("DEBUG",perfil.getNomePerfil());
            perf = perfil;

        }
    }
}