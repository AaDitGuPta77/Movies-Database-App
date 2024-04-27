package com.example.myapplicati;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btnView;
    HomeFragment homeFragment = new HomeFragment();
    ExploreFragment exploreFragment = new ExploreFragment();
    userFragment UserFragment = new userFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = findViewById(R.id.bottomBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        btnView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                // Your navigation logic here
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                }
                else if(id == R.id.explore){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, exploreFragment).commit();
                }
                else if(id == R.id.profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, UserFragment).commit();
                }
            }
        });
    }
}
