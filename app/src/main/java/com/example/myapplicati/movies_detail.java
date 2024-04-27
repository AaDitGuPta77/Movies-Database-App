package com.example.myapplicati;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplicati.Domains.MoviesItem;
import com.google.gson.Gson;

public class movies_detail extends AppCompatActivity {
    RequestQueue requestQueue;
    StringRequest stringRequest;
    TextView mName, des,rateTXT, durTXT;
    ImageView mImage, backBTN;
    ProgressBar loading10;
    RecyclerView recyclerViewActors;
    RecyclerView.Adapter adapterActors;
    NestedScrollView sv;
    int idMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        idMovie = getIntent().getIntExtra("id",0);
        mName = findViewById(R.id.mName);
        des = findViewById(R.id.des);
        mImage = findViewById(R.id.mImage);
        backBTN = findViewById(R.id.backBTN);
        loading10 = findViewById(R.id.pb10);
        rateTXT = findViewById(R.id.rateTxt);
        durTXT = findViewById(R.id.durTxt);
        sv = findViewById(R.id.sv);
        recyclerViewActors = findViewById(R.id.recyclerView6);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backBTN.setOnClickListener(v -> finish());
        sendRequest();
    }
    void sendRequest() {
        requestQueue = Volley.newRequestQueue(this);
        loading10.setVisibility(View.VISIBLE);
        sv.setVisibility(View.GONE);

        stringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idMovie, response -> {
            Gson gson = new Gson();
            loading10.setVisibility(View.GONE);
            sv.setVisibility(View.VISIBLE);

            MoviesItem item = gson.fromJson(response, MoviesItem.class);
            Glide.with(movies_detail.this)
                    .load(item.getPoster())
                    .into(mImage);
            mName.setText(item.getTitle());
            des.setText(item.getPlot());
            rateTXT.setText(item.getImdbRating());
            durTXT.setText(item.getRuntime());

            if (item.getImages() != null){
                adapterActors = new ActorListAdapter(item.getImages());
                recyclerViewActors.setAdapter(adapterActors);
            }
        }, error -> loading10.setVisibility(View.VISIBLE));
        requestQueue.add(stringRequest);
    }
}