package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
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

public class SingUp extends AppCompatActivity {

    EditText edtName, edtUser, edtPass, edtPhone, edtEmail;
    Button btnSend, btnLogin;
    TextView txtHead, txtDesc;

    RequestQueue requestQueue;


    private final String baseUrl = "http://172.17.3.114:3300/usuarios";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //HOOKS
        btnSend = findViewById(R.id.btnNextSG);
        btnLogin = findViewById(R.id.btnLoginSG);
        txtHead = findViewById(R.id.txtHead);
        txtDesc = findViewById(R.id.txtDesc);

        edtName = findViewById(R.id.edtName);
        edtUser = findViewById(R.id.edtUserSG);
        edtPass = findViewById(R.id.edtPassSG);
        edtPhone = findViewById(R.id.edtPhoneSG);
        edtEmail = findViewById(R.id.edtEmailSG);

        requestQueue = Volley.newRequestQueue(this);

        btnLogin.setOnClickListener((View vie) -> {
            Intent i = new Intent(SingUp.this, Login.class);
            startActivity(i);
        });


        btnSend.setOnClickListener((View view) -> {
//            String nombre = edtName.getText().toString();
//            String phone = edtPhone.getText().toString();
//            String email = edtEmail.getText().toString();
//            String usuario = edtUser.getText().toString();
//            String clave = edtPass.getText().toString();

            //System.out.print("LO DE LOS CAMPOS "+ nombre + phone + email + usuario + clave);
            //saveUsers(nombre, phone, email, usuario, clave);

            IngresarUsuario(baseUrl);
        });
    }

    public void callNextSG(View view) {

        Intent i = new Intent(SingUp.this, SingUpFinish.class);

        Pair[] pairs = new Pair[4];

        //SET ANIMATION
        pairs[0] = new Pair<View, String>(btnLogin, "transition_login");
        pairs[1] = new Pair<View, String>(btnSend, "transition_next");
        pairs[2] = new Pair<View, String>(txtHead, "transition_title");
        pairs[3] = new Pair<View, String>(txtDesc, "transition_desc");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SingUp.this, pairs);
            startActivity(i, options.toBundle());
        }
        else {
            startActivity(i);
        }

    }


//    public UsuariosDTO createRequest() {
//        UsuariosDTO userRequest = new UsuariosDTO();
//
////        String nombre = edtName.getText().toString();
////        String usuario = edtUser.getText().toString();
////        String clave = edtPass.getText().toString();
////        String phone = edtPhone.getText().toString();
////        String email = edtEmail.getText().toString();
//
//        userRequest.setNombre(edtName.getText().toString());
//        userRequest.setTelefono(edtUser.getText().toString());
//        userRequest.setEmail(edtPass.getText().toString());
//        userRequest.setUsuario(edtPhone.getText().toString());
//        userRequest.setClave(edtEmail.getText().toString());
//
//
//        return userRequest;
//    }

//    public void saveUsers(UsuariosDTO userRequest) {
//        Call<UsuariosDTO> responseCall = ApiClient.getUserService().createUsers(userRequest);
//        responseCall.enqueue(new Callback<UsuariosDTO>() {
//            @Override
//            public void onResponse(Call<UsuariosDTO> call, Response<UsuariosDTO> response) {
//
//                if (response.isSuccessful()) {
//                    Toast.makeText(SingUp.this, "Saved", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(SingUp.this, "Request Failed", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UsuariosDTO> call, Throwable t) {
//                Toast.makeText(SingUp.this, "Saved"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void listeners() {


    }

    private void clearTXT() {
        edtName.setText("");
        edtPhone.setText("");
        edtEmail.setText("");
        edtUser.setText("");
        edtPass.setText("");
    }

    public void saveUsers(String nombre, String telefono, String email, String usuario, String clave) {
        Log.i("Campo Nombre", nombre);
        Log.i("Campo Phone", telefono);
        Log.i("Campo Email", email);
        Log.i("Campo User", usuario);
        Log.i("Campo Pass", clave);

        String url = baseUrl + "usuarios";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//
                        Toast.makeText(SingUp.this, "Usuario insertado", Toast.LENGTH_SHORT).show();
                        System.out.print("-------------------------------------------------------------------------");
                        System.out.print("RESPUESTA " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SingUp.this, error.getMessage() + "", Toast.LENGTH_LONG).show();
                        System.out.print(error);
                        //Log.i("ERROR", error.getMessage());
                    }
                }) {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre);
                params.put("telefono", telefono);
                params.put("email", email);
                params.put("usuario", usuario);
                params.put("clave", clave);
                return params;
            }
        };
        requestQueue.add(postRequest);
    }


    private void IngresarUsuario(String baseUrl) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SingUp.this, "Se ha insertado", Toast.LENGTH_SHORT).show();
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


                Map<String, String> params = new HashMap<String, String>();
                String nombre = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();
                String usuario = edtUser.getText().toString();
                String clave = edtPass.getText().toString();

                params.put("nombre", nombre);
                params.put("telefono", phone);
                params.put("email", email);
                params.put("usuario", usuario);
                params.put("clave", clave);


                Log.i("Campo Nombre", nombre);
                Log.i("Campo Phone", phone);
                Log.i("Campo Email", email);
                Log.i("Campo User", usuario);
                Log.i("Campo Pass", clave);
                Log.i("fsfsd", params.toString());
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}