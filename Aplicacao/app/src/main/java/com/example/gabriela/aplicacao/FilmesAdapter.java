package com.example.gabriela.aplicacao;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.ViewHolder> {

    private List<Filme> mFilmes;
    private OnItemClickListener mListener;

    public FilmesAdapter(List<Filme> movies) {
        this.mFilmes = movies;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_filme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Filme filme = mFilmes.get(position);

        LoadURLIntoImageView task = new LoadURLIntoImageView(holder.imgPhoto);
        task.execute("https://image.tmdb.org/t/p/w92" + filme.getPoster());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onItemClick(view, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilmes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);

        }
    }
}
