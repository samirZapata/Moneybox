package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private final String baseUrl = "http://172.20.10.4:3000/ingresos";

    Button btnGoDH;
    TextView viewUsuario;
    EditText edtGF;

    RequestQueue requestQueue;
    Intent i;

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
        String user = i.getStringExtra("nombre");
        String usuarioReal = i.getStringExtra("usuario");
        viewUsuario.setText("Hola, " + user);

        btnGoDH.setOnClickListener((View view) -> {
            Intent j = new Intent(Income_earn.this, Dashboard.class);
            String valor = edtGF.getText().toString();
            j.putExtra("valor", valor);
            j.putExtra("nombre", user);
            
           IngresoMensual(baseUrl);
            startActivity(j);
        });
        
        viewUsuario.setOnClickListener(view -> {
            Intent i = new Intent(Income_earn.this, perfil.class);
            i.putExtra("nombre", usuarioReal);
            startActivity(i);
        });
    }


    private void IngresoMensual(String baseUrl) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Income_earn.this, "Se ha insertado", Toast.LENGTH_SHORT).show();
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


                Map<String, String> parametros = new HashMap<String, String>();
                String ig = edtGF.getText().toString();

                Intent g;

                i = getIntent();
                String usuario = i.getStringExtra("usuario");
                parametros.put("valor", ig);
                parametros.put("usuario", usuario);
                
                Log.i("fsfsd", parametros.toString());
                
                return parametros;

            }
        };
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}