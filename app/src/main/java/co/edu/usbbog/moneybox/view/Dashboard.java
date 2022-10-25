package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.helperclasses.Bottom_sheet;

public class Dashboard extends AppCompatActivity {

    Button btnIF, btnIN;
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


        i = getIntent();
        String usr = i.getStringExtra("nombre");
        String valor = i.getStringExtra("valor");
        viewIngresos.setText(valor);
        viewUSR.setText("Hola, " + usr);


        bottomSheetDialog = new BottomSheetDialog(this);

        btnIF.setOnClickListener((View view) -> {
            Bottom_sheet bottomSheet = new Bottom_sheet();
            bottomSheet.show(getSupportFragmentManager(), "TAG");
        });

    }

}