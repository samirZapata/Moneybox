package co.edu.usbbog.moneybox.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import co.edu.usbbog.moneybox.R;
import co.edu.usbbog.moneybox.helperclasses.SliderAdapter;

public class Walkthrough extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dots;
    SliderAdapter sliderAdapter;
    TextView[] points;
    Button btnStart, btnNext;
    Animation animation;
    int currentPosition;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_walkthrough);

        //HOOKS
        viewPager = findViewById(R.id.walk);
        dots = findViewById(R.id.dots);
        btnStart = findViewById(R.id.btnEmpezar);
        btnNext = findViewById(R.id.btnNext);


        //CALL ADAPTER
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);

        btnStart.setOnClickListener((View view) -> {
            Intent i = new Intent(Walkthrough.this, Login.class);
            startActivity(i);
            finish();
        });

    }

    public void next(){
        viewPager.setCurrentItem(currentPosition+1);
    }

    private void addDots(int position) {

        points = new TextView[3];
        dots.removeAllViews();

        for (int i = 0; i < points.length; i++) {
            points[i] = new TextView(this);
            points[i].setText(Html.fromHtml("&#8226;"));
            points[i].setTextSize(35);

            dots.addView(points[i]);
            viewPager.addOnPageChangeListener(changeListener);
        }

        if (points.length > 0) {
            points[position].setTextColor(getResources().getColor(R.color.purple_200));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;

            //SET BUTTON VISIBLE N' INVISIBLE
            if (position == 0) {
                btnStart.setVisibility(View.INVISIBLE);
            }
            else if (position == 1) {
                btnStart.setVisibility(View.INVISIBLE);
            }
            else if (position == 2) {
                animation = AnimationUtils.loadAnimation(Walkthrough.this, R.anim.bottom_animation);
                btnStart.setAnimation(animation);
                btnStart.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}