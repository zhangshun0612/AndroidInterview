package com.example.zhangshun.multithreaddemo;
/*
该例子用于演示Android下的多线程使用、异步执行等情况

AsyncTask 提供了一个封装好的Thread Handler模型
其中 doInBackground 方法，运行在另一个非UI的线程中
通过几个回调函数
    onPreExecute
    onProgressUpdate
    onPostExecute
    onCancelled
    等方法与UI线程进行交互

对于Thread 执行异步多线程任务，使用Handler与UI线程进行交互，使用Handler 的 post 方法，post方法中的Runnable参数的run()中可以执行UI更新等任务，这个Runnable中的内容是在主UI线程中运行的

 */

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button cancelButton;
    private TextView textView;
    private ProgressBar progressBar;

    private RadioButton radioButton1;
    private RadioButton radioButton2;

    private DemoAsyncTask asyncTask = null;
    private DemoThread thread = null;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        radioButton1 = (RadioButton) findViewById(R.id.radio1_btn);
        radioButton2 = (RadioButton) findViewById(R.id.radio2_btn);

        textView = (TextView) findViewById(R.id.text_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        button.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                if(radioButton1.isChecked()) {
                    asyncTask = new DemoAsyncTask();
                    asyncTask.execute("Photo 1");
                    button.setEnabled(false);
                }else{
                    thread = new DemoThread();
                    thread.execute("Photo 1");
                    button.setEnabled(false);
                }
                break;
            case R.id.cancel_button:
                if(asyncTask != null){
                    asyncTask.cancel(false); //如果参数为true的话，会直接终结线程，为false的话，通过isCancelled 自己判断然后退出
                }else if(thread != null){
                    thread.cancel();
                }
                break;
        }


    }


    private class DemoAsyncTask extends AsyncTask<String , Integer, Boolean> {

        private String title;
        @Override
        protected Boolean doInBackground(String... strings) {
            title = strings[0];

            for(int i = 0 ; i <= 100; i++){
                publishProgress(i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(isCancelled()){
                    Log.d("DemoAsyncTask", "cancelled during do in background");
                    return false;
                }
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            String text = title + ":" + String.valueOf(values[0]) + "%";
            textView.setText(text);

            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            button.setEnabled(true);
            Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(MainActivity.this, "取消下载", Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            textView.setText("");
            button.setEnabled(true);
        }
    }

    private class DemoThread extends Thread {

        private String mTitle;
        private boolean isCancelled = false;
        private int progressCnt = 0;

        public void execute(String title){
            this.mTitle = title;
            this.isCancelled = false;
            start();
        }

        public void cancel(){
            isCancelled = true;
        }

        @Override
        public void run() {
            for(int i = 0 ; i < 100; i++){

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        String text = mTitle + ":" + String.valueOf(progressCnt) + "%";
                        textView.setText(text);

                        progressBar.setProgress(progressCnt);
                    }
                });

                if(isCancelled)
                {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "取消下载", Toast.LENGTH_SHORT).show();
                            progressBar.setProgress(0);
                            textView.setText("");
                            button.setEnabled(true);
                        }
                    });
                    return;
                }
                progressCnt++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    button.setEnabled(true);
                    Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
