package co.edu.usbbog.moneybox.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.helperclasses.ConnAdapter;

public class Login extends AppCompatActivity {

    Button btnSingup, btnLogin;
    EditText edtUser, edtPass;
    RequestQueue requestQueue;
    TextView scope;
    ConnAdapter conAux;
    SharedPreferences Income_earn;

    //private final String baseUrl = conAux.ip();
    private final String baseUrl = "http://192.168.0.2:3300/";
    private final String getDepositURL = "http://192.168.0.2:3300/";

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
        scope = findViewById(R.id.scope);


        btnSingup.setOnClickListener((View view) -> {
            Intent i = new Intent(Login.this, SingUp.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener((View view) -> {

            login(baseUrl);

//            new Handler().postDelayed(()->{
//
//            });

//            Income_earn = getSharedPreferences("Income_earn", MODE_PRIVATE);
//            boolean isFirstTime = Income_earn.getBoolean("firstTime", true);
//
//            if (isFirstTime) {
//                SharedPreferences.Editor editor = Income_earn.edit();
//                editor.putBoolean("firstTime", false);
//                editor.commit();
//
//                Intent k = new Intent(this, Income_earn.class);
//                startActivity(k);
//            }
//            else {
//                getDeposit(getDepositURL);
//                Intent l = new Intent(this, Dashboard.class);
//                startActivity(l);
//            }
        });

        scope.setOnClickListener(view -> {
            String texto = "En esta entrega se mostrará el inicio de sesión y las \noperaciones básicas CRUD de usuarios de manera funcional. ";
            scope.setText(texto);
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
                                        String usuario = jsonObject.getString("usuario");
                                        String id = jsonObject.getString("id");

                                        j.putExtra("nombre", name);
                                        j.putExtra("usuario", usuario);
                                        j.putExtra("id", id);
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


    private void getDeposit(String URL) {

        Intent ids;
        ids = getIntent();
        String id = ids.getStringExtra("id");
        String depositID = "ingresos?usuario=" + id;

        String url = URL + depositID;
        Log.i("URL", url);
        //Log.i("ID", id);

        JsonObjectRequest depositRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("deposit");
                            int size = jsonArray.length();

                            for (int i = 0; i < size; i++) {
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                if (jsonObject.getString("valor").equals(depositID)) {

                                    Intent deposit;
                                    deposit = null;
                                    String valor = jsonObject.getString("valor");
                                    deposit.putExtra("valor", valor);
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
                        Toast.makeText(Login.this, "Error al cargar ingresos...", Toast.LENGTH_SHORT).show();
                        System.out.println("----------------------------");
                        System.out.println("ERROR: " + error.getMessage());
                        System.out.println("----------------------------");
                    }
                });
        requestQueue.add(depositRequest);

    }


}