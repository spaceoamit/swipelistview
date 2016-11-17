package com.swipelistview;

import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeMenuListView mListView;

    private ArrayList<String> mArrayList=new ArrayList<>();

    private ListDataAdapter mListDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
    }

    private void initControls() {

        mListView=(SwipeMenuListView)findViewById(R.id.listView);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        for (int i=0;i<5;i++){
            mArrayList.add("List item --> "+i);
        }
       // mListView.setCloseInterpolator(new BounceInterpolator());

        mListDataAdapter=new ListDataAdapter();
        mListView.setAdapter(mListDataAdapter);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                SwipeMenuItem goodItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                goodItem.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
                        0xF5)));
                // set item width
                goodItem.setWidth(dp2px(90));
                // set a icon
                goodItem.setIcon(R.drawable.ic_action_good);
                // add to menu
                menu.addMenuItem(goodItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_action_discard);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        Toast.makeText(MainActivity.this,"Like button press",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        mArrayList.remove(position);
                        mListDataAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"Item deleted",Toast.LENGTH_SHORT).show();

                        break;
                }
                return true;
            }
        });

        //mListView

        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }
        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            //TODO add item to list from here
            mArrayList.add("List item --> "+mArrayList.size());
            mListDataAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }




    class ListDataAdapter extends BaseAdapter {

        ViewHolder holder;
        @Override
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){

                holder=new ViewHolder();

                convertView=getLayoutInflater().inflate(R.layout.list_item,null);

                holder.mTextview=(TextView)convertView.findViewById(R.id.textView);

                convertView.setTag(holder);
            }else {

                holder= (ViewHolder) convertView.getTag();
            }

            holder.mTextview.setText(mArrayList.get(position));

            return convertView;
        }

        class ViewHolder {

            TextView mTextview;

        }
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
