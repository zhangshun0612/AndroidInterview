package com.example.zhangshun.androiddialogdemo;

/*
该例子用于探讨android对话框相关内容

Android 的两种对话框：PopupWindow 和 AlertDialog
它们都可以实现弹窗功能，但是他们之间有一些差别

AlertDialog 是非阻塞式的对话框，弹出后，后台还可以做事情
PopupWindow 是阻塞式的对话框，弹出时，程序等待，只有调用dismiss方法后，程序才会向下执行

若想要PopupWindow不响应back键，则在pop = new PopupWindow(popView, 400, 350, true); 或 pop.setFocusable(true);
这样back键不会响应
 */

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int selectedIndex = 0;

    private Button alertDialogButton;
    private Button popupWindowButton;
    private PopupWindow pop = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertDialogButton = (Button) findViewById(R.id.alert_dialog_button);
        alertDialogButton.setOnClickListener(this);

        popupWindowButton = (Button) findViewById(R.id.popup_window_button);
        popupWindowButton.setOnClickListener(this);
    }


    private void setupAlertDialog(){
        final String[] arrayFruit = new String[] { "苹果", "橘子", "草莓", "香蕉" };

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("选择喜欢吃的水果")
                .setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(arrayFruit, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedIndex = i;
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "你选择了：" + arrayFruit[selectedIndex], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        alertDialog.show();

        Log.d("MainActivity", "AlertDialog 弹窗弹出后，所执行");
    }

    private void setupPopupWindow()
    {
        View popView = LayoutInflater.from(this).inflate(R.layout.popwindow_view, null);

        pop = new PopupWindow(popView, 400, 350);
        pop.setFocusable(true);

        Button cancelButton = popView.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });

        pop.showAtLocation(popupWindowButton, Gravity.CENTER, 0, 0);
        Log.d("MainActivity", "PopupWindow 弹窗弹出后，所执行");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alert_dialog_button:
                setupAlertDialog();
                break;
            case R.id.popup_window_button:
                setupPopupWindow();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if(pop != null){
            pop.dismiss();
        }
        super.onDestroy();
    }
}
