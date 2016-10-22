package com.example.claquete;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class AtoresFragment extends Fragment {

    private List<Ator> mAtores;
    private AtoresAdapter mAdapter;

    public AtoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atores, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerView.setLayoutManager(layoutManager);

        mAtores = new ArrayList<>();
        mAdapter = new AtoresAdapter(mAtores);
        recyclerView.setAdapter(mAdapter);

        ObterAtoresTask task = new ObterAtoresTask();
        task.execute("http://api.themoviedb.org/3/person/popular?api_key=6f3c2d1f7598c1e6142f73b0dd3b58e8");

        return view;
    }

    public class ObterAtoresTask extends AsyncTask<String, Void, JSONObject> {

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
                    mAtores.clear();
                    JSONArray actorsArray = json.getJSONArray("results");
                    for (int i = 0; i < actorsArray.length(); i++) {
                        JSONObject actorObject = actorsArray.getJSONObject(i);
                        int id = actorObject.getInt("id");
                        String name = actorObject.getString("name");
                        String profilePath = actorObject.getString("profile_path");
                        mAtores.add(new Ator(id, name, profilePath));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
