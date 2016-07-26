package lzh.dongruan.com.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lzh.dongruan.com.news.R;

/**
 * Created by lzh220 on 2016/7/13.
 */
public class TabAdapter extends BaseAdapter {
    List<String> list;
    Context context;

    public TabAdapter(Context context) {
        this.context = context;
        list=new ArrayList<String>();

    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        VerHolder vh=null;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.tab_item,null);
            vh=new VerHolder();
            vh.tv= (TextView) view.findViewById(R.id.tv);
            view.setTag(vh);
        }else {
            vh= (VerHolder) view.getTag();
        }
        vh.tv.setText(list.get(i));
        return view;
    }
    class VerHolder{
        TextView tv;

    }
}
