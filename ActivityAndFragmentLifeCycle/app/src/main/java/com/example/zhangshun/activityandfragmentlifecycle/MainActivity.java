package com.example.zhangshun.activityandfragmentlifecycle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/*
该项目用于探讨Activity 和 Fragment的生命周期问题
对于Activity的生命周期
onCreate --> onStart --> onResume --> onPause --> onStop --> onDestroy

对于Fragment的生命周期
onAttach --> onCreate --> onCreateView --> onStart --> onResume
--> onPause --> onStop --> onDestroyView --> onDestroyView --> onDestroy --> onDetach


 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager mFragmentManager;
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate Called");

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fg_layout ,new ContentFragment());
        fragmentTransaction.commit();

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop Called");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy Called");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fg_layout, new AnotherFragment());
                fragmentTransaction.commit();
                break;
            case R.id.button2:
                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MainActivity.this);
                normalDialog.setTitle("这是一个Dialog");
                normalDialog.setCancelable(false);
                normalDialog.show();
                break;
        }

    }
}
