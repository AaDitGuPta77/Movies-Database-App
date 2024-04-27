package com.example.myapplicati;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupPage extends AppCompatActivity {

    EditText userEDT, emailEDT, passEDT;
    Button signUpBTN;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        DBHelper dbHelper = new DBHelper(this);
        userEDT = findViewById(R.id.userEdt);
        emailEDT = findViewById(R.id.emailEdt);
        passEDT = findViewById(R.id.passEdt);
        signUpBTN = findViewById(R.id.signUpBtn);
        signIn = findViewById(R.id.signInOnsignUp);

        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userEDT.getText().toString();
                String emailID = emailEDT.getText().toString();
                String password = passEDT.getText().toString();

                if (validatePassword() | validateUsername() | validateEmailID()) {
                    boolean insert =  dbHelper.insertData(username, emailID,password);
                    if (insert) {
                        Intent intent = new Intent(SignupPage.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignupPage.this,"not inserted",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupPage.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
    private boolean validateUsername() {
        String val = userEDT.getText().toString();
        String noWhiteSpace = "(?=\\S+$) ";

        if (val.isEmpty()) {
            return false;
        } else if(val.length() >= 15) {
            return false;
        } else if (val.matches(noWhiteSpace)) {
            return false;
        } else {
            return true;
        }
    }
    private boolean validatePassword() {
        String val = passEDT.getText().toString();
        String pattern = "^(?=.[a-z])(?=.[A-Z])(?=.\\d)[a-zA-Z\\d@$!%?&]{8,}$";

        if (val.isEmpty()) {
            return false;
        } else if (!val.matches(pattern)) {
            return false;
        } else {
            return true;
        }
    }
    private boolean validateEmailID(){
        String val = emailEDT.getText().toString();
        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            return false;
        } else if (!val.matches(pattern)) {
            return false;
        } else {
            return true;
        }
    }
}
