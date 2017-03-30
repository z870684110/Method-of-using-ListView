package com.zh.young.mutipitemlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListViewItemDataType[] datas;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initAdapter();
        setListView();

    }

    private void setListView() {
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(myAdapter);
    }

    private void initAdapter() {
        myAdapter = new MyAdapter();
    }

    private void initData() {
        //数据OK了
        //==========================================================
        ListViewItemDataType data1 = new ListViewItemDataType();
        data1.data = "hello";
        data1.type = ConstantValue.LEFT;

        ListViewItemDataType data2 = new ListViewItemDataType();
        data2.data = "hello";
        data2.type = ConstantValue.RIGHT;

        ListViewItemDataType data3 = new ListViewItemDataType();
        data3.data = "额，还是用中文吧，词穷了～";
        data3.type = ConstantValue.LEFT;

        datas = new ListViewItemDataType[]{
                data1, data2,data3
        };
        //==========================================================
            //left item & right item 准备好了
        //==========================================================
        //获取适配器 继承自BaseAdapter

    }


    private class MyAdapter extends BaseAdapter{

        //下面这两个方法就是多条目必写方法哦
        //=============================================

        //这个方法看一下源码（Ctrl+鼠标右键）,返回的是0，那么我们要多一类，那么很容易想到用另外一个数据代表那个条目，刚刚我们设置的Data中的type就是
        //用在这里的
        //datas 是我把申请的三个条目的数据对象放到了数组中，为了方便
        @Override
        public int getItemViewType(int position) {
            return datas[position].type;
        }

        //这个方法看一下源码,返回的结果是1，这个代表什么呢，相信聪明的你已经猜到了，对，就是view的类型的个数，那么我们要做的就是
        //在返回的结果上加1，当然也可以直接设置条目的类型数，下面采用第二种
        @Override
        public int getViewTypeCount() {
            return 2;
        }
        //============================================
        @Override
        public int getCount() {
            return datas.length;
        }

        //这里你要理解一下什么叫做方法重写，重写的要求是什么，不要以为重写就是方法头全部相同，这种认为是不正确滴（虽然一起我也是这么认为的），
        //这里就修改了返回值的类型，但是要记住，返回值类型尅进行修改，但是必须是其子类才可以，这样就避免了在获取条目之后还需要强制类型转换
        @Override
        public ListViewItemDataType getItem(int position) {

            return datas[position];
        }

        //随便写写啦，直接填position
        @Override
        public long getItemId(int position) {
            return position;
        }

        //OK终于到重点了，坚持看下去
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //这里使用ViewHolder和convertView的组合，如果你还是不清楚啥意思，去看我的复习Note02,那里面有详细的解释
            ViewHolder viewHolder;
            //在这里你有没有一个疑问？convertView在多条目的时候是怎么判断为null的？是不是只要有一个条目，那么convertView都不会为null呢？
            //其实我也有这个疑问，所以带着这个疑问，查看了源码，在注释中这么写（位置在AbsListView->adapter->getView）
            /**
             * @param convertView The old view to reuse, if possible. Note: You should check that this view
             *        is non-null and of an appropriate type before using. If it is not possible to convert
             *        this view to display the correct data, this method can create a new view.
             *        Heterogeneous lists can specify their number of view types, so that this View is
             *        always of the right type (see {@link #getViewTypeCount()} and
             *        {@link #getItemViewType(int)}).
             */
            //还是没有解决问题啊，那再查一下getItemViewType(int)
            /**
             * Get the type of View that will be created by {@link #getView} for the specified item.
             */
            //看上面这一句，getItemViewType(int)决定了getView创建条目的类型，那么至此我们解决了这个问题，因为我们在getItemViewType(int)已经写上了获取的方法
            //所以这里就可以像一个条目那么写了，不过多了几个else
            if(convertView == null){
                //当是左边的条目的时候，那么加载左边
                if(getItemViewType(position) == ConstantValue.LEFT){
                    convertView = View.inflate(getApplicationContext(),R.layout.left_item,null);

                    //当是右边的条目的时候，那么加载右边
                }else if(getItemViewType(position) == ConstantValue.RIGHT){
                    convertView = View.inflate(getApplicationContext(),R.layout.right_item,null);
                }

                //这两个写在外面就是为了简化代码
                viewHolder = new ViewHolder();
                viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);

                convertView.setTag(viewHolder);

            }else{
                viewHolder = (ViewHolder)convertView.getTag();

            }



            viewHolder.tv_content.setText(getItem(position).data);
            return convertView;
        }

        private class ViewHolder {
            TextView tv_content;
        }

    }
}
