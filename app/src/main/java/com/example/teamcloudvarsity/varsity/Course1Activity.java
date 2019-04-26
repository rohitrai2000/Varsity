package com.example.teamcloudvarsity.varsity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Course1Activity extends Activity {


    private TextView topic1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_1);


        topic1 = findViewById(R.id.Topic_1);
        topic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Course1Activity.this,Course11Activity.class));
            }
        });
    }
}
