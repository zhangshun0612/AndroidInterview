package com.example.zhangshun.useservicedemo;

/*
该例子探讨Service的使用方法
以及Service的生命周期


关于Service的启动周期：
对于使用startService启动，生命周期为：
onCreate ---> onStartCommand ---> onDestroy

对于使用bindService启动，生命周期为
Service: onCreate ---> onBind ---> onUnBind ---> onDestroy
ServiceConnection: bind后，执行onServiceConnected， 一般不会执行onServiceDisconnected,只有在Service丢失，进程被迫中止时才会调用


关于Activity和Service的数据交换
第1种:
Activity通过startService中的Intent将数据传入Service，Service通过startCommand接口获取数据
第2种:
对于bindService启动方式，使用ServiceConnection 所返回的Binder来进行通信，通过Binder中的方法来设置和改变Service中的数据
第3种:
通过事件监听模式，即回调的方法进行通信与数据交换

 */

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean firstServiceStartFlag = false;
    private boolean secondServiceStartFlag = false;

    private Button firstServiceButton;
    private Button secondServiceButton;
    private ProgressBar progressBar;

    private Intent firstServiceIntent;
    private Intent secondServiceIntent;

    SecondServiceConnection secondConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstServiceButton = (Button) findViewById(R.id.first_service_start);
        secondServiceButton = (Button) findViewById(R.id.second_service_start);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        firstServiceButton.setOnClickListener(this);
        secondServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_service_start:
                if(!firstServiceStartFlag)
                {
                    firstServiceIntent = new Intent(MainActivity.this, FirstService.class);
                    startService(firstServiceIntent);
                    firstServiceButton.setText("以stop方式停止一个Service");
                    firstServiceStartFlag = true;
                }else{
                    stopService(firstServiceIntent);
                    firstServiceButton.setText("以start方式启动一个Service");
                    firstServiceStartFlag = false;
                }
                break;
            case R.id.second_service_start:
                if(!secondServiceStartFlag)
                {
                    secondServiceIntent = new Intent(MainActivity.this, SecondService.class);
                    secondConn = new SecondServiceConnection();
                    bindService(secondServiceIntent, secondConn, BIND_AUTO_CREATE);
                    secondServiceButton.setText("以unbind方式停止一个Service");
                    secondServiceStartFlag = true;
                }else{
                    unbindService(secondConn);
                    secondServiceButton.setText("以bind方式启动一个Service");
                    secondServiceStartFlag = false;
                }
                break;
        }

    }

    private class SecondServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("SecondServiceConnection", "onServiceConnected Called");

            SecondService.SecondBinder binder = (SecondService.SecondBinder) iBinder;

            binder.setProgressEventCallback(new SecondService.onProgressEvent() {
                @Override
                public void progressChanged(int progress) {
                    progressBar.setProgress(progress);
                }
            });
            binder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("SecondServiceConnection", "onServiceDisconnected Called");
        }
    }
}
