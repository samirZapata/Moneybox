package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.helperclasses.ListAdapter;
import co.edu.usbbog.moneybox.helperclasses.ListEmelemnt;

public class Show_bills extends AppCompatActivity {


    List<ListEmelemnt> Lgastos;
    RecyclerView ListaGastos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bills);
        ListaGastos = findViewById(R.id.Gastos);

        ListaGastos();
    }

    private void ListaGastos(){
        Lgastos = new ArrayList<>();
        Lgastos.add(new ListEmelemnt(null, null, "Arriendo", "$ 900.000", "09/22"));
        Lgastos.add(new ListEmelemnt(null, null, "Colegiatura", "$ 90.000", "09/22"));
        Lgastos.add(new ListEmelemnt(null, null, "Utencilios", "$ 500.000", "09/22"));
        Lgastos.add(new ListEmelemnt(null, null, "Desayuno", "$ 900.000", "09/22"));

        ListAdapter listAdapter = new ListAdapter(Lgastos, this);
        ListaGastos.setHasFixedSize(true);
        ListaGastos.setLayoutManager(new LinearLayoutManager(this));
        ListaGastos.setAdapter(listAdapter);

    }

}