package lzh.dongruan.com.news.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import lzh.dongruan.com.news.Base.BaseActivity;
import lzh.dongruan.com.news.Fragment.Home;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Service.MyService;
import lzh.dongruan.com.news.Until.SPFManager;
import lzh.dongruan.com.news.Until.Set;
import lzh.dongruan.com.news.adapter.GridviewAdapter;

/**
 * Created by lzh220 on 2016/7/21.
 */
public class Setting extends BaseActivity {
    ToggleButton tab1;
    Intent intent;
    GridView gridview;
    ImageView left;
    List<Set> list;
    LinearLayout linearLayout;
    NotificationCompat.Builder nb;
    NotificationManager nm;
    GridviewAdapter gridviewAdapter;
    SPFManager spfManager = new SPFManager(this);

    @Override
    public void setLayout() {
        setContentView(R.layout.setting);
        addlSetlistcolor();
    }

    @Override
    public void indata() {

    }

    @Override
    public void getview() {
        nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        tab1= (ToggleButton) findViewById(R.id.setting_open);
        gridview= (GridView) findViewById(R.id.shezhi_gridview);
        left= (ImageView) findViewById(R.id.title_left);
        linearLayout= (LinearLayout) findViewById(R.id.title_color);
        intent=new Intent(this,MyService.class);
        Boolean music= spfManager.Get(this);
        tab1.setChecked(music);

    }

    @Override
    public void setview() {

        int a=spfManager.GetMessage();
        linearLayout.setBackgroundResource(a);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Setting.this, Home.class);
                startActivity(i);
                finish();
            }
        });
        tab1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Bundle bundle = new Bundle();
                if (isChecked) {
                    bundle.putInt("number", 1);
                    spfManager.Save(Setting.this, isChecked);
                    show();


                } else {
                    bundle.putInt("number", 2);
                    spfManager.Save(Setting.this, isChecked);
                    nm.cancel(0);


                }
                intent.putExtras(bundle);
                startService(intent);

            }
        });
        gridviewAdapter=new GridviewAdapter(list,this);
        gridview.setAdapter(gridviewAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                linearLayout.setBackgroundResource(list.get(position).getId());
                spfManager = new SPFManager(Setting.this);
                spfManager.SaveMessage(list.get(position).getId());
            }
        });

    }
    public  void show(){
        nb = new NotificationCompat.Builder(this);
        nb.setTicker("来通知了");
        nb.setContentTitle("音乐播放器");
        nb.setContentText("欢迎使用");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.setWhen(System.currentTimeMillis());
        nb.setPriority(Notification.PRIORITY_DEFAULT);
        nb.setDefaults(Notification.DEFAULT_ALL);
        Intent intent = new Intent(this, Home.class);//产生于一个意图
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        nb.setContentIntent(pi);//给通知栏设置点击
        nb.setAutoCancel(false);//设置点击消失，但是必须给通知栏绑定点击事件
        Notification nf = nb.build();//获得一个通知栏对象
        nm.notify(0, nf);//唤醒通知

    }
    void addlSetlistcolor(){
        list = new ArrayList<Set>();
        Set homeMenu1 = new Set("海洋蓝",R.color.one);
        Set homeMenu2 = new Set("森林绿",R.color.two);
        Set homeMenu3 = new Set("宝石蓝",R.color.three);
        Set homeMenu4 = new Set("柠檬黄",R.color.four);
        Set homeMenu5 = new Set("胭脂红",R.color.five);
        Set homeMenu6 = new Set("日光黄",R.color.six);
        Set homeMenu7 = new Set("玫瑰红",R.color.seven);
        Set homeMenu8 = new Set("葡萄紫",R.color.eight);
        list.add(homeMenu1);
        list.add(homeMenu2);
        list.add(homeMenu3);
        list.add(homeMenu4);
        list.add(homeMenu5);
        list.add(homeMenu6);
        list.add(homeMenu7);
        list.add(homeMenu8);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent i = new Intent(Setting.this, Home.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
