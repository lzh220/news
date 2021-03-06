package lzh.dongruan.com.news.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.Mipmap;
import lzh.dongruan.com.news.Until.News;

/**
 * Created by lzh220 on 2016/7/12.
 */
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder> {
    Context context;
    List<News> list;
    final int ONE_TYPE=1;
    final int TWO_TYPE=2;
    int type;
    public NewAdapter(Context context) {
        this.context = context;
        list=new ArrayList<News>();
    }

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }
//加载布局
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View my= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
//            MyViewHolder myViewHolder=new MyViewHolder(my);
//            return myViewHolder;
        MyViewHolder viewHolder = null;
        if (viewType==ONE_TYPE){
            View view= LayoutInflater.from(context).inflate(R.layout.info_title_item,parent,false);
            viewHolder =new MyViewHolder(view);
        }
        if (viewType==TWO_TYPE){
            View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            viewHolder =new MyViewHolder(view);
        }
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Mipmap mipmap=new Mipmap(context);

        Log.i("msg", "进来");
        if(type==ONE_TYPE){
            mipmap.setBitmap(list.get(position).getThumbnail_pic_s03(), holder.info_img);
            holder.info_tv.setText(list.get(position).getTitle());
            if(recycleListener!=null){
                holder.info_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int ps = holder.getLayoutPosition();
                        recycleListener.OnItemClick(holder.itemView, ps);
                    }
                });
            }

        }
        if(type==TWO_TYPE) {
            holder.title_tv.setText(list.get(position).getTitle());
            Log.i("msg", "aaa" + list.get(position).getTitle());
            holder.date_tv.setText(list.get(position).getDate());
            Log.i("msg", "bbb" + list.get(position).getDate());
            holder.author_tv.setText(list.get(position).getAuthor_name());
            Log.i("msg", "ccc" + list.get(position).getAuthor_name());
            mipmap.setBitmap(list.get(position).getThumbnail_pic_s(), holder.img);
            if(recycleListener!=null){
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int ps = holder.getLayoutPosition();
                        recycleListener.OnItemClick(holder.itemView, ps);
                    }
                });
            }
        }


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (position==0){
            type=ONE_TYPE;//将第0项的type类型设为1
            return ONE_TYPE;
        }else {
            type=TWO_TYPE;//其他项的type类型设为2，若不设置，type值全部为0；
            return TWO_TYPE;
        }
    }


    //加载控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title_tv,date_tv,author_tv,info_tv;
        ImageView img,info_img;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            if (type==ONE_TYPE){
              info_tv= (TextView) itemView.findViewById(R.id.info_tv);
                info_img= (ImageView) itemView.findViewById(R.id.info_title_img);
            }
            if(type==TWO_TYPE){
                title_tv= (TextView) itemView.findViewById(R.id.titletv);
                date_tv = (TextView) itemView.findViewById(R.id.datetv);
                author_tv = (TextView) itemView.findViewById(R.id.authortv);
                img = (ImageView) itemView.findViewById(R.id.img);
                cardView= (CardView) itemView.findViewById(R.id.news_card);
            }



        }
    }
    public  interface  RecycleListener {
        void OnItemClick(View view,int position);
    }
    private RecycleListener recycleListener=null;

    public RecycleListener getRecycleListener() {
        return recycleListener;
    }

    public void setRecycleListener(RecycleListener recycleListener) {
        this.recycleListener = recycleListener;
    }
}
