package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import co.edu.usbbog.moneybox.R;

public class perfil extends AppCompatActivity {
    private final String baseURL = "http://192.168.0.3:3300/";

    private EditText edtName;
    private EditText edtPhoneSG;
    private EditText edtEmailSG;
    private Button btnBuscar;
    private Button btnModificar;
    private Button btnEliminar;

    private Intent intent;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        init();
        listeners();

        intent = getIntent();
        usuario = intent.getStringExtra("nombre");
    }

    private void init() {
        this.edtName = findViewById(R.id.edtName);
        this.edtName.requestFocus();
        this.edtPhoneSG = findViewById(R.id.edtPhoneSG);
        this.edtEmailSG = findViewById(R.id.edtEmailSG);
        this.btnBuscar = findViewById(R.id.btnBuscar);
        this.btnModificar = findViewById(R.id.btnModificar);
        this.btnEliminar = findViewById(R.id.btnEliminar);
    }

    private void listeners() {
        this.btnBuscar.setOnClickListener(view -> {
            buscar(usuario);
        });

        this.btnModificar.setOnClickListener(view -> {
            modificar(
                    usuario,
                    edtName.getText().toString(),
                    edtPhoneSG.getText().toString(),
                    edtEmailSG.getText().toString()
            );
        });

        this.btnEliminar.setOnClickListener(view -> {
            eliminar(usuario);
        });
    }

    private void buscar(String username) {
        String uri = String.format(baseURL + "usuarios?username=%1$s", username);
        Log.i("Usuario", username);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, uri, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("data");
                int size = jsonArray.length();
                for (int i = 0; i < size; i++) {
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                    if (jsonObject.getString("usuario").equals(username)) {
                        edtName.setText(jsonObject.getString("nombre"));
                        edtPhoneSG.setText(jsonObject.getString("telefono"));
                        edtEmailSG.setText(jsonObject.getString("email"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            hideKeyboard(this);
        }, error -> {
            Toast.makeText(perfil.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
            Log.e("nose", error + "");
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> query = new HashMap<>();
                query.put("username", username);

                return query;
            }
        };
        Volley.newRequestQueue(this).add(getRequest);
    }

    private void modificar(String username, String nombre, String telefono, String email) {
        String uri = String.format(baseURL + "usuarios/%1$s", username);

        StringRequest putRequest = new StringRequest(Request.Method.PUT, uri, response -> {
            Toast.makeText(perfil.this, "Usuario modificado", Toast.LENGTH_LONG).show();
            edtName.setText("");
            edtPhoneSG.setText("");
            edtEmailSG.setText("");
            hideKeyboard(this);
        }, error -> {
            Toast.makeText(perfil.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre);
                params.put("telefono", telefono);
                params.put("email", email);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(putRequest);
    }

    private void eliminar(String username) {
        String uri = String.format(baseURL + "usuarios/%1$s", username);

        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, uri, response -> {
            Toast.makeText(perfil.this, "Usuario eliminado", Toast.LENGTH_LONG).show();
            edtName.setText("");
            edtPhoneSG.setText("");
            edtEmailSG.setText("");
            hideKeyboard(this);
        }, error -> {
            Toast.makeText(perfil.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
        });
        Volley.newRequestQueue(this).add(deleteRequest);
    }

    private static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}