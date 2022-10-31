package co.edu.usbbog.moneybox.helperclasses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import co.edu.usbbog.moneybox.R;

public class Bottom_sheet extends BottomSheetDialogFragment {

    private final String baseURL = "http://192.168.0.2:3300/clasificacion";
    EditText edtGfijo, edtPeriodo, edtValor;
    RequestQueue requestQueue;
    Button btnIN;

    public Bottom_sheet() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_item, container, false);

//        //HOOKS
//        btnIN = view.findViewById(R.id.btnIndicarGasto);
//        edtGfijo = view.findViewById(R.id.edtGFijo);
//        edtPeriodo = view.findViewById(R.id.edtPeriodo);
//        edtValor = view.findViewById(R.id.edtValor);
//
//
//        btnIN.setOnClickListener((View v) -> {
//
//            gastos();
//            Intent i = new Intent(getContext(), Show_bills.class);
//            startActivity(i);
//
//        });
//
//
        return view;
//    }
//
//
//    private void gastos() {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseURL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //Toast.makeText(Bottom_sheet.this, "Se ha insertado", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "Se ha insertado", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("error", error + "");
//            }
//        }) {
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> parametros = new HashMap<String, String>();
//
//                String concepto = edtGfijo.getText().toString();
//                String periodo = edtPeriodo.getText().toString();
//                String valor = edtValor.getText().toString();
//
//                System.out.println("CONCEPTO" + concepto);
//                System.out.println("PERIODO" + periodo);
//                System.out.println("VALOR" + valor);
//
//
//                parametros.put("tipo", concepto);
//                parametros.put("periodo", periodo);
//                parametros.put("valor", valor);
//
//                Log.i("PARAMETROS", parametros.toString());
//                return parametros;
//            }
//        };
//        //Volley volley = null;
//        requestQueue.add(stringRequest);
//        Log.i("DATOS", stringRequest + "");
//    }
    }
}

