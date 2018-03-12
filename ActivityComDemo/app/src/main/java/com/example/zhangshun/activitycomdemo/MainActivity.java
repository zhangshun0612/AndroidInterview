package com.example.zhangshun.activitycomdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
本例子用于说明Activity之间 3 中传参的方式
1. 通过Intent传递参数
2. 通过类中的静态变量传递参数
3. 借助全局变量来传递参数
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String value;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //通过Intent传入参数
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("value", "这是从Intent传入的字符串");
        intent.putExtras(bundle);

        //通过静态变量传入参数
        MainActivity.value = "这是从静态变量传入的字符串";

        //通过全局变量传入参数
        ValueUntils utils = ValueUntils.getInstance();
        utils.setValue("这是从一个全局的单例模式传入的字符串");

        startActivity(intent);
    }
}
