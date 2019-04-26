package com.example.teamcloudvarsity.varsity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

    private static int splash_time_out = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("out", Activity.MODE_PRIVATE);
                int key_value = sp.getInt("key", -1);


                if (key_value == 1) {
                    Intent homeIntent = new Intent(Splash.this, DashBoard.class);
                    startActivity(homeIntent);
                    finish();
                } else if (key_value == 2) {
                    Intent homeIntent = new Intent(Splash.this, DashBoardSignedIn.class);
                    startActivity(homeIntent);
                    finish();
                } else {
                    Intent homeIntent = new Intent(Splash.this, DashBoard.class);
                    startActivity(homeIntent);
                    finish();
                }


            }
        }, splash_time_out);
    }
}

