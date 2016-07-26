package lzh.dongruan.com.news.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lzh.dongruan.com.news.Base.BaseActivity;
import lzh.dongruan.com.news.DB.NewsDb;
import lzh.dongruan.com.news.DB.NewsDbHelper;
import lzh.dongruan.com.news.Fragment.Home;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.MyItem;
import lzh.dongruan.com.news.Until.News;
import lzh.dongruan.com.news.adapter.CollectionAdapter;
import lzh.dongruan.com.news.adapter.NewAdapter;

/**
 * Created by lzh220 on 2016/7/21.
 */
public class Collection extends BaseActivity {
    RecyclerView recyclerView;
    CollectionAdapter myadapter;
    List<News> list;
    NewsDb newsDb;
    SwipeRefreshLayout swipeRefreshLayout;
    int count=0;
    @Override
    public void setLayout() {
        setContentView(R.layout.info_pager);
    }

    @Override
    public void indata() {

    }

    @Override
    public void getview() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        list = new ArrayList<News>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        recyclerView.addItemDecoration(new MyItem(this, LinearLayoutManager
                .HORIZONTAL, 5, Color.TRANSPARENT));
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.sr);
        swipeRefreshLayout.setColorSchemeResources(R.color.one,R.color.two,R.color.three,R.color.four);

    }

    @Override
    public void setview() {
         loadData();
        count+=1;
        myadapter.setRecycleListener(new CollectionAdapter.RecycleListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent=new Intent(Collection.this, WebViewActivity.class);
                Bundle b=new Bundle();
                b.putString("url",list.get(position).getUrl());
                intent.putExtras(b);
                startActivity(intent);
            }

            @Override
            public void OnItemLongClick(View view, int position) {

                    newsDb.delete(list.get(position));
                    myadapter.remove(position);


            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (count != 0) {
                    swipeRefreshLayout.setRefreshing(false);
                    loadData();
                    Toast.makeText(Collection.this, "刷新成功", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public  void loadData(){
        newsDb = new NewsDb(this);
        list=new ArrayList<News>();
        list=newsDb.findall();
        myadapter=new CollectionAdapter(this);
        myadapter.setList(list);
        recyclerView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent i = new Intent(Collection.this, Home.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
