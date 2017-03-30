package com.zh.young.uselistview;

import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
       ListView listView = (ListView) findViewById(R.id.listview);
        MyAdapter myAdapter = new MyAdapter();

        listView.setAdapter(myAdapter);
        listView.setEmptyView(findViewById(R.id.image_view));
//        listView.setSelection(4);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
               if(firstVisibleItem + visibleItemCount == totalItemCount){
                   Toast.makeText(MainActivity.this, "滑到底了", Toast.LENGTH_SHORT).show();
               }
            }
        });



    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;  //作为后面使用的变量，提前声明出来
            //如果convertView == null 那么一定是第一次使用，那么就需要做初始化工作，这个初始化工作，就在这个if里面做
            if(convertView == null){
                convertView = View.inflate(getApplicationContext(),R.layout.listview_item,null);
                viewHolder = new ViewHolder();
                viewHolder.tv_test = (TextView) convertView.findViewById(R.id.tv_test);
                viewHolder.btn_test = (Button) convertView.findViewById(R.id.btn_test);
                viewHolder.ll = (LinearLayout) convertView.findViewById(R.id.ll);
                //将获取奥的控件ID都存入到convertView中，下次就可以直接使用了
                convertView.setTag(viewHolder);

            }else{
                //这里就可以从convertView里面讲viewHolder取出来，就避免了再次进行深度遍历了
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "hahah", Toast.LENGTH_SHORT).show();
                }
            });
            //然后就可以对view进行操作了
            return convertView;
        }

        private class ViewHolder{
            LinearLayout ll;
            TextView tv_test;
            Button btn_test;
        }
    }


}
