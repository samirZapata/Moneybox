package co.edu.usbbog.moneybox.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Income_earn extends AppCompatActivity {

    private final String usbURl = "http://172.17.3.114:3300/ingresos";
    private final String baseUrl = "http://172.17.3.114:3300/ingresos";

    Button btnGoDH;
    TextView viewUsuario;
    EditText edtGF;

    RequestQueue requestQueue;
    Intent i;
    SharedPreferences Income_earn;
    String nombre, usuario, id;
    //String userPrefer, name, ID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_earn);

        //HOOKS
        btnGoDH = findViewById(R.id.btnGo);
        viewUsuario = findViewById(R.id.viewUSR);
        edtGF = findViewById(R.id.edtGFI);

        requestQueue = Volley.newRequestQueue(this);

        //GET USER NAME
        i = getIntent();
        nombre = i.getStringExtra("nombre");
        usuario = i.getStringExtra("usuario");
        id = i.getStringExtra("id");
        viewUsuario.setText("Hola, " + nombre);


//        SharedPreferences objectsPrefer = getSharedPreferences("objects", MODE_PRIVATE);
//        ID = objectsPrefer.getString("id", "");
//        name = objectsPrefer.getString("name", "");
//        userPrefer = objectsPrefer.getString("usuario", "");


        btnGoDH.setOnClickListener((View view) -> {
            Intent j = new Intent(Income_earn.this, Dashboard.class);
            String valor = edtGF.getText().toString();
            j.putExtra("valor", valor);
            j.putExtra("nombre", nombre);
            j.putExtra("usuario", usuario);
            j.putExtra("id", id);

            IngresoMensual(baseUrl);
            startActivity(j);
        });

        viewUsuario.setOnClickListener(view -> {
            Intent i = new Intent(Income_earn.this, Perfil.class);
            i.putExtra("nombre", usuario);
            startActivity(i);
        });
    }


    private void IngresoMensual(String baseUrl) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Income_earn.this, "Se ha insertado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error + "");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> parametros = new HashMap<String, String>();
                String valor = edtGF.getText().toString();

                Intent g;

//                i = getIntent();
//                String usuario = i.getStringExtra("id");
                parametros.put("valor", valor);
                parametros.put("usuario", id);
                parametros.put("username", usuario);


                Log.i("PARAMETROS ", parametros.toString());

                return parametros;

            }
        };
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}