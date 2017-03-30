package com.zh.young.gestureandtouchevent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Created by young on 17-3-29.
 */

public class SecondActivity extends Activity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //1、获取VelocityTracker的实例
        VelocityTracker obtain = VelocityTracker.obtain();
        //2.将事件交给VelocityTracker处理
        obtain.addMovement(event);
        //3.添加计算 第一个参数需要说明一下 1 代表 每微秒滑动多少px,1000代表每秒滑动多少像素 参数2代表最大速度
        obtain.computeCurrentVelocity(1000,1000);
        float xVelocity = obtain.getXVelocity();
        Log.i(TAG,"mVelocityTracker : " +xVelocity);
        return true;
    }
}
