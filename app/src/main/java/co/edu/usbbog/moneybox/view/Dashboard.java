package co.edu.usbbog.moneybox.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.temporal.ValueRange;

import co.edu.usbbog.moneybox.R;

public class Dashboard extends AppCompatActivity {

    Button btnIF, btnIN, btnViewBill;
    TextView viewUSR, viewIngresos;

    EditText edtGFijo, edtPeriodo, edtValor;


    Intent i;
    BottomSheetDialog bottomSheetDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //HOOKS
        btnIF = findViewById(R.id.btnGF);
        viewUSR = findViewById(R.id.viewUserName);
        viewIngresos = findViewById(R.id.viewIngresos);
        btnViewBill = findViewById(R.id.btnVG);


        //DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        i = getIntent();
        String usr = i.getStringExtra("nombre");
        String  valor = i.getStringExtra("valor");
        String id = i.getStringExtra("id");
        String usuario = i.getStringExtra("usuario");
        //String fomateado = decimalFormat.format(valor);

        viewIngresos.setText("$ " + valor);
        viewUSR.setText("Hola, " + usr);


        bottomSheetDialog = new BottomSheetDialog(this);
        //btnIF.setOnClickListener(view -> new Bottom_sheet().show(getSupportFragmentManager(),"Show"));

        btnIF.setOnClickListener((View v) -> {
            Intent i = new Intent(Dashboard.this, Input_Bill.class);
            i.putExtra("nombre", usr);
            i.putExtra("cash", valor);
            System.out.println("VALOR ENVIADO" + valor);
            i.putExtra("id", id);
            startActivity(i);
        });

        viewUSR.setOnClickListener((View view)->{
            Intent perfil = new Intent(Dashboard.this, Perfil.class);
            perfil.putExtra("usuario", usuario);
            perfil.putExtra("nombre", usr);
            startActivity(perfil);
        });

        btnViewBill.setOnClickListener((View view)->{
            Intent vBill = new Intent(Dashboard.this, Show_bills.class);
            vBill.putExtra("nombre", usr);
            vBill.putExtra("id", id);
            vBill.putExtra("cash", valor);

            System.out.println("ENVIANDO " + usr + " " + id + " " + valor);

            startActivity(vBill);
        });

    }

}