package com.example.gabriela.aplicacao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VerFilmeActivity extends AppCompatActivity {

    public static final String ARG_MOVIE = "movie";
    private List<Filme> mFilmesSimilares;
    private FilmesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_filme);

        Filme filme = (Filme) getIntent().getSerializableExtra(ARG_MOVIE);

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(filme.getTitulo());

        TextView txtOriginalTitle = (TextView) findViewById(R.id.txtOriginalTitle);
        txtOriginalTitle.setText("(" + filme.getTituloOriginal() + ")");

        TextView txtReleasedAt = (TextView) findViewById(R.id.txtReleasedAt);
        txtReleasedAt.setText(filme.getLancadoEm());

        TextView txtOverview = (TextView) findViewById(R.id.txtOverview);
        txtOverview.setText(filme.getResumo());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mFilmesSimilares = new ArrayList<>();
        mAdapter = new FilmesAdapter(mFilmesSimilares);
        recyclerView.setAdapter(mAdapter);

        UpdatePosterTask updatePosterTask = new UpdatePosterTask();
        updatePosterTask.execute("https://image.tmdb.org/t/p/w154" + filme.getPoster());

        ObterFilmesSimilaresTask obterFilmesSimilaresTask = new ObterFilmesSimilaresTask();
        obterFilmesSimilaresTask.execute("https://api.themoviedb.org/3/movie/" + filme.getID() + "/similar?api_key=6f3c2d1f7598c1e6142f73b0dd3b58e8&language=pt-BR");
    }

    public class UpdatePosterTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            String posterURL = params[0];
            Bitmap bitmap = null;

            try {
                URL url = new URL(posterURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                ImageView imgPoster = (ImageView) findViewById(R.id.imgPoster);
                imgPoster.setImageBitmap(bitmap);
            }
        }
    }

    public class ObterFilmesSimilaresTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject result = null;

            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                result = new JSONObject(builder.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            if (json != null) {
                try {
                    mFilmesSimilares.clear();
                    JSONArray moviesArray = json.getJSONArray("results");
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObject = moviesArray.getJSONObject(i);
                        Filme filme = Filme.createFromJSONObject(movieObject);
                        mFilmesSimilares.add(filme);
                    }

                    View similarCard = findViewById(R.id.similarCard);
                    if (mFilmesSimilares.size() > 0) {
                        similarCard.setVisibility(View.VISIBLE);
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
