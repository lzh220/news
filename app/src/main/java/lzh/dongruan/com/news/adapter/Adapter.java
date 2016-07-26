package lzh.dongruan.com.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzh220 on 2016/6/12.
 */
public class Adapter extends FragmentStatePagerAdapter {
    List<Fragment> list;
    List<String> strlist;


    public List<Fragment> getList() {
        return list;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public List<String> getStrlist() {
        return strlist;
    }

    public void setStrlist(List<String> strlist) {
        this.strlist = strlist;
    }

    public Adapter(FragmentManager fm,List<Fragment> list,List<String> strlist) {
        super(fm);
        this.list=list;
        this.strlist=strlist;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public CharSequence getPageTitle(int position) {
        return strlist.get(position);
    }


}
