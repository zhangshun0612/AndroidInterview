package com.example.zhangshun.activitylaunchmodedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
该Demo用于演示4种不同的启动模式

模式1： standard模式（标准模式）
每次启动一个Activity,都创建一个Activity实例，并放入任务栈中
初始情况下：MainActivity --> StandardLaunchActivity --> StandardLaunchActivity
启动一次变为： MainActivity --> StandardLaunchActivity --> StandardLaunchActivity --> StandardLaunchActivity
按一次后退：MainActivity --> StandardLaunchActivity --> StandardLaunchActivity

模式2：singleTop模式 （唯一栈顶模式）
当启动一个Activity，如果是该Activity启动自己，则不需要创建，其余情况都要创建该Activity
初始情况下：MainActivity --> StandardLaunchActivity --> SingleTopLaunchActivity
启动一次变为：MainActivity --> StandardLaunchActivity --> SingleTopLaunchActivity
按一次后退：MainActivity --> StandardLaunchActivity --> SingleTopLaunchActivity

模式3：singleTask模式 （唯一任务模式）
当启动一个Activity，如果任务栈中没有该Activity，则创建一个，如果有该Activity，则将其放到栈顶，中间的所有Activity全部移除任务栈
初始情况下：MainActivity --> StandardLaunchActivity --> SingleTaskLaunchActivity --> StandardLaunchActivity --> StandardLaunchActivity
启动一次变为：MainActivity --> StandardLaunchActivity --> SingleTaskLaunchActivity
按一次后退：MainActivity --> StandardLaunchActivity

模式4：singleInstance模式 （系统唯一单例模式）
如果一个应用中启动了该Activity，应用2再启动时，将共享该Activity，整个系统只有一个单例的Activity

 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button standardButton;
    private Button singleTaskButton;
    private Button singleTopButton;
    private Button singleInstanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                intent = new Intent(MainActivity.this, StandardLaunchActivity.class);
                startActivity(intent);
                break;
            case R.id.singleTaskLaunchBtn:
                intent = new Intent(MainActivity.this, SingleTaskLaunchActivity.class);
                startActivity(intent);
                break;
            case R.id.singleTopLaunchBtn:
                intent = new Intent(MainActivity.this, SingleTopLaunchActivity.class);
                startActivity(intent);
                break;
            case R.id.singleInstanceLaunchBtn:
                intent = new Intent(MainActivity.this, SingleInstanceLaunchActivity.class);
                startActivity(intent);
                break;
        }

    }
}
