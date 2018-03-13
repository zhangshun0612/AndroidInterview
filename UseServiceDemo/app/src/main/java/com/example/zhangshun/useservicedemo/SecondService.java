package com.example.zhangshun.useservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SecondService extends Service {

    public static final int MAX_PROGRESS = 100;

    private int progress = 0;
    private boolean progressRunning = false;

    private onProgressEvent eventCallback = null;

    public SecondService() {
    }

    public void addProgressEventCallback(onProgressEvent event){
        eventCallback = event;
    }

    @Override
    public void onCreate() {
        Log.d("SecondService", "onCreate Called");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("SecondService", "onBind Called");
        return new SecondBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("SecondService", "onUnbind Called");
        progressRunning = false;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("SecondService", "onDestroy Called");
        super.onDestroy();
    }


    private void startDownLoad() {
        progressRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress < MAX_PROGRESS && progressRunning){

                    progress += 1;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(eventCallback != null){
                       eventCallback.progressChanged(progress);
                    }
                }

                if(!progressRunning){
                    if(eventCallback != null){
                        eventCallback.progressChanged(0);
                    }
                }

            }
        }).start();
    }

    public class SecondBinder extends Binder {

        public void startDownload(){
            Log.d("SecondBinder", "startDownload Called");
            startDownLoad();
        }

        public void setProgressEventCallback(onProgressEvent event){
            addProgressEventCallback(event);
        }
    }

    public interface onProgressEvent {
        void progressChanged(int progress);
    }
}
