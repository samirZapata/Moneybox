package co.edu.usbbog.moneybox.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.usbbog.moneybox.R;

public class Presentation_screen extends AppCompatActivity {


    private static int SPLASH_SCREEN = 5000;

    Animation topAnimation, bottomAnimation;
    ImageView img;
    TextView logo, slogan;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //ANIMATIONS
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //HOOKS
        img = findViewById(R.id.img);
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);

        //SET ANIMATIONS
        img.setAnimation(topAnimation);
        logo.setAnimation(bottomAnimation);
        slogan.setAnimation(bottomAnimation);


        new Handler().postDelayed(() -> {

            onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
            boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

            if (isFirstTime) {
                SharedPreferences.Editor editor = onBoardingScreen.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();

                Intent i = new Intent(Presentation_screen.this, Walkthrough.class);
                startActivity(i);
                finish();
            }
            else {
                Intent i = new Intent(Presentation_screen.this, login.class);
                startActivity(i);
                finish();

            }


        }, SPLASH_SCREEN);


    }
}