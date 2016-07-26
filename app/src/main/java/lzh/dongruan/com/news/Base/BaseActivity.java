package lzh.dongruan.com.news.Base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/6/27.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        indata();
        getview();
        setview();
    }

    public abstract void setLayout();

    public abstract void indata();

    public abstract void getview();

    public abstract void setview();
}
