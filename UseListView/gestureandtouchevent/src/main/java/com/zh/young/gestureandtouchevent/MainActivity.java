package com.zh.young.gestureandtouchevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view){
        startActivity(new Intent(this,SecondActivity.class));
    }

    //1、new出来一个GestureDetector，这个是主要目的，然后重写Listener的接口中的方法
    GestureDetector mGestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {  //按下出发触发
//            Log.i(TAG,"down");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {//在按下一段时间的时候触发这个事件，这个事件比LongPress触发的实践要短，O(∩_∩)O
//            Log.i(TAG,"Press");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {  //轻触抬起动作触发，长时间按下抬起是不触发的哦
//            Log.i(TAG,"SingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {  //在用户滑动屏幕的时候触发
//            Log.i(TAG,"Scroll");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {//这个是最持久的，时间最长的，坚持住！
//            Log.i(TAG,"LongPress");
        }

        //这个是用户的滑屏的动作 I am fling!  其实做手势操作，这一个callback足够应付了
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            Log.i(TAG,"Fling");
            //1. 既然要处理横向滑动事件，那么就要判断是不是在横向滑动呢？这里就不去判断Y方向的误差了，如果想要user更爽，还是判断比较好哈
            float startX = e1.getX();
            float endX = e2.getX();
//            Log.i(TAG,"startX = " + startX + "-------" + "endX = " + endX);
            /*
            * 03-29 16:51:26.508 4274-4274/com.zh.young.gestureandtouchevent I/MainActivity: startX = 106.09497-------endX = 796.6846  向右滑动的结果
            * 03-29 16:51:31.825 4274-4274/com.zh.young.gestureandtouchevent I/MainActivity: startX = 781.68823-------endX = 235.19531 向左滑动的结果
            * 可以看出规律了，向右滑动endX的值比较大，向左滑动的时候endX的值比较小，那么我们就可以根据这个来判断用户是向哪滑动了，进而去响应事件
            * */
/*            if(startX < endX)
                Log.i(TAG,"向右滑动");
            else if(startX > endX)
                Log.i(TAG,"向左滑动");*/
            //这样，我们规定，如果滑动的大小超过了100px，那么我们就让它滑向另外一个页面  如果scrollDistance大于0，那么走向左边的页面，否则滑向右边的页面，这个你可以自己动手试一试就理解了
            int scrollDistance = (int) (endX - startX);
            if(scrollDistance < 100){

                startActivity(new Intent(MainActivity.this,SecondActivity.class));
                //解释一下这个方法的意义，就是为上面的这两个Activity的切换提供的动画效果，左右滑动效果，如果有兴趣呢，可以看一下API，
                //并且了解一下Android的坐标系
                overridePendingTransition(R.anim.right_enter,R.anim.left_out);

            }
            return true;
        }
    });

    //2、这么写，我猜是TouchEvent的接受事件的优先级较高，是不是这样呢？还没看过源码呢
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //3、将事件传递给GestureDetector来处理掉
        return mGestureDetector.onTouchEvent(event);
    }




}
