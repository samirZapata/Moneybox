package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.edu.usbbog.moneybox.R;

public class Income_earn extends AppCompatActivity {

    Button btnGoDH;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_earn);

        btnGoDH = findViewById(R.id.btnGo);


        btnGoDH.setOnClickListener((View view)->{
            Intent i = new Intent(Income_earn.this, Dashboard.class);
            startActivity(i);
        });
    }
}