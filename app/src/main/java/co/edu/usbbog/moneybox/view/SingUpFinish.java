package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.edu.usbbog.moneybox.R;

public class SingUpFinish extends AppCompatActivity {

    Button btnLogin, btnSingUpFIN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_finish);

        //HOOKS
        btnLogin = findViewById(R.id.btnLogSG);
        btnSingUpFIN = findViewById(R.id.btnSingUpFIN);

        btnLogin.setOnClickListener((View view) -> {
            Intent i = new Intent(SingUpFinish.this, login.class);
            startActivity(i);
        });

    }
}