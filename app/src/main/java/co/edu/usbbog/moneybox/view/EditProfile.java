package co.edu.usbbog.moneybox.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class EditProfile extends AppCompatActivity {

    private final String baseURL = "http://192.168.0.6:3300/";
    private EditText edtName, edtPhoneSG, edtEmailSG;
    private Button btnEditar, btnEliminar;

    Intent intent;
    String username;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //HOOKS
        edtName = findViewById(R.id.edtNameEP);
        edtPhoneSG = findViewById(R.id.edtPhoneEP);
        edtEmailSG = findViewById(R.id.edtEmailEP);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        intent = getIntent();
        username = intent.getStringExtra("usuario");


        getData();
        listeners();

    }


    private void getData() {
        String name = intent.getStringExtra("nombre");
        String phone = intent.getStringExtra("telefono");
        String mail = intent.getStringExtra("email");

        edtName.setText(name);
        edtPhoneSG.setText(phone);
        edtEmailSG.setText(mail);
    }


    private void listeners() {

        buscar(username);

        this.btnEditar.setOnClickListener(view -> {
            modificar(
                    username,
                    edtName.getText().toString(),
                    edtPhoneSG.getText().toString(),
                    edtEmailSG.getText().toString()
            );
        });

        this.btnEliminar.setOnClickListener(view -> {
            eliminar(username);
        });
    }


    private void buscar(String username) {
        String uri = String.format(baseURL + "usuarios?usuario=%1$s", username);
        Log.i("URL", uri);
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
            Toast.makeText(EditProfile.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
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
            Toast.makeText(EditProfile.this, "Usuario modificado", Toast.LENGTH_LONG).show();
            edtName.setText("");
            edtPhoneSG.setText("");
            edtEmailSG.setText("");
            hideKeyboard(this);
        }, error -> {
            Toast.makeText(EditProfile.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
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
            Toast.makeText(EditProfile.this, "Usuario eliminado", Toast.LENGTH_LONG).show();
            edtName.setText("");
            edtPhoneSG.setText("");
            edtEmailSG.setText("");
            hideKeyboard(this);
        }, error -> {
            Toast.makeText(EditProfile.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
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