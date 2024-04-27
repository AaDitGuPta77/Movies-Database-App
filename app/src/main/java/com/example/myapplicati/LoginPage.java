package com.example.myapplicati;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class LoginPage extends AppCompatActivity {
    EditText emailEDT, passEDT;
    Button loginBTN;
    TextView signUp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        DBHelper dbHelper = new DBHelper(this);

        emailEDT = findViewById(R.id.emailEdt);
        passEDT = findViewById(R.id.passEdt);
        loginBTN = findViewById(R.id.loginBtn);
        signUp = findViewById(R.id.signUpOnsignIn);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEDT.getText().toString();
                String pass = passEDT.getText().toString();
                if(dbHelper.checkUsername(email) && dbHelper.checkusernamePassword(email,pass)) {
                    Intent intent = new Intent(LoginPage.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPage.this,SignupPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
