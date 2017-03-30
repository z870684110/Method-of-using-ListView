package com.zh.young.usetoolbar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private ObjectAnimator alpha;
    private AnimatorSet animatorSet;
    private ObjectAnimator translationY;
    private int mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);

        initAnimation();

    }

    private void initAnimation() {
        translationY = ObjectAnimator.ofFloat(toolbar, "TranslationY", -150);
        translationY.setDuration(500);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE :
                int newY = (int) event.getY();

                if(newY - mLastY > 0){
                    translationY.start();
                }

                mLastY = newY;
                break;
            case MotionEvent.ACTION_UP :
                break;
        }
        return true;
    }
}
