package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.usbbog.moneybox.R;

public class Login extends AppCompatActivity {

    Button btnSingup, btnLogin;
    EditText edtUser, edtPass;
    RequestQueue requestQueue;

    private final String baseUrl = "http://192.168.0.2:3000/";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestQueue = Volley.newRequestQueue(this);

        //HOOKS
        btnLogin = findViewById(R.id.btnLoginLG);
        btnSingup = findViewById(R.id.btnSinUpLG);
        edtUser = findViewById(R.id.edtUserlg);
        edtPass = findViewById(R.id.edtPasslg);


        btnSingup.setOnClickListener((View view) -> {
            Intent i = new Intent(Login.this, SingUp.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener((View view) -> {
            login(baseUrl);
        });
    }


    public void login(String URL) {

        String usuario = "usuarios?username=" + edtUser.getText().toString();
        String url = URL + usuario;
        Log.i("URL", url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String nombre, usuario, clave;


                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            int size = jsonArray.length();

                            for (int i = 0; i < size; i++) {
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                if (jsonObject.getString("usuario").equals(edtUser.getText().toString())) {
                                    if (jsonObject.getString("clave").equals(edtPass.getText().toString())) {
                                        Toast.makeText(Login.this, "¡INICIO DE SESION EXITOSO!", Toast.LENGTH_SHORT).show();
                                        Intent j = new Intent(Login.this, Income_earn.class);
                                        String name = jsonObject.getString("nombre");
                                        j.putExtra("nombre", name);
                                        startActivity(j);
                                    }
                                }
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
                        Toast.makeText(Login.this, "Error al loguearse...", Toast.LENGTH_SHORT).show();
                        System.out.println("----------------------------");
                        System.out.println("ERROR: " + error.getMessage());
                        System.out.println("----------------------------");
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }


//    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, response -> {
//        JSONObject jsonObject;
//        for (int i = 0; i < response.length(); i++) {
//            try {
//                jsonObject = (JSONObject) response.getJSONObject(i);
//                if (edtUser.getText().toString().equals(jsonObject.getString("usuario")) && edtPass.getText().toString().equals(jsonObject.getString("clave"))) {
//
//                    Intent j = new Intent(Login.this, Income_earn.class);
//                    j.putExtra("usuarios", jsonObject.getString("usuarios"));
//                    j.putExtra("clave", jsonObject.getString("clave"));
//                    startActivity(j);
//                }
//            } catch (JSONException e) {
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.e("Error", e.getMessage());
//            }
//        }
//    }, error ->
//            Toast.makeText(getApplicationContext(), "ERROR DE CONEXION",
//                    Toast.LENGTH_SHORT).show()
//    );
//        requestQueue.add(jsonArrayRequest);
//}


}