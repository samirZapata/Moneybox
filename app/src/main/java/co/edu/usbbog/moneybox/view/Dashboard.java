package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.helperclasses.Bottom_sheet;

public class Dashboard extends AppCompatActivity {

    Button btnIF;
    TextView viewUSR, viewIngresos;
    
    Intent i;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //HOOKS
        btnIF = findViewById(R.id.btnGF);
        viewUSR = findViewById(R.id.viewUserName);
        viewIngresos = findViewById(R.id.viewIngresos);
        
        i = getIntent();
        String usr = i.getStringExtra("nombre");
        String valor = i.getStringExtra("valor");
        viewIngresos.setText(valor);
        viewUSR.setText("Hola, "+usr);
        
        
        btnIF.setOnClickListener((View view)->{
            Bottom_sheet bottomSheet = new Bottom_sheet();
            bottomSheet.show(getSupportFragmentManager(), "TAG");
        });
    }
}