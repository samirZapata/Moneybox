package co.edu.usbbog.moneybox.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
    private final String baseURL = "http://172.17.3.114:3300/gastos";
    private final String usbURL = "http://172.17.3.114:3300/";

    Intent i;
    TextView viewUser, viewCash;
    EditText edtGfijo, edtPeriodo, edtValor;
    RequestQueue requestQueue;
    Button btnIN;
    AutoCompleteTextView billType;

    String cash;

    @SuppressLint({"MissingInflatedId", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bill);

        //HOOKS
        btnIN = findViewById(R.id.btnIndicarGasto);
        edtGfijo = findViewById(R.id.edtGFijo);
        edtPeriodo = findViewById(R.id.edtPeriodo);
        edtValor = findViewById(R.id.edtValor);
        billType = findViewById(R.id.billType);
        viewCash = findViewById(R.id.n);
        requestQueue = Volley.newRequestQueue(this);

        /*---------------------------------------------------------------------*/
        i = getIntent();
        String usr = i.getStringExtra("nombre");
        cash = i.getStringExtra("cash");
        System.out.println("VALOR RECIBIDO " + cash);
        String id = i.getStringExtra("id");

//        viewUser.setText("Hola " + usr);
        viewCash.setText("$ " + cash);
        //END SHOW USER NAME 'N CASH

        /*---------------------------------------------------------------------*/
        //SPINNER
        /*---------------------------------------------------------------------*/

        //Gastos basicos = 50%, Deseos = 30%, Ahorro = 20%
        String[] billTyp = new String[]{"Gastos basicos", "Deseos", "Ahorro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                billTyp
        );

        billType.setAdapter(adapter);
        //END SPINNER

        /*---------------------------------------------------------------------*/
        //ACTION
        /*---------------------------------------------------------------------*/

        btnIN.setOnClickListener((View v) -> {


            String valor = edtValor.getText().toString();
            System.out.println(valor);
            int val = Integer.parseInt(valor);
            int balance = Integer.parseInt(cash);

            int total = (balance - val);

            Intent k = new Intent(Input_Bill.this, Show_bills.class);

            bills(baseURL);
            k.putExtra("id", id);
            k.putExtra("nombre", usr);
            k.putExtra("cash", cash);
            k.putExtra("gastado", val);
            k.putExtra("total", total + "");
            Log.i("CASH QUE SE MANDA A SHOW BILLS ", cash + "");
            Log.i("GASTO QUE SE MANDA A SHOW BILLS ", val + "");
            startActivity(k);
            finish();
        });

//        billType.setOnClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Input_Bill.this, billType.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void bills(String url) {

        String concepto = edtGfijo.getText().toString();
        String periodo = edtPeriodo.getText().toString();
        String valor = edtValor.getText().toString();
        String tipo = billType.getText().toString();

        System.out.println("LO QUE OBTENGO");
        Log.i("CONCEPTO", concepto + "");
        Log.i("PERIODO", periodo + "");
        Log.i("VALOR", valor + "");
        Log.i("TIPO", tipo + "");

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
                Log.e("error", error + "");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                Intent id;
                id = getIntent();
                String ids = id.getStringExtra("id");
                String origen = "1"; //id.getStringExtra("is");

                params.put("gasto", concepto);
                params.put("fecha", periodo);
                params.put("valor", valor);
                params.put("usuario", ids);
                params.put("origen", origen);
                params.put("tipo", tipo);

                id.putExtra("gasto", valor);

                System.out.println("LO QUE SE ESTA ENVIANDO");
                Log.i("CONCEPTO", concepto + "");
                Log.i("PERIODO", periodo + "");
                Log.i("VALOR", valor + "");
                Log.i("USUARIO & ORIGEN", ids + "");
                Log.i("TIPO", tipo + "");


                Log.i("COMPLETE", params.toString());
                return params;

            }
        };
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void updateBill() {
        i = getIntent();
        String saldo = i.getStringExtra("saldo");
        viewCash.setText("$ " + saldo);
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        i = getIntent();
//        String saldo = i.getStringExtra("saldo");
//        viewCash.setText("$ " + saldo);
//    }

    private void clean() {
        edtGfijo.setText("");
        edtPeriodo.setText("");
        edtValor.setText("");
    }

}