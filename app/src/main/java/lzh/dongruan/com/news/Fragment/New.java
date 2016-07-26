package lzh.dongruan.com.news.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lzh.dongruan.com.news.Activity.WebViewActivity;
import lzh.dongruan.com.news.Base.BaseFragment;
import lzh.dongruan.com.news.Http.VolleySingleton;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.Mipmap;
import lzh.dongruan.com.news.Until.MyItem;
import lzh.dongruan.com.news.Until.News;
import lzh.dongruan.com.news.adapter.NewAdapter;

/**
 * Created by lzh220 on 2016/6/30.
 */
public class New extends BaseFragment {
    View view;
    int count=0;
    RecyclerView recyclerView;
    NewAdapter myadapter;
    String path;
    SwipeRefreshLayout swipeRefreshLayout;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    List<News> list;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Log.i("msg", list.toString());
                    Log.i("msg", "list进来了");
                    myadapter=new NewAdapter(getContext());
                    myadapter.setRecycleListener(new NewAdapter.RecycleListener() {
                        @Override
                        public void OnItemClick(View view, int position) {
                            Intent intent = new Intent(getActivity(), WebViewActivity.class);
                            Bundle b = new Bundle();
                            News news=list.get(position);
                            b.putSerializable("news",news);
                            b.putString("url", list.get(position).getUrl());
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(myadapter);
                    myadapter.setList(list);
                    myadapter.notifyDataSetChanged();
                    count+=1;
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                      @Override
                      public void onRefresh() {
                          if(count!=0){
                              swipeRefreshLayout.setRefreshing(false);
                              loadData();
                              Toast.makeText(getActivity(),"刷新成功",Toast.LENGTH_SHORT).show();
                          }

                  }
                  });
                    break;
            }
        }
    };
    @Override
    public View setLayout(LayoutInflater inflater) {
        view=inflater.inflate(R.layout.info_pager,null);
        return view;
    }

    @Override
    public void indata() {

    }

    @Override
    public void getview(View view) {
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.sr);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        list = new ArrayList<News>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置分割线
        recyclerView.addItemDecoration(new MyItem(getContext(), LinearLayoutManager
                .HORIZONTAL, 5, Color.TRANSPARENT));
       swipeRefreshLayout.setColorSchemeResources(R.color.one,R.color.two,R.color.three,R.color.four);

//        getData();
    }

    @Override
    public void setview() {
        loadData();


    }

    @Override
    public void onClick(View v) {

    }
    public void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getData();

            }
        }).start();
    }
    public void getData(){
        Log.i("msg", "获取数据");
        StringRequest st=new StringRequest(Request.Method.GET,getPath(),new Response.Listener<String>(){

            @Override
            public void onResponse(String s) {
                Log.i("msg",s);
                News newInfp = null;
                try {
                        JSONObject jo=new JSONObject(s);
                        JSONObject joresult = jo.getJSONObject("result");
                        JSONArray array=joresult.getJSONArray("data");
                        for (int i=0;i<array.length();i++){
                            newInfp=new Gson().fromJson(array.getJSONObject(i).toString(),News.class);
                            list.add(newInfp);

                        }
                    handler.sendEmptyMessage(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("msg","错误"+getPath());
            }
        });
        VolleySingleton.getVolleySingleton(getActivity()).addToRequestQueue(st);
    }


}