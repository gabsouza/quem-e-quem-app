package com.example.gabriela.aplicacao.Fragment;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gabriela.aplicacao.MainActivity;
import com.example.gabriela.aplicacao.R;
import com.example.gabriela.aplicacao.adapter.MiniJogoAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.gabriela.aplicacao.Interface.RecyclerViewOnClickListenerHack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pojo.MiniJogo;

/**
 * Created by Gabriela on 08/10/2016.
 */
public class MiniJogoFragment extends Fragment implements RecyclerViewOnClickListenerHack {
    private RecyclerView mRecyclerView;
    private List<MiniJogo> miniJogos;
    private MiniJogoAdapter mAdapter;

    public MiniJogoFragment() {
    }

    public static MiniJogoFragment createInstance(List<MiniJogo> miniJogos) {
        MiniJogoFragment miniJogoFragment = new MiniJogoFragment();
        miniJogoFragment.miniJogos = miniJogos;
        return miniJogoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mini_jogo, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                MiniJogoAdapter adapter = (MiniJogoAdapter) mRecyclerView.getAdapter();

                if(miniJogos.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1){
                    List<MiniJogo> listAux = ((MainActivity)getActivity()).getSetMiniJogoList(4);

                    for(int i = 0; i <listAux.size(); i++){
                        adapter.addListItem(listAux.get(i), miniJogos.size());
                    }
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        miniJogos = ((MainActivity)getActivity()).getSetMiniJogoList(4);
        MiniJogoAdapter adapter = new MiniJogoAdapter(getActivity(), miniJogos);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        miniJogos = new ArrayList<>();
        mAdapter = new MiniJogoAdapter(miniJogos);
        mRecyclerView.setAdapter(mAdapter);

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
                    miniJogos.clear();
                    JSONArray actorsArray = json.getJSONArray("results");
                    for (int i = 0; i < actorsArray.length(); i++) {
                        JSONObject actorObject = actorsArray.getJSONObject(i);
                        int idMiniJogo = actorObject.getInt("idMiniJogo");
                        String nomeMiniJogo = actorObject.getString("nomeMiniJogo");
                        int photo = actorObject.getInt("photo");
                        String introducao = actorObject.getString("introducao");
                        miniJogos.add(new MiniJogo(idMiniJogo, nomeMiniJogo, photo, introducao));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_SHORT).show();

        MiniJogoAdapter adapter = (MiniJogoAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);
    }
}