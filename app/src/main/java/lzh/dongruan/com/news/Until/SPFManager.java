package lzh.dongruan.com.news.Until;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lzh.dongruan.com.news.R;


/**
 * Created by HRR on 2016/7/11.
 */
public class SPFManager {
    Context context;

    public SPFManager(Context context) {
        this.context = context;
    }


    public  void SaveType(Context context,Set<String> set){
        SharedPreferences spf = context.getSharedPreferences("SaveType",Context.MODE_PRIVATE);
        SharedPreferences.Editor spfe = spf.edit();
        spfe.putStringSet("type", set);
        spfe.commit();
    }

    public List<String> GetType(Context context){
        SharedPreferences spf = context.getSharedPreferences("SaveType",Context.MODE_PRIVATE);
        Set<String> set=spf.getStringSet("type", null);
        List<String> list = new ArrayList<String>();
        if(set==null){
            list.add("头条");
            list.add("体育");
            list.add("时尚");

        }else {
            for (String s:set) {
            list.add(s);
        }
        }

        return list;
    }
    public  void Save(Context context,Boolean is){
        SharedPreferences spf = context.getSharedPreferences("SaveType",Context.MODE_PRIVATE);
        SharedPreferences.Editor spfe = spf.edit();
        spfe.putBoolean("music", is);
        spfe.commit();
    }
    public Boolean Get(Context context){
        SharedPreferences spf = context.getSharedPreferences("SaveType",Context.MODE_PRIVATE);
        Boolean kaiji=spf.getBoolean("music", false);
        return kaiji;
    }


    public void SaveMessage(int i) {
        SharedPreferences spf = context.getSharedPreferences("color.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor sed = spf.edit();
        sed.putInt("背景颜色", i);
        sed.commit();
    }

    public int GetMessage() {
        SharedPreferences spf = context.getSharedPreferences("color.txt", Context.MODE_PRIVATE);
        int i = spf.getInt("背景颜色", R.color.colorPrimary);
        return i;
    }

}
