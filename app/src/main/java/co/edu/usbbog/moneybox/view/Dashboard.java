package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.badge.BadgeUtils;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.helperclasses.Bottom_sheet;

public class Dashboard extends AppCompatActivity {

    Button btnIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnIF = findViewById(R.id.btnGF);

        btnIF.setOnClickListener((View view)->{
            Bottom_sheet bottomSheet = new Bottom_sheet();
            bottomSheet.show(getSupportFragmentManager(), "TAG");
        });
    }
}