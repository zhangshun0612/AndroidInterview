package com.example.zhangshun.adnroidanimationdemo;

/*
该例子用来展示，Android 属性动画相关的内容
Android 实现动画的方式有3中
1. 帧动画：将一个完整的动画拆分成一张一张单独的图片，然后再讲它们连贯起来进行播放
   特点：由于是一帧一帧的，需要大量图片来完成一个动画，会增大apk的大小，而且很难实现比较复杂的效果

2. 补间动画：慢慢过渡，设置初值和末值，并用差值器来控制过渡
   特点：相对比较简单，页面切换的动画多用这个来做。缺点，视觉上变化，并不是真正的位置变化。

3. 属性动画：控制属性来实现动画
   特点：最为强大的动画，弥补了补间动画的确定，实现位置 + 视觉的变化。并且可以自定义差值器，实现各种效果
 */

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.id.button3;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animator.AnimatorListener {

    private ImageView imageView;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private ObjectAnimator animator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image_view);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                animator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f);
                /*
                这样的话只执行一次
                animator = ObjectAnimator.ofFloat(imageView, "alpha", 0f);
                */
                animator.setDuration(1000); //动画时间
                animator.setRepeatCount(2); //重复执行次数
                animator.start(); //异步过程，不会阻塞
                break;
            case R.id.button2:
                animator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, -300f, 0f);
                animator.setDuration(2000);
                animator.start();
                break;
            case R.id.button3:
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f, 0f);
                animator.setDuration(2000);
                animator.start();
                break;
            case R.id.button4:
                ObjectAnimator animator0 = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2f, 1f);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f, 1f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "translationX", 0f, -300f, 0f);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f, 0f);
                AnimatorSet set = new AnimatorSet();
                set.play(animator1).with(animator2).with(animator3).after(animator0);
                set.setDuration(3000);
                //为动画添加监听事件
                set.addListener(this);
                set.start();
                break;
            case R.id.button5:
                Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_set);
                animator.setTarget(imageView);
                animator.start();
                break;
        }
    }

    @Override
    public void onAnimationStart(Animator animator) {
        Log.d("MainActivity", "Animation Start");
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        Log.d("MainActivity", "Animation End");
    }

    @Override
    public void onAnimationCancel(Animator animator) {
        Log.d("MainActivity", "Animation Cancel");
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
        Log.d("MainActivity", "Animation Repeat");
    }
}
