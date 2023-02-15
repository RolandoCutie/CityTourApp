package com.example.citytourtravelapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.citytourtravelapp.R;
import com.example.citytourtravelapp.User.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIMER = 4000;

    ImageView backGroundImageView;
    TextView powerBy;

    //Animations
    Animation sideAnimation, buttonAnimation;

    SharedPreferences onBoardingScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.splash_screen);

        initView();

        //this function is taking cara that in some time it goins to another screen
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);
                if (isFirstTime) {
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);

                    //When whe put this whe finiish this activity and
                    // prevent when the user press back button its going
                    // to this splash screens

                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, SPLASH_SCREEN_TIMER);
    }

    private void initView() {
        //Hooks
        backGroundImageView = findViewById(R.id.background_imagen);
        powerBy = findViewById(R.id.power_by_line);

        //Animations
        sideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //SetAnimationsOn elements
        backGroundImageView.setAnimation(sideAnimation);
        powerBy.setAnimation(buttonAnimation);


    }
}