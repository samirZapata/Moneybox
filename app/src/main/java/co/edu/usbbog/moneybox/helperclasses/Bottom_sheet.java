package co.edu.usbbog.moneybox.helperclasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.view.Dashboard;
import co.edu.usbbog.moneybox.view.Income_earn;
import co.edu.usbbog.moneybox.view.Show_bills;

public class Bottom_sheet extends BottomSheetDialogFragment {

    private final String baseURL = "http://localhost:3000/clasificacion";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = inflater.inflate(R.layout.add_item, container, false);


        Button btnIN = view.findViewById(R.id.btnIndicarGasto);

        btnIN.setOnClickListener((View v)->{
            Toast.makeText(getContext(), "PRUEBA", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), Show_bills.class);
            startActivity(i);

        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
