package co.edu.usbbog.moneybox.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.helperclasses.ListAdapter;
import co.edu.usbbog.moneybox.helperclasses.ListEmelemnt;

public class Show_bills extends AppCompatActivity {

    private final String baseUrl = "http://192.168.0.2:3300/";

    TextView viewUser, viewBalance;
    List<ListEmelemnt> Lgastos;
    RecyclerView ListaGastos;
    RequestQueue requestQueue;
    Intent id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bills);

        //HOOKS
        ListaGastos = findViewById(R.id.Gastos);
        viewUser = findViewById(R.id.usr);
        viewBalance = findViewById(R.id.viewCash);
        requestQueue = Volley.newRequestQueue(this);

        //PUT USER NAME
        id = getIntent();
        String usrName = id.getStringExtra("nombre");
        String cash = id.getStringExtra("cash");
        String valorGasto = id.getStringExtra("gasto");
        //int balance = (cash - valorGasto);
        viewBalance.setText("$ "+ cash );
        viewUser.setText("Hola " + usrName);


        showBills(baseUrl);

    }

    private void showBills(String URL) {
        id = getIntent();
        String idUser = id.getStringExtra("id");
        String direction = "gastos?usuario=" + idUser;
        String url = URL + direction;
        Log.i("URL", url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            int size = jsonArray.length();

                            for (int i = 0; i < size; i++) {
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());

                                String gasto = jsonObject.getString("gasto");
                                String valor = jsonObject.getString("valor");
                                String fecha = jsonObject.getString("fecha");


                                Lgastos = new ArrayList<>();
                                Lgastos.add(new ListEmelemnt(null, null, gasto, valor, fecha));
                                Lgastos.add(new ListEmelemnt(null, null, gasto, valor, fecha));
//                                Lgastos.add(new ListEmelemnt(null, null, gasto, valor, fecha));
//                                Lgastos.add(new ListEmelemnt(null, null, "Utencilios", "$ 500.000", "09/22"));
//                                Lgastos.add(new ListEmelemnt(null, null, "Desayuno", "$ 900.000", "09/22"));

                                ListAdapter listAdapter = new ListAdapter(Lgastos, Show_bills.this);
                                ListaGastos.setHasFixedSize(true);
                                ListaGastos.setLayoutManager(new LinearLayoutManager(Show_bills.this));
                                ListaGastos.setAdapter(listAdapter);

                            }
                        } catch (JSONException e) {

                            System.out.println("ERROR: " + e.getMessage());
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Show_bills.this, "Error al loguearse...", Toast.LENGTH_SHORT).show();
                        System.out.println("----------------------------");
                        System.out.println("ERROR: " + error.getMessage());
                        System.out.println("----------------------------");
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

}