package com.example.citytourtravelapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.citytourtravelapp.R;
import com.google.android.material.navigation.NavigationView;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final float END_SCALE = 0.7f;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    ImageView menuIcon;

    LinearLayout content;


    //DrawerMenu
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_user_dashboard);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        content=findViewById(R.id.contentAll);

        //Navigation Drawer
        navigationDrawer();

    }

    //Navigation Drawer Functions

    void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navigation_view);


        //Para cuando se haga click en el imagen del menu
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        animateNavigationDrawer();

    }


    //Para la animacion
    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
               //Scale thye view
                final float diffScaledOffset=slideOffset*(1-END_SCALE);
                final float offsetScale=1-diffScaledOffset;
                content.setScaleX(offsetScale);
                content.setScaleY(offsetScale);


                final float xOffset=drawerView.getWidth() *slideOffset;
                final float xOffsetDiff=content.getWidth()*diffScaledOffset;
                final float xTranslation=xOffset- xOffsetDiff;
                content.setTranslationX(xTranslation);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });
    }


    //Para cuando se presione hacia atras
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Aqui es donde manejamos todos los eventos onCLick de el menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}