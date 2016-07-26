package lzh.dongruan.com.news.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import lzh.dongruan.com.news.Base.BaseActivity;
import lzh.dongruan.com.news.Fragment.Home;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.SPFManager;

/**
 * Created by lzh220 on 2016/5/19.
 */
public class AboutUs extends BaseActivity {
    View view;
    TextView name,versionName;
    ImageView left;

    @Override
    public void setLayout() {
        setContentView(R.layout.aboutus);
    }

    @Override
    public void indata() {

    }

    @Override
    public void getview() {
        view=findViewById(R.id.title_color);
        left= (ImageView) findViewById(R.id.title_left);
        name= (TextView) findViewById(R.id.tv1);
        versionName= (TextView) findViewById(R.id.tv2);
        SPFManager spfManager = new SPFManager(AboutUs.this);
        int a=spfManager.GetMessage();
        view.setBackgroundResource(a);

    }

    @Override
    public void setview() {
        PackageManager packageManager=getPackageManager();
        try {
            PackageInfo packageInfo=packageManager.getPackageInfo(getPackageName(),0);
            name.setText(this.getString(R.string.app_name));
            versionName.setText(packageInfo.versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutUs.this, Home.class);
                startActivity(i);
            }
        });


    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent i = new Intent(AboutUs.this, Home.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


}
