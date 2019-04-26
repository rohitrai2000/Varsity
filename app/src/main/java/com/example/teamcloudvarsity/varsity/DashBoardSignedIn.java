package com.example.teamcloudvarsity.varsity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class DashBoardSignedIn extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] Images= {R.drawable.beast};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private ImageButton imgBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_signed_in);

        SharedPreferences sp = getSharedPreferences("out", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("key", 2);
        editor.apply();


        init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.twotone_menu_white_24);

        imgBtn1 = findViewById(R.id.Course_1_Image);

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoardSignedIn.this,Course1Activity.class));
            }
        });


        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();

                        if (id == R.id.nav_enrolled_courses) {

                        } else if (id == R.id.nav_courses) {

                        }else if (id == R.id.nav_books) {

                        }
                        else if (id == R.id.nav_clarity) {

                        }
                        else if (id == R.id.nav_settings) {

                        }
                        else if (id == R.id.nav_sign_out) {

                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(DashBoardSignedIn.this,DashBoard.class);
                            startActivity(intent);
                            SharedPreferences sp = getSharedPreferences("out", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("key", 0);
                            editor.apply();
                            finish();

                        }
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        for(int i=0;i<Images.length;i++)
            ImagesArray.add(Images[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(DashBoardSignedIn.this,ImagesArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == Images.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            //preventing default implementation previous to
            //android.os.Build.VERSION_CODES.ECLAIR
            //   return false;
            // setContentView(R.layout.activity_home);
            /*Intent homeIntent = new Intent(DoctorHome.this, DoctorHome.class);
            startActivity(homeIntent);*/
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
