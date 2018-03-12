package com.example.zhangshun.activitylaunchmodedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SingleInstanceLaunchActivity extends AppCompatActivity implements View.OnClickListener {


    private Button standardButton;
    private Button singleTaskButton;
    private Button singleTopButton;
    private Button singleInstanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance_launch);

        standardButton = (Button) findViewById(R.id.standardLaunchBtn);
        singleTaskButton = (Button) findViewById(R.id.singleTaskLaunchBtn);
        singleTopButton = (Button) findViewById(R.id.singleTopLaunchBtn);
        singleInstanceButton = (Button) findViewById(R.id.singleInstanceLaunchBtn);

        standardButton.setOnClickListener(this);
        singleTaskButton.setOnClickListener(this);
        singleTopButton.setOnClickListener(this);
        singleInstanceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.standardLaunchBtn:
                intent = new Intent(SingleInstanceLaunchActivity.this, StandardLaunchActivity.class);
                startActivity(intent);
                break;
            case R.id.singleTaskLaunchBtn:
                intent = new Intent(SingleInstanceLaunchActivity.this, SingleTaskLaunchActivity.class);
                startActivity(intent);
                break;
            case R.id.singleTopLaunchBtn:
                intent = new Intent(SingleInstanceLaunchActivity.this, SingleTopLaunchActivity.class);
                startActivity(intent);
                break;
            case R.id.singleInstanceLaunchBtn:
                intent = new Intent(SingleInstanceLaunchActivity.this, SingleInstanceLaunchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
