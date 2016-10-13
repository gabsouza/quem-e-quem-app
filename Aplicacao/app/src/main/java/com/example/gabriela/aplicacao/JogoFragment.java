package com.example.gabriela.aplicacao;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pojo.MiniJogo;

/**
 * Created by Gabriela on 08/10/2016.
 */
public class JogoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<MiniJogo> mList;

    @Nullable
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
                CardAdapter adapter = (CardAdapter) mRecyclerView.getAdapter();

                if(mList.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1){
                    List<MiniJogo> listAux = ((TelaPrincipalCrianca)getActivity()).getSetMiniJogoList(4);

                    for(int i = 0; i <listAux.size(); i++){
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mList = ((TelaPrincipalCrianca)getActivity()).getSetMiniJogoList(4);
        CardAdapter adapter = new CardAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);

        return view;
    }
}
