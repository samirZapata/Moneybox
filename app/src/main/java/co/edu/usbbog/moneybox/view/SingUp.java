package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.edu.usbbog.moneybox.R;

public class SingUp extends AppCompatActivity {

    Button btnNext, btnLogin;
    TextView txtHead, txtDesc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //HOOKS
        btnNext = findViewById(R.id.btnNextSG);
        btnLogin = findViewById(R.id.btnLoginSG);
        txtHead = findViewById(R.id.txtHead);
        txtDesc = findViewById(R.id.txtDesc);


        btnLogin.setOnClickListener((View vie) -> {
            Intent i = new Intent(SingUp.this, login.class);
            startActivity(i);
        });
    }

    public void callNextSG(View view) {

        Intent i = new Intent(SingUp.this, SingUpFinish.class);

        Pair[] pairs = new Pair[4];

        //SET ANIMATION
        pairs[0] = new Pair<View, String>(btnLogin, "transition_login");
        pairs[1] = new Pair<View, String>(btnNext, "transition_next");
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
}