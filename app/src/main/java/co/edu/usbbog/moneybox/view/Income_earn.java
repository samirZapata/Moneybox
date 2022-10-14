package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.usbbog.moneybox.R;

public class Income_earn extends AppCompatActivity {

    private final String baseUrl = "";

    Button btnGoDH;
    TextView viewUsuario;
    EditText edtGF;

    Intent userName;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_earn);

        //HOOKS
        btnGoDH = findViewById(R.id.btnGo);
        viewUsuario = findViewById(R.id.viewUSR);
        edtGF = findViewById(R.id.edtGFI);

        //GET USER NAME
       userName = getIntent();
       String user = userName.getStringExtra("nombre");
       viewUsuario.setText("Hola, "+user);

        btnGoDH.setOnClickListener((View view)->{
            Intent i = new Intent(Income_earn.this, Dashboard.class);
            startActivity(i);
        });
    }
}