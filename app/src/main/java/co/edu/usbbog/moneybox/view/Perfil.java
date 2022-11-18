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
import android.widget.TextView;
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

public class Perfil extends AppCompatActivity {
    private final String baseURL = "http://172.17.3.114:3300/";

    private EditText edtName;
    private EditText edtPhoneSG;
    private EditText edtEmailSG;
    private Button btnBuscar;
    private Button btnModificar;

    private TextView viewMainUser, viewNombre, viewTelefono,
            viewMail, viewSecondUser, viewPass, Ucharacter, btnEliminar;

    private Intent intent;
    private String usuario;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //HOOKS
        viewNombre = findViewById(R.id.viewNombre);
        viewTelefono = findViewById(R.id.viewTelefono);
        viewMail = findViewById(R.id.viewMail);
        viewSecondUser = findViewById(R.id.viewUser);
        viewPass = findViewById(R.id.viewPass);
        viewMainUser = findViewById(R.id.viewMainUsr);
        Ucharacter = findViewById(R.id.App);
        btnModificar = findViewById(R.id.btnModificar);
        btnEliminar = findViewById(R.id.btnEliminarAC);

        listeners();

        intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        String nombreUser = intent.getStringExtra("nombre");
        String character = nombreUser;
        int n = 1;
        String cNumber = Nchar(character, n);
        Ucharacter.setText(cNumber);
        viewMainUser.setText(nombreUser);
        buscar(usuario);


        btnModificar.setOnClickListener((View view) -> {
            intent = new Intent(Perfil.this, EditProfile.class);
            intent.putExtra("usuario", usuario);
            buscar(usuario);
            startActivity(intent);
        });

    }


    public static String Nchar(String str, int n) {
        if (str == null) {
            return null;
        }
        return str.substring(0, Math.min(str.length(), n));
    }

    private void listeners() {

        this.btnEliminar.setOnClickListener(view -> {
            eliminar(usuario);
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
                        viewNombre.setText(jsonObject.getString("nombre"));
                        viewTelefono.setText(jsonObject.getString("telefono"));
                        viewMail.setText(jsonObject.getString("email"));
                        viewSecondUser.setText(jsonObject.getString("usuario"));
                        viewPass.setText(jsonObject.getString("clave"));
                        viewMainUser.setText(jsonObject.getString("nombre"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //hideKeyboard(this);
        }, error -> {
            Toast.makeText(Perfil.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
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


    private void eliminar(String username) {
        String uri = String.format(baseURL + "usuarios/%1$s", username);

        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, uri, response -> {
            Toast.makeText(Perfil.this, "Usuario eliminado", Toast.LENGTH_LONG).show();
            hideKeyboard(this);
        }, error -> {
            Toast.makeText(Perfil.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
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