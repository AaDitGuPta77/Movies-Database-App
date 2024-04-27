package com.example.myapplicati;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplicati.Domains.ListFilm;
import com.google.gson.Gson;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }
    RecyclerView recyclerViewTrendingMovies,recyclerViewTopPicks,recyclerViewMostLiked,recyclerViewAdventure;
    RecyclerView.Adapter adapterTrendingMovies,adapterTopPicks,adapterMostLiked,adapterAdventure;
    RequestQueue requestQueue;
    ViewPager2 viewPager2;
    ArrayList<Integer> slideItems;
    StringRequest stringRequest1, stringRequest2, stringRequest3, stringRequest4;
    ProgressBar loading1, loading2, loading3, loading4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager2 = view.findViewById(R.id.viewPager);

        slideItems = new ArrayList<>();

        slideItems.add(R.drawable.wakanda);
        slideItems.add(R.drawable.xmen);

        SliderAdapter sliderAdapter = new SliderAdapter(slideItems);
        viewPager2.setAdapter(sliderAdapter);


        recyclerViewTrendingMovies = view.findViewById(R.id.recyclerView1);
        recyclerViewTrendingMovies.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTopPicks = view.findViewById(R.id.recyclerView2);
        recyclerViewTopPicks.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        recyclerViewMostLiked = view.findViewById(R.id.recyclerView3);
        recyclerViewMostLiked.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAdventure = view.findViewById(R.id.recyclerView4);
        recyclerViewAdventure.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));

        loading1 = view.findViewById(R.id.pb1);
        loading2 = view.findViewById(R.id.pb2);
        loading3 = view.findViewById(R.id.pb3);
        loading4 = view.findViewById(R.id.pb4);

        sendRequest1();
        sendRequest2();
        sendRequest3();
        sendRequest4();
        return view;
    }
    void sendRequest1(){
        requestQueue = Volley.newRequestQueue(getActivity());
        loading1.setVisibility(View.VISIBLE);
        stringRequest1 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterTrendingMovies = new moviesListAdapter(items);
            recyclerViewTrendingMovies.setAdapter(adapterTrendingMovies);
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loading1.setVisibility(View.GONE);
            }
        });
        requestQueue.add(stringRequest1);
    }
    void sendRequest2(){
        requestQueue = Volley.newRequestQueue(getActivity());
        loading2.setVisibility(View.VISIBLE);
        stringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterTopPicks = new moviesListAdapter(items);
            recyclerViewTopPicks.setAdapter(adapterTopPicks);
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loading2.setVisibility(View.GONE);
            }
        });
        requestQueue.add(stringRequest2);
    }
    void sendRequest3(){
        requestQueue = Volley.newRequestQueue(getActivity());
        loading3.setVisibility(View.VISIBLE);
        stringRequest3 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=5", response -> {
            Gson gson = new Gson();
            loading3.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterMostLiked = new moviesListAdapter(items);
            recyclerViewMostLiked.setAdapter(adapterMostLiked);
        }, volleyError -> loading3.setVisibility(View.GONE));
        requestQueue.add(stringRequest3);
    }
    void sendRequest4(){
        requestQueue = Volley.newRequestQueue(getActivity());
        loading4.setVisibility(View.VISIBLE);
        stringRequest4 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=7", response -> {
            Gson gson = new Gson();
            loading4.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterAdventure = new moviesListAdapter(items);
            recyclerViewAdventure.setAdapter(adapterAdventure);
        }, volleyError -> loading4.setVisibility(View.GONE));
        requestQueue.add(stringRequest4);
    }
}