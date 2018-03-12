package com.example.zhangshun.activitycomdemo;

/**
 * Created by ZhangShun on 2018/3/9.
 */

public class ValueUntils {

    private static ValueUntils mInstance = null;
    private String value;

    private ValueUntils(){

    }

    public  static  ValueUntils  getInstance(){
        synchronized (ValueUntils.class){
            if(mInstance == null){
                mInstance = new ValueUntils();
            }
        }

        return mInstance;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
