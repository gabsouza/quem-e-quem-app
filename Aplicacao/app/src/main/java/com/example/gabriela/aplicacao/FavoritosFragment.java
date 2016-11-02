package com.example.gabriela.aplicacao;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/*public class FavoritosFragment extends Fragment implements FilmesAdapter.OnItemClickListener {

    private List<Filme> mMovies;
    private FilmesAdapter mAdapter;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filmes, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);

        mMovies = new ArrayList<>();
        mAdapter = new FilmesAdapter(mMovies);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);

        GetFavoredMoviesTask task = new GetFavoredMoviesTask();
        task.execute(205596, 286217, 808, 109445);

        return view;
    }

    /*@Override
    public void onItemClick(View itemView, int position) {
        Intent intent = new Intent(getActivity(), VerFilmeActivity.class);
        intent.putExtra(VerFilmeActivity.ARG_MOVIE, mMovies.get(position));
        startActivity(intent);
    }*/

   /* public class GetFavoredMoviesTask extends AsyncTask<Integer, Void, List<JSONObject>> {

        @Override
        protected List<JSONObject> doInBackground(Integer... movieIds) {
            List<JSONObject> jsonObjects = new ArrayList<>();

            for (Integer movieId : movieIds) {
                try {
                    URL url = new URL("https://api.themoviedb.org/3/movie/" + movieId +"?api_key=6f3c2d1f7598c1e6142f73b0dd3b58e8&language=pt-BR");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    InputStream stream = new BufferedInputStream(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }

                    JSONObject jsonObject = new JSONObject(builder.toString());
                    jsonObjects.add(jsonObject);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            return jsonObjects;
        }

        @Override
        protected void onPostExecute(List<JSONObject> jsonObjects) {
            if (jsonObjects != null && jsonObjects.size() > 0) {
                mMovies.clear();
                for (JSONObject jsonObject : jsonObjects) {
                    try {
                        mMovies.add(Filme.createFromJSONObject(jsonObject));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}*/
