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
import java.util.Random;

import lzh.dongruan.com.news.Fragment.New;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.Mipmap;
import lzh.dongruan.com.news.Until.News;

/**
 * Created by lzh220 on 2016/7/12.
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.MyViewHolder> {
    Context context;
    List<News> list;

    public HotAdapter(Context context) {
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
        View my= LayoutInflater.from(context).inflate(R.layout.hot_item, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(my);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        News newInfp=list.get(position);
        holder.textView.setText(list.get(position).getTitle());
        Mipmap setBitmap = new Mipmap(context);
        setBitmap.setBitmap(newInfp.getThumbnail_pic_s(), holder.imageView);
//        Random random=new Random();
//        int top=random.nextInt(200)+30;
//        int bottom=random.nextInt(200) +30;
//        int left=50;
//        int right=50;
//        holder.cardView.setContentPadding(top,bottom,left,right);
        //随机设置控件宽高
        //设置长按短按事件
        if(recycleListener!=null){
            //短按
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i=holder.getLayoutPosition();
                    recycleListener.OnItemClick(holder.itemView,i);
                }
            });
            //长按
            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int i=holder.getLayoutPosition();
                    recycleListener.OnItemLongClick(holder.itemView,i);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //加载控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.hot_tv);
            imageView= (ImageView) itemView.findViewById(R.id.hot_img);
            cardView= (CardView) itemView.findViewById(R.id.hot_card);

        }
    }

    private  RecycleListener recycleListener=null;
    //RecycleListener的get和set方法
    public RecycleListener getRecycleListener() {
        return recycleListener;
    }

    public void setRecycleListener(RecycleListener recycleListener) {
        this.recycleListener = recycleListener;
    }
    //设置接口长按，短按
    public interface RecycleListener{
        void OnItemClick(View view,int position);
        void OnItemLongClick(View view,int position);
    }
//    //添加删除的方法
//    public void add(int position,String msg){
//        list.add(position,msg);
//        notifyItemInserted(position);
//    }
//    public void remove(int position){
//        list.remove(position);
//        notifyItemRemoved(position);
//    }
}
