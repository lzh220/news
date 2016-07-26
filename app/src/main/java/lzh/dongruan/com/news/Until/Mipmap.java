package lzh.dongruan.com.news.Until;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import lzh.dongruan.com.news.Http.VolleySingleton;
import lzh.dongruan.com.news.R;

/**
 * Created by lzh220 on 2016/7/15.
 */
public class Mipmap {
    Context context;

    public Mipmap(Context context) {
        this.context = context;
    }

    public void setBitmap(String url, final ImageView iv){
        if(url.length()>0){
            ImageRequest request = new ImageRequest(url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            iv.setImageBitmap(bitmap);
                        }
                    }, 0, 0, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            iv.setImageResource(R.mipmap.ic_launcher);//加载失败
                        }
                    });
            VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);
        }else{
            iv.setImageResource(R.mipmap.ic_launcher);
        }
    }
}
