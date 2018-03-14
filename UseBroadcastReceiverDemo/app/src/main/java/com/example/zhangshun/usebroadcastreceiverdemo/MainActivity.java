package com.example.zhangshun.usebroadcastreceiverdemo;

/*
下面例子用来说明BroadcastReceiver的使用

BroadcastReceiver的注册：
FirstBroadcastReceiver 采用静态方式注册，即在Manifest中通过Intent-Filter 和 action 进行了注册
SecondBroadcastReceiver 采用动态方式注册，即通过调用registerReceiver方法来进行注册

 */

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendButton;

    private SecondBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new SecondBroadcastReceiver();

        sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);

        registerReceiver(broadcastReceiver, new IntentFilter("com.zhangshun.broadcast"));

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent("com.zhangshun.broadcast");
        sendBroadcast(intent);
    }


}
