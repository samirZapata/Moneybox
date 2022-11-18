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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.model.UsuariosDTO;

public class Dashboard extends AppCompatActivity {

    private final String getDepositURL = "http://192.168.0.4:3300/";

    Button btnIF, btnIN, btnViewBill, btnIE, btnAH;
    TextView viewUSR, viewIngresos;

    EditText edtGFijo, edtPeriodo, edtValor;


    Intent i;
    RequestQueue requestQueue;
    BottomSheetDialog bottomSheetDialog;
    String valor;
    boolean isFirstTime;

    UsuariosDTO idDto;

//    String usr, user, id, usuario, ID, name, userPrefer;
    String nombre, usuario, id, balance;
    SharedPreferences.Editor objectsEditor, objectValEditor;
    String objectBalance;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //HOOKS
        btnIF = findViewById(R.id.btnGF);
        btnIE = findViewById(R.id.btnIE);
        btnAH = findViewById(R.id.btnAH);
        btnViewBill = findViewById(R.id.btnVG);
        viewUSR = findViewById(R.id.viewUserName);
        viewIngresos = findViewById(R.id.viewIngresos);

        idDto = new UsuariosDTO();


        i = getIntent();
        nombre = i.getStringExtra("nombre"); //usr
        usuario = i.getStringExtra("usuario"); //user
        valor = i.getStringExtra("valor"); //valor
        id = i.getStringExtra("id");

        viewIngresos.setText(valor);

        usuario = i.getStringExtra("usuario");


//        SharedPreferences objectsPrefer = getSharedPreferences("objects", MODE_PRIVATE);
//        ID = objectsPrefer.getString("id", "");
//        name = objectsPrefer.getString("name", "");
//        userPrefer = objectsPrefer.getString("usuario", "");
//        objectsEditor = objectsPrefer.edit();
//        objectsEditor.commit();
//        objectsEditor.remove("objects");
//
//        SharedPreferences objectValor = getSharedPreferences("objectBalance", MODE_PRIVATE);
//        objectBalance = objectValor.getString("mainBalance", "$ 0.00");
//        objectValEditor = objectValor.edit();
//        objectValEditor.commit();
//        objectValEditor.remove("objectBalance");


//        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
//        String fomateado = decimalFormat.format(objectBalance);

        viewIngresos.setText("$ " + valor);
        viewUSR.setText("Hola, " + nombre);
        //objectValEditor.remove("mainBalance");

        bottomSheetDialog = new BottomSheetDialog(this);
        //btnIF.setOnClickListener(view -> new Bottom_sheet().show(getSupportFragmentManager(),"Show"));

        listeners();

    }


    private void listeners() {
        btnIF.setOnClickListener((View v) -> {
            Intent i = new Intent(Dashboard.this, Input_Bill.class);
            i.putExtra("nombre", nombre);
            i.putExtra("cash", valor);
            System.out.println("VALOR ENVIADO" + valor);
            i.putExtra("id", id);
            startActivity(i);
            finish();
        });

        viewUSR.setOnClickListener((View view) -> {
            Intent perfil = new Intent(Dashboard.this, Perfil.class);
            perfil.putExtra("usuario", usuario);
            perfil.putExtra("nombre", nombre);
            startActivity(perfil);
        });

        btnViewBill.setOnClickListener((View view) -> {
            Intent vBill = new Intent(Dashboard.this, Show_bills.class);
            vBill.putExtra("nombre", nombre);
            vBill.putExtra("id", id);
            vBill.putExtra("cash", valor);

            System.out.println("ENVIANDO " + nombre + " " + id + " " + valor);

            startActivity(vBill);
        });


        btnIE.setOnClickListener((View view) -> {
            i = new Intent(Dashboard.this, ExtraIncome.class);
            startActivity(i);
            finish();
        });

        btnAH.setOnClickListener((View view) -> {
            i = new Intent(Dashboard.this, SaveMoney.class);
            startActivity(i);
        });

    }

    private void getDeposit(String URL, String usuario) {

        String depositID = "ingresos?usuario=" + usuario;

        String url = URL + depositID;
        Log.i("URL", url);

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
                                if (jsonObject.getString("valor") != null) {

                                    String balance = jsonObject.getString("valor");
                                    viewIngresos.setText(balance);
//                                    Intent deposit;
//                                    deposit = null;
//                                    String valor = jsonObject.getString("valor");
//                                    deposit.putExtra("valor", valor
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
                        Toast.makeText(Dashboard.this, "Error al cargar ingresos...", Toast.LENGTH_SHORT).show();
                        System.out.println("----------------------------");
                        System.out.println("ERROR: " + error.getMessage());
                        System.out.println("----------------------------");
                    }
                });
        requestQueue.add(depositRequest);
    }


}