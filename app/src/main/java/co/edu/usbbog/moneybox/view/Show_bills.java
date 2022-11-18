package co.edu.usbbog.moneybox.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    private final String baseUrl = "http://172.17.3.114:3300/";
    private final String usbURL = "http://172.17.3.114:3300/";


    TextView viewCash, viewBillless, viewTotal;
    List<ListEmelemnt> Lgastos;
    RecyclerView ListaGastos;
    RequestQueue requestQueue;
    Intent id, l;
    int counter = 0;

    Button btnAddBill, btnHome;
    String total;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bills);

        //HOOKS
        ListaGastos = findViewById(R.id.Gastos);
//        viewBillless = findViewById(R.id.viewBill);
        viewCash = findViewById(R.id.viewCash);
        viewTotal = findViewById(R.id.viewBalance);
        btnAddBill = findViewById(R.id.btnAdd);
        btnHome = findViewById(R.id.btnHome);
        requestQueue = Volley.newRequestQueue(this);

        //PUT USER NAME
        id = getIntent();
        String usrName = id.getStringExtra("nombre");
        String cash = id.getStringExtra("cash");
        total = id.getStringExtra("total");

        System.out.println("CASH QUE SE RECIBE SHOW BILLS " + cash);
        System.out.println("NOMBRE QUE SE RECIBE SHOW BILLS " + usrName);


        viewCash.setText("+" + cash + " $");
        viewTotal.setText("$ " + total);

        showBills(baseUrl);

        listeners();
    }


    private void listeners() {
        btnAddBill.setOnClickListener((View view) -> {
            l = new Intent(Show_bills.this, Input_Bill.class);
            l.putExtra("saldo", total);
            startActivity(l);
            finish();
        });

        btnHome.setOnClickListener((View view) -> {
            l = new Intent(Show_bills.this, Dashboard.class);
            l.putExtra("saldo", total);
            startActivity(l);
            finish();
        });
    }


    private void showBills(String URL) {
        id = getIntent();
        String idUser = id.getStringExtra("id");
        System.out.println("ID QUE SE RECIBE SHOW BILLS " + idUser);

        String direction = "gastos?id=" + idUser;
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

                            List<ListEmelemnt> items = new ArrayList<>();

                            for (int i = 0; i < size; i++) {
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());

                                String gasto = jsonObject.getString("gasto");
                                String valor = jsonObject.getString("valor");
                                String fecha = jsonObject.getString("fecha");
                                String billType = jsonObject.getString("tipo");

                                String billLess = valor;
//                                viewBillless.setText("-" + billLess + " $");

                                items.add(new ListEmelemnt(null, null, gasto, valor, fecha, billType));

                                ListAdapter adapter = new ListAdapter(items, Show_bills.this);
                                adapter.setItems(items);
                                adapter.notifyItemInserted(adapter.getItemCount() - 1);


                                ListaGastos.setHasFixedSize(true);
                                ListaGastos.setLayoutManager(new LinearLayoutManager(Show_bills.this));
                                ListaGastos.setAdapter(adapter);


                            }
                        } catch (JSONException e) {

                            System.out.println("ERROR: " + e);
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