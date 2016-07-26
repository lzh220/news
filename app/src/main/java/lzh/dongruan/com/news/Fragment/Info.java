package lzh.dongruan.com.news.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lzh.dongruan.com.news.Activity.TopActivity;
import lzh.dongruan.com.news.Base.BaseFragment;



import lzh.dongruan.com.news.Http.Tab;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.SPFManager;
import lzh.dongruan.com.news.adapter.Adapter;

/**
 * Created by lzh220 on 2016/6/30.
 */
public class Info extends BaseFragment {
    View view;
    ViewPager viewpager;
    List<Fragment> list;
    Adapter adapter;
    List<String> liststring;
    TabLayout tab;
    List<Tab> tablist;
    ImageView add_img;
    SPFManager spfManager=new SPFManager(getContext());


    public void info(){
        tablist = new ArrayList<Tab>();
        tablist.add(new Tab("头条", "http://v.juhe.cn/toutiao/index?type=top&key=9a3d127136ad916de7070328b52560ae"));
        tablist.add(new Tab("军事","http://v.juhe.cn/toutiao/index?type=junshi&key=9a3d127136ad916de7070328b52560ae"));
        tablist.add(new Tab("国际","http://v.juhe.cn/toutiao/index?type=guoji&key=9a3d127136ad916de7070328b52560ae"));
        tablist.add(new Tab("体育","http://v.juhe.cn/toutiao/index?type=tiyu&key=9a3d127136ad916de7070328b52560ae"));
        tablist.add(new Tab("财经","http://v.juhe.cn/toutiao/index?type=caijing&key=9a3d127136ad916de7070328b52560ae"));
        tablist.add(new Tab("时尚", "http://v.juhe.cn/toutiao/index?type=shishang&key=9a3d127136ad916de7070328b52560ae"));
    }

    @Override
    public void onResume() {
        super.onResume();
        liststring = new ArrayList<String>();
        list = new ArrayList<Fragment>();
        liststring = spfManager.GetType(getContext());
        if(liststring==null){
            list=setFragment();
        }else {

            list = getType();
            Log.i("aaaaa",list.toString());
        }
        adapter=new Adapter(getFragmentManager(),list,liststring);
            viewpager.setAdapter(adapter);
        tab.setTabTextColors(Color.BLUE, Color.RED);
        tab.setupWithViewPager(viewpager);
        adapter.notifyDataSetChanged();
        Log.i("aaaaa", "2"+list.toString());
    }

    public List<Fragment> setFragment(){
        List<Fragment> list_one=new ArrayList<Fragment>();
        for(int i=0;i<tablist.size();i++){
            liststring.add(tablist.get(i).getName());
            New n = new New();
            n.setPath(tablist.get(i).getPath());
            list_one.add(n);
        }
        return list_one;
    }

    @Override
    public View setLayout(LayoutInflater inflater) {
        view=inflater.inflate(R.layout.info,null);
        info();
        return view;
    }

    @Override
    public void indata() {



    }

    @Override
    public void getview(View view) {
        viewpager= (ViewPager) view. findViewById(R.id.main_info_viewPager);
        tab= (TabLayout) view.findViewById(R.id.main_info_tablayout);
        add_img= (ImageView) view.findViewById(R.id.mian_info_addimg);


    }

    @Override
    public void setview() {

        add_img.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), TopActivity.class);
        startActivity(intent);

    }

    public List<Fragment> getType(){
        List<Fragment> fragmentList=new ArrayList<Fragment>();
        liststring= spfManager.GetType(getContext());
        for (int i =0;i<liststring.size();i++){
            for(int j =0 ;j<tablist.size();j++){
                if(liststring.get(i).equals(tablist.get(j).getName())){
                    New n = new New();
                    n.setPath(tablist.get(j).getPath());
                    fragmentList.add(n);
                }
            }
        }
        return  fragmentList;
    }
}
