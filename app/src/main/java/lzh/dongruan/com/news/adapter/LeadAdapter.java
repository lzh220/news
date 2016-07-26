package lzh.dongruan.com.news.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by lzh220 on 2016/7/10.
 */
public class LeadAdapter extends PagerAdapter {
    List<ImageView> list;
    Context context;

    public LeadAdapter(List<ImageView> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view=list.get(position);
        container.removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=list.get(position);
        container.addView(view);
        return view;
    }
}
