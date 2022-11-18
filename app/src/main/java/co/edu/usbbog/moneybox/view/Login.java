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
import co.edu.usbbog.moneybox.model.UsuariosDTO;

public class Login extends AppCompatActivity {

    Button btnSingup, btnLogin;
    EditText edtUser, edtPass;
    RequestQueue requestQueue;
    TextView scope;
    ConnAdapter conAux;
    SharedPreferences Income_earn;
    SharedPreferences onBoardingScreen;
    UsuariosDTO objectDto;
    Intent j;

    //private final String baseUrl = conAux.ip();

    private final String usbURl = "http://172.17.3.114:3300/";
    private final String baseUrl = "http://172.17.3.114:3300/";
    private final String getDepositURL = "http://192.168.0.6:3300/";

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
        objectDto = new UsuariosDTO();


        btnSingup.setOnClickListener((View view) -> {
            Intent i = new Intent(Login.this, SingUp.class);
            startActivity(i);
        });

        //DECLARE OBJECT TO IDENTIFY IF THE USER USE THE APP FOR FIRST TIME OF SECOND TIME
        /*SHAREDPREFERENCES SAVE A VARIABLE IN THE LOCAL STORAGE WITH ALL DATA IN THIS VARIABLE, BEFORE
        * I CAN GET THIS VARIABLE IN ANY ACTIVITY, SINCE THE DATA ARE PERSINTENCY WE HAVE TO REMOVE THEM WHEN
        * THEY AREN'T NECESSARY */

//        onBoardingScreen = getSharedPreferences("loginToDash", MODE_PRIVATE);
//        boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

        btnLogin.setOnClickListener((View view) -> {
            login(baseUrl);
//            if (isFirstTime) {
//                SharedPreferences.Editor editor = onBoardingScreen.edit();
//                editor.putBoolean("firstTime", false);
//                editor.commit();
//

//                startActivity(i);
//                finish();
//            }
//            else {
//                Intent i = new Intent(Login.this, Dashboard.class);
//                getDeposit(baseUrl);
//                startActivity(i);
//                finish();
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

                                        //= new Intent(Login.this, Income_earn.class);
                                        String name = jsonObject.getString("nombre");
                                        String usuario = jsonObject.getString("usuario");
                                        String id = jsonObject.getString("id");
                                        //Toast.makeText(Login.this, "¡Bienvenido! " + name, Toast.LENGTH_SHORT).show();

                                        // --> STORING USER DATA WITH SHARED PREFERENCES
//                                        SharedPreferences idPrefer = getSharedPreferences("objects", MODE_PRIVATE);
//                                        SharedPreferences.Editor objectEditor = idPrefer.edit();
//                                        objectEditor.putString("objectId", id);
//                                        objectEditor.putString("name", name);
//                                        objectEditor.putString("usuario", usuario);
//                                        objectEditor.commit();
//                                        objectEditor.remove("objects");

                                        j = new Intent(Login.this, Income_earn.class);

                                        j.putExtra("nombre", name);
                                        j.putExtra("usuario", usuario);
                                        j.putExtra("id", id);
                                        startActivity(j);
                                        finish();
                                    }else{
                                        Toast.makeText(Login.this, "¡Usuario o contraseña incorrectos!", Toast.LENGTH_SHORT).show();
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


//    private void getDeposit(String URL) {
//
//        // --> GETTING USER DATA FROM LOCAL STORAGE
//        SharedPreferences objectsPrefer = getSharedPreferences("objects", MODE_PRIVATE);
//        String ID = objectsPrefer.getString("objectId", "");
//        String name = objectsPrefer.getString("name", "");
//        String userPrefer = objectsPrefer.getString("usuario", "");
//        SharedPreferences.Editor objectsEditor = objectsPrefer.edit();
//        objectsEditor.commit();
//        objectsEditor.remove("objects");
//
//
////        Intent ids;
////        ids = getIntent();
////        String id = ids.getStringExtra("objectId");
//        String depositID = "ingresos?username=" + userPrefer;
//
//        String url = URL + depositID;
//        Log.i("URL", url);
//
//        JsonObjectRequest depositRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                url,
//                null,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("data");
//                            int size = jsonArray.length();
//
//                            for (int i = 0; i < size; i++) {
//                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
//                                if (jsonObject.getString("valor") != null) {
//
//                                    String valor = jsonObject.getString("valor");
//
//                                    // --> STORING USER BALANCE
//                                    SharedPreferences idPrefer = getSharedPreferences("objectBalance", MODE_PRIVATE);
//                                    SharedPreferences.Editor objectEditor = idPrefer.edit();
//                                    objectEditor.putString("mainBalance", valor);
//                                    objectEditor.commit();
//                                    objectEditor.remove("mainBalance");
//
////                                    Intent deposit = new Intent();
////                                    //assert deposit != null;
////                                    deposit.putExtra("balance", valor);
//                                }
//                            }
//                        } catch (JSONException e) {
//
//                            System.out.println("ERROR: " + e.getMessage());
//                            e.printStackTrace();
//
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Login.this, "Error al cargar ingresos...", Toast.LENGTH_SHORT).show();
//                        System.out.println("----------------------------");
//                        System.out.println("ERROR: " + error.getMessage());
//                        System.out.println("----------------------------");
//                    }
//                });
//        requestQueue.add(depositRequest);
//
//    }


}