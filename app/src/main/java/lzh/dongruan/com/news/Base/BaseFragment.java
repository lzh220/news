package lzh.dongruan.com.news.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/27.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = setLayout(inflater);
        indata();
        getview(view);
        setview();
        return view;
    }

    public abstract View setLayout(LayoutInflater inflater);

    public abstract void indata();
    public abstract void getview(View view);
    public abstract void setview();

    @Override
    public abstract void onClick(View v);
}
