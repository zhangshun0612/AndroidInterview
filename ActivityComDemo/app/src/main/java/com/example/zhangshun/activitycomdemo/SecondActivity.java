package com.example.zhangshun.activitycomdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    private TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String value1 = bundle.getString("value");

        tv1.setText(value1);

        String value2 = MainActivity.value;
        tv2.setText(value2);

        String value3 = ValueUntils.getInstance().getValue();
        tv3.setText(value3);
    }
}
