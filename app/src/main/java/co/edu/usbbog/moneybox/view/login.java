package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.edu.usbbog.moneybox.R;

public class login extends AppCompatActivity {

    Button btnSingup, btnLogin;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //HOOKS
        btnLogin =  findViewById(R.id.btnLoginLG);
        btnSingup = findViewById(R.id.btnSinUpLG);


        btnSingup.setOnClickListener((View view) -> {
            Intent i = new Intent(login.this, SingUp.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener((View view) -> {
            Intent i = new Intent(login.this, Income_earn.class);
            startActivity(i);
        });
    }
}