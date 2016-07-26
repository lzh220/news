package lzh.dongruan.com.news.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lzh.dongruan.com.news.Base.BaseActivity;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.adapter.LeadAdapter;

/**
 * Created by lzh220 on 2016/7/10.
 */
public class Lead extends BaseActivity {
    ViewPager viewPager;
    Button button;
    LayoutInflater inflater;
    LeadAdapter adapter;
    List<ImageView> list;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int count=0;

    @Override
    public void setLayout() {
        setContentView(R.layout.lead);
    }

    @Override
    public void indata() {
        sp=getSharedPreferences("count", MODE_PRIVATE);
        editor=sp.edit();
        count=sp.getInt("count",0);
    }

    @Override
    public void getview() {
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        button= (Button) findViewById(R.id.btn);


    }

    @Override
    public void setview() {
        if(count==0){
            editor.putInt("count",++count);
            editor.commit();
            inflater=LayoutInflater.from(this);
            ImageView img1= (ImageView) inflater.inflate(R.layout.img,null);
            img1.setBackgroundResource(R.mipmap.lead_1);
            ImageView img2= (ImageView) inflater.inflate(R.layout.img,null);
            img2.setBackgroundResource(R.mipmap.lead_2);
            ImageView img3= (ImageView) inflater.inflate(R.layout.img,null);
            img3.setBackgroundResource(R.mipmap.lead_3);
            list=new ArrayList<ImageView>();
            list.add(img1);
            list.add(img2);
            list.add(img3);
            adapter=new LeadAdapter(list,this);
            viewPager.setAdapter(adapter);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Lead.this,Show.class);
                    startActivity(intent);
                    finish();


                }
            });
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == list.size() - 1) {
                        button.setVisibility(View.VISIBLE);
                    } else {
                        button.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }else {
            Intent intent=new Intent(this,Show.class);
            startActivity(intent);
            finish();
        }



    }
}
