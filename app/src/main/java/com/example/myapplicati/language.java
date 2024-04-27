package com.example.myapplicati;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class language extends AppCompatActivity {

    Button hin,eng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_change);

        eng = findViewById(R.id.eng);
        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocal();
                recreate();
                Intent intent = new Intent(language.this, LoginPage.class);
                startActivity(intent);
            }
        });

        hin = findViewById(R.id.hin);
        hin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale();
                recreate();
                Intent intent = new Intent(language.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }

    private void setLocal(){
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
    private void setLocale() {
        Locale locale = new Locale("hi");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}
