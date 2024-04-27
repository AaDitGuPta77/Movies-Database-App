package com.example.myapplicati;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

public class ExploreFragment extends Fragment {

    public ExploreFragment() {
    }
    RecyclerView recyclerViewExplore;
    RecyclerView.Adapter adapterExplore;
    RequestQueue requestQueue1;
    StringRequest stringRequest5;
    ProgressBar loading5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerViewExplore = view.findViewById(R.id.recyclerViewExplore);
        recyclerViewExplore.setLayoutManager(new GridLayoutManager(getActivity(),3));

        loading5 = view.findViewById(R.id.pb5);

        sendRequest5();
        return view;
    }

    void sendRequest5(){
        requestQueue1 = Volley.newRequestQueue(getActivity());
        loading5.setVisibility(View.VISIBLE);
        stringRequest5 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", response -> {
            Gson gson = new Gson();
            loading5.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterExplore = new moviesListAdapter(items);
            recyclerViewExplore.setAdapter(adapterExplore);
        }, volleyError -> loading5.setVisibility(View.GONE));
        requestQueue1.add(stringRequest5);
    }
}