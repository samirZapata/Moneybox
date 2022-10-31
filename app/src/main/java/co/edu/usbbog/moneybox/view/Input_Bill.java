package co.edu.usbbog.moneybox.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import co.edu.usbbog.moneybox.R;

public class Input_Bill extends AppCompatActivity {
    private final String baseURL = "http://192.168.0.2:3300/gastos";

    Intent i;
    TextView viewUser, viewCash;
    EditText edtGfijo, edtPeriodo, edtValor;
    RequestQueue requestQueue;
    Button btnIN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bill);

        //HOOKS
        btnIN = findViewById(R.id.btnIndicarGasto);
        edtGfijo = findViewById(R.id.edtGFijo);
        edtPeriodo = findViewById(R.id.edtPeriodo);
        edtValor = findViewById(R.id.edtValor);
        requestQueue = Volley.newRequestQueue(this);

        //START SHOW USER NAME 'N CASH
        viewCash = findViewById(R.id.n);
        viewUser = findViewById(R.id.viewUserNameBills);
        i = getIntent();
        String usr = i.getStringExtra("nombre");
        String cash = i.getStringExtra("cash");
        String id = i.getStringExtra("id");
        viewUser.setText("Hola " + usr);
        viewCash.setText("$ " + cash);
        //END SHOW USER NAME 'N CASH


        //ACTION
        btnIN.setOnClickListener((View v)->{

            Intent k = new Intent(Input_Bill.this, Show_bills.class);

            bills(baseURL);
            k.putExtra("id", id);
            k.putExtra("nombre", usr);
            k.putExtra("cash", cash);
            startActivity(k);
        });

    }


    private void bills(String url) {

        String concepto = edtGfijo.getText().toString();
        String periodo = edtPeriodo.getText().toString();
        String  valor = edtValor.getText().toString();

        System.out.println("LO QUE OBTENGO");
        Log.i("CONCEPTO", concepto+ "");
        Log.i("PERIODO", periodo+ "");
        Log.i("VALOR", valor+ "");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Input_Bill.this, "Se ha insertado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(SingUp.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("error", error + "");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> params = new HashMap<String, String>();

                Intent id;
                id = getIntent();
                String ids = id.getStringExtra("id");

                params.put("gasto", concepto);
                params.put("fecha", periodo);
                params.put("valor", valor);
                params.put("usuario", ids);
                params.put("origen", ids);

                id.putExtra("gasto", valor);

                System.out.println("LO QUE SE ESTA ENVIANDO");
                Log.i("CONCEPTO", concepto+ "");
                Log.i("PERIODO", periodo+ "");
                Log.i("VALOR", valor+ "");
                Log.i("USUARIO & ORIGEN", ids+ "");


                Log.i("fsfsd", params.toString());
                return params;

            }
        };
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}