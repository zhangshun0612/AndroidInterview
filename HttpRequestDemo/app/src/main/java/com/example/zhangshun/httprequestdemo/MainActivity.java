package com.example.zhangshun.httprequestdemo;

/*
该例子用来演示http请求的相关内容

一次完整的Http请求所经历的7个步骤
1. 建立Tcp连接
2. App向Web服务器发送请求命令
3. App发送请求头信息
4. Web服务器应答
5. Web服务器发送应答头信息
6. Web服务器向App发送数据
7. Web服务器关闭Tcp连接

常用的网络请求方式：HttpClient HttpURLConnection OkHttp Volley
HttpClient 已经不推荐了
HttpURLConnection 是一种对用途、轻量级的HTTP客户端，使用它来进行HTTP操作可以适用于大多数应用程序
OkHttp 是一种现代、快速、高效的Http Client，非常高效，支持SPDY、 连接池、GZIP和Http缓存
Volley 是一个简化网络任务的库。负责处理请求，加载，缓存，线程，同步等问题
       Volley在Android2.3以上的版本使用的是HttpURLConnection, 而在其以下则是使用的HttpClient，现在Volley已经停止了更新
Glide 一个被google所推荐的图片加载库，


 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final private String site = "http://7xi8d6.com1.z0.glb.clouddn.com/20180129074038_O3ydq4_Screenshot.jpeg";

    private ImageView imageView;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private Handler mHandler;
    private Bitmap bitmap;

    final private int BITMAP_TYPE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image_view);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == BITMAP_TYPE) {
                    imageView.setImageBitmap((Bitmap) msg.obj);
                }
                return false;
            }
        });

    }


    private void httpUrlConnectionTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(site);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setRequestProperty("charset", "UTF-8");

                    if(connection.getResponseCode() == 200){
                        InputStream in = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(in);
                        Message msg = new Message();
                        msg.what = BITMAP_TYPE;
                        msg.obj = bitmap;

                        mHandler.sendMessage(msg);
                    }else{
                        Toast.makeText(getApplicationContext(), "HttpURLConnection 无法获取图片", Toast.LENGTH_SHORT).show();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }

    private void okHttpTask()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request   = new Request.Builder().url(site).build();

                Response response = null;

                try {
                    response = client.newCall(request).execute();
                    if(response.isSuccessful()){
                        InputStream in = response.body().byteStream();
                        bitmap = BitmapFactory.decodeStream(in);

                        Message msg = new Message();
                        msg.what = BITMAP_TYPE;
                        msg.obj = bitmap;

                        mHandler.sendMessage(msg);
                    }else{
                        Toast.makeText(getApplicationContext(), "OkHttp 无法获取图片", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void glideTask()
    {
        Glide.with(getApplicationContext())
                .load(site)
                .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                .error(R.mipmap.ic_launcher)//图片加载失败后，显示的图片
                .into(imageView);
    }

    private void picassoTask()
    {
        Picasso.with(getApplicationContext())
                .load(site)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                httpUrlConnectionTask();
                break;
            case R.id.button2:
                okHttpTask();
                break;
            case R.id.button3:
                glideTask();
                break;
            case R.id.button4:
                picassoTask();
                break;
        }
    }
}
