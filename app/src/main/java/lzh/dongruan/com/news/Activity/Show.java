package lzh.dongruan.com.news.Activity;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import lzh.dongruan.com.news.Base.BaseActivity;
import lzh.dongruan.com.news.Fragment.Home;
import lzh.dongruan.com.news.R;

/**
 * Created by lzh220 on 2016/7/10.
 */
public class Show extends BaseActivity {
    AlphaAnimation animation;
    ImageView iv;
    @Override
    public void setLayout() {
        setContentView(R.layout.show);

    }

    @Override
    public void indata() {

    }

    @Override
    public void getview() {
        iv= (ImageView) findViewById(R.id.show);

    }

    @Override
    public void setview() {
        animation= (AlphaAnimation) AnimationUtils.loadAnimation(this,R.anim.alpha);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent i=new Intent(Show.this,Home.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
      iv.startAnimation(animation);
    }
}
