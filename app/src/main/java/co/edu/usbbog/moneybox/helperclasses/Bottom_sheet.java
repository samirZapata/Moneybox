package co.edu.usbbog.moneybox.helperclasses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import co.edu.usbbog.moneybox.R;

public class Bottom_sheet extends BottomSheetDialogFragment {

    private final String baseURL = "http://localhost:3000/clasificacion";



    public Bottom_sheet() {
    }

    private void init() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_item, container, false);
        
        return view;

    }
}
