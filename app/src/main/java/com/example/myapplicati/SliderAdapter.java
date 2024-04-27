package com.example.myapplicati;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    List<Integer> slideItems;
    Context context;
    public SliderAdapter(List<Integer> slideItems) {
        this.slideItems = slideItems;
    }
    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_imovie,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Glide.with(context)
                .load(slideItems.get(position))
                .into(holder.imageV);
    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    public class SliderViewHolder extends  RecyclerView.ViewHolder{
       ImageView imageV;
       public SliderViewHolder(@NonNull View itemView){
           super(itemView);
           imageV = itemView.findViewById(R.id.pagerImage);
       }
   }
}
