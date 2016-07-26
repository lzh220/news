package lzh.dongruan.com.news.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;
import lzh.dongruan.com.news.Activity.AboutUs;
import lzh.dongruan.com.news.Activity.Collection;
import lzh.dongruan.com.news.Activity.LoginActivity;
import lzh.dongruan.com.news.Activity.Setting;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.MyApplication;
import lzh.dongruan.com.news.Until.SPFManager;
import lzh.dongruan.com.news.Until.Tools;


/**
 * Created by lzh220 on 2016/6/30.
 */
public class Home extends FragmentActivity implements View.OnClickListener {
    RelativeLayout r1,r2,r3,collection;
    LinearLayout setting,left_linearLayout,us_liner;
    private OnekeyShare oks;
    TextView t1,t2,t3,login_tv;
    ImageView i1,i2,i3;
    CircleImageView circleImageView;
    long l=0;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Info info=new Info();
    Hot hot=new Hot();
    Select select=new Select();
    Intent intent;
    int[] select_img={R.drawable.new_selected,R.drawable.collect_selected,R.drawable.find_selected};
    int[] deflaut_img={R.drawable.new_unselected,R.drawable.collect_unselected,R.drawable.find_defult};
    int position; //选择项
    ImageView[] bottom_img = new ImageView[3];
    TextView[]  bottom_tv = new TextView[3];
    boolean Login_Statce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_top);
        Tools.isRefresh = true;
        oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("每天都有新资讯");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("下载我！每天都离万事通更近一点！");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
        getview();
        setview();
    }
    public void ChangeFragment(Fragment fragment,Fragment fragment1,Fragment fragment2){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!fragment.isAdded()){
            ft.add(R.id.main_linearlayout, fragment);
        }
        if (!fragment1.isAdded()){
            ft.add(R.id.main_linearlayout, fragment1);
        }if (!fragment2.isAdded()){
            ft.add(R.id.main_linearlayout, fragment2);
        }
        ft.hide(fragment1);
        ft.hide(fragment2);
        ft.show(fragment);
        ft.commit();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.main_relativelayout_one:
                select(0);
                ChangeFragment(info, hot, select);
                break;
            case R.id.main_relativelayout_two:
                select(1);
                ChangeFragment(hot, info, select);
                break;
            case R.id.main_relativelayout_three:
                select(2);
                ChangeFragment(select,info, hot);
                break;
            case R.id.touxiang:
                intent = new Intent(Home.this, LoginActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.login_tv:
                 intent = new Intent(Home.this, LoginActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.setting_lire:
                intent=new Intent(Home.this, Setting.class);
                startActivity(intent);
                finish();
                break;
            case R.id.colle_rela:
                intent=new Intent(Home.this,Collection.class);
                startActivity(intent);
                finish();
                break;
            case R.id.us_liner:
                intent=new Intent(Home.this,AboutUs.class);
                startActivity(intent);
                finish();
                break;
        }


    }
    public void getview(){
        toolbar= (Toolbar) findViewById(R.id.mytoolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.mydrawer);
        left_linearLayout= (LinearLayout) findViewById(R.id.left);
        us_liner= (LinearLayout) findViewById(R.id.us_liner);
        circleImageView= (CircleImageView) findViewById(R.id.touxiang);
        login_tv= (TextView) findViewById(R.id.login_tv);
        r1= (RelativeLayout) findViewById(R.id.main_relativelayout_one);
        r2= (RelativeLayout) findViewById(R.id.main_relativelayout_two);
        r3= (RelativeLayout) findViewById(R.id.main_relativelayout_three);
        collection= (RelativeLayout) findViewById(R.id.colle_rela);
        t1= (TextView) findViewById(R.id.mian_bottomtitle_info_tv);
        t2= (TextView) findViewById(R.id.mian_bottomtitle_hot_tv);
        t3= (TextView) findViewById(R.id.mian_bottomtitle_select_tv);
        i1= (ImageView) findViewById(R.id.main_bottomtitle_info_img);
        i2= (ImageView) findViewById(R.id.main_bottomtitle_hot_img);
        i3= (ImageView) findViewById(R.id.main_bottomtitle_select_img);
        setting= (LinearLayout) findViewById(R.id.setting_lire);
        bottom_img[0]=i1;
        bottom_img[1]=i2;
        bottom_img[2]=i3;
        bottom_tv[0]=t1;
        bottom_tv[1]=t2;
        bottom_tv[2]=t3;
        bottom_img[0].setImageResource(select_img[0]);
        bottom_tv[0].setTextColor(Color.BLUE);
        ChangeFragment(info,hot,select);
        SPFManager spfManager = new SPFManager(Home.this);
        int  a=spfManager.GetMessage();
        toolbar.setBackgroundResource(a);
    }
    public  void setview(){
        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        setting.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
        collection.setOnClickListener(this);
        login_tv.setOnClickListener(this);
        us_liner.setOnClickListener(this);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//                intent.putExtra(Intent.EXTRA_TEXT, "终于可以了!!!");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(Intent.createChooser(intent, getTitle()));
                Tools.isRefresh = false;
                oks.show(Home.this);
                return true;
            }
        });
    }
    public void select(int position){
        for(int i=0;i<deflaut_img.length;i++){
            if(position==i){
                bottom_img[i].setImageResource(select_img[i]);
                bottom_tv[i].setTextColor(Color.BLUE);
            }else {
                bottom_img[i].setImageResource(deflaut_img[i]);
                bottom_tv[i].setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            Login_Statce = true;
            login_tv.setText("退出登录");
        }
        if (resultCode == 3) {
            Login_Statce = true;
            login_tv.setText("退出登录");
            String icon_path = data.getStringExtra("usericon");
            ImageRequest imageRequest = new ImageRequest(
                    icon_path, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    circleImageView.setImageBitmap(bitmap);
                }
            }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    circleImageView.setImageResource(R.mipmap.login_defult_img);
                }
            });
            MyApplication.getApplication(this).addToRequestQueue(imageRequest);

        }
    }
    public boolean onKeyDown(int keyCode,KeyEvent event ){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                Long before=System.currentTimeMillis();
                if(before-l>800){
                    l=before;
                    Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
                    return  true;
                }else {
                    finish();
                }
                break;
        }
        return super.onKeyDown(keyCode,event);
    }
}
