package lzh.dongruan.com.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.Set;

/**
 * Created by lzh220 on 2016/7/21.
 */
public class GridviewAdapter extends BaseAdapter {
    List<Set> list;
    Context context;

    public GridviewAdapter(List<Set> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHorder v=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.setting_item,null);
            v= new ViewHorder();
            v.tv = (TextView) convertView.findViewById(R.id.color);
            v.color = (TextView) convertView.findViewById(R.id.color_color);
            convertView.setTag(v);
        }else {
            v = (ViewHorder) convertView.getTag();
        }
        Set shezhi =list.get(position);
        v.tv.setText(shezhi.getName());
        v.color.setBackgroundResource(shezhi.getId());
        return convertView;
    }
    class ViewHorder{
        TextView tv,color;

    }
}
