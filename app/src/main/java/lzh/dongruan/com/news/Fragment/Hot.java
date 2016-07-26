package lzh.dongruan.com.news.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import lzh.dongruan.com.news.Until.News;
import lzh.dongruan.com.news.adapter.HotAdapter;

/**
 * Created by lzh220 on 2016/6/30.
 */
public class Hot extends BaseFragment {
    List<News> list;
    RecyclerView recyclerView;
    HotAdapter myadapter;
    View view;
    SwipeRefreshLayout swipeRefreshLayout;
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
        //三种样式
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
        list=new ArrayList<News>();
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.sr);
        swipeRefreshLayout.setColorSchemeResources(R.color.one,R.color.two,R.color.three,R.color.four);
//      recyclerView.setLayoutManager(new LinearLayoutManager(this));
//      recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        myadapter=new HotAdapter(getContext(),list);
        //设置分割线
//        recyclerView.addItemDecoration(new MyItem(getContext(), LinearLayoutManager
//                .HORIZONTAL,5, Color.TRANSPARENT));
        //监听
        //把recyclerView加到适配器里
//        recyclerView.setAdapter(myadapter);


    }

    @Override
    public void setview() {
        loadData();

    }

    @Override
    public void onClick(View v) {

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    myadapter=new HotAdapter(getContext());
                    myadapter.setRecycleListener(new HotAdapter.RecycleListener() {
                        @Override
                        public void OnItemClick(View view, int position) {
                            Intent intent=new Intent(getActivity(), WebViewActivity.class);
                            Bundle b=new Bundle();
                            b.putString("url",list.get(position).getUrl());
                            intent.putExtras(b);
                            startActivity(intent);
                        }

                        @Override
                        public void OnItemLongClick(View view, int position) {

                        }
                    });
                    recyclerView.setAdapter(myadapter);
                    myadapter.setList(list);
                    myadapter.notifyDataSetChanged();
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            if (list != null) {
                                loadData();
                                swipeRefreshLayout.setRefreshing(false);
                                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
            }
        }
    };
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
        StringRequest st=new StringRequest(Request.Method.GET,"http://v.juhe.cn/toutiao/index?type=top&key=9a3d127136ad916de7070328b52560ae",new Response.Listener<String>(){

            @Override
            public void onResponse(String s) {
                Log.i("msg",s);
                News newInfp = null;
                try {
                    JSONObject jo=new JSONObject(s);
                    JSONObject joresult = jo.getJSONObject("result");
                    JSONArray array=joresult.getJSONArray("data");
                    for (int i=0;i<8;i++){
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
            }
        });
        VolleySingleton.getVolleySingleton(getActivity()).addToRequestQueue(st);
    }
}
