package com.example.citytourtravelapp.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

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

import com.example.citytourtravelapp.R;
import com.example.citytourtravelapp.HelperClass.SliderAdapter;
import com.example.citytourtravelapp.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter adapter;
    TextView[] dots;

    Button letsGetStarted, nextBtn;

    Animation animation;
    int currentPosition = 0;

    public void skip(View view) {

        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();

    }

    public void next(View view) {
        if (currentPosition < dots.length - 1) {
            viewPager.setCurrentItem(currentPosition + 1);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);


        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);

        //Call adapter
        adapter = new SliderAdapter(this);
        viewPager.setAdapter(adapter);
        letsGetStarted = findViewById(R.id.get_started);
        nextBtn = findViewById(R.id.next_btn);


        addDots(0);


        viewPager.addOnPageChangeListener(changeListener);


    }



    private void addDots(int position) {


        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
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

            if (position == dots.length - 1) {
                //Here we add animation to our bbotton
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            } else {
                letsGetStarted.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}