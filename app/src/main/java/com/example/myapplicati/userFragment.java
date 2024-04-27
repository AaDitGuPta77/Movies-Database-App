package com.example.myapplicati;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class userFragment extends Fragment {

    public userFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView textView = view.findViewById(R.id.textView);
         DBHelper dbHelper = new DBHelper(this.getActivity().getApplicationContext());

         textView.setText(dbHelper.getUsername());
         return view;
    }
}