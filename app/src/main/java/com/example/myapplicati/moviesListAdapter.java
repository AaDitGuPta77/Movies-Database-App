package com.example.myapplicati;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplicati.Domains.ListFilm;

public class moviesListAdapter extends RecyclerView.Adapter<moviesListAdapter.moviesListViewHolder> {
    ListFilm items;
    Context context;
    public moviesListAdapter(ListFilm items) {
        this.items = items;
    }

    @NonNull
    @Override
    public moviesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_model, parent, false);
        return new moviesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull moviesListViewHolder holder, int position) {
        RequestOptions ReqOptions = new RequestOptions();
        ReqOptions = ReqOptions.transform(new RoundedCorners(30));

        Glide.with(holder.itemView.getContext())
                .load(items.getData().get(position).getPoster())
                .apply(ReqOptions)
                .into(holder.moviesImg);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), movies_detail.class);
            intent.putExtra("id", items.getData().get(position).getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.getData().size();
    }

    public class moviesListViewHolder extends RecyclerView.ViewHolder {
        ImageView moviesImg;


        public moviesListViewHolder(@NonNull View itemView) {
            super(itemView);
            moviesImg = itemView.findViewById(R.id.moviesImage);

        }

    }
}
