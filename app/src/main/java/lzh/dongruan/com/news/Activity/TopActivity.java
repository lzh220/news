package lzh.dongruan.com.news.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lzh.dongruan.com.news.Fragment.Info;
import lzh.dongruan.com.news.Http.Tab;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.SPFManager;
import lzh.dongruan.com.news.adapter.TabAdapter;

public class TopActivity extends Activity implements View.OnClickListener{
    List<String> list;
    TabAdapter adapter;
    ListView listView;
    TextView t1,t2,t3,t4,t5,t6;
    Set<String> set;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        set = new HashSet<String>();
        listView= (ListView) findViewById(R.id.listview);
        t1= (TextView) findViewById(R.id.tv1);
        t2= (TextView) findViewById(R.id.tv2);
        t3= (TextView) findViewById(R.id.tv3);
        t4= (TextView) findViewById(R.id.tv4);
        t5= (TextView) findViewById(R.id.tv5);
        t6= (TextView) findViewById(R.id.tv6);
        btn= (Button) findViewById(R.id.add_tn);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
        t6.setOnClickListener(this);
        list=new ArrayList<String>();
        list.add("头条");
        list.add("军事");
        list.add("国际");
        list.add("体育");
        list.add("财经");
        list.add("时尚");
        adapter=new TabAdapter(this);
        adapter.setList(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (position){
                   case 0:
                       t1.setText("头条");
                       t1.setVisibility(View.VISIBLE);
                       t1.setBackgroundColor(Color.GRAY);
                       set.add("头条");
                       break;
                   case 1:
                       t2.setText("军事");
                       t2.setVisibility(View.VISIBLE);
                       t2.setBackgroundColor(Color.GRAY);
                       set.add("军事");
                       break;
                   case 2:
                       t3.setText("国际");
                       t3.setVisibility(View.VISIBLE);
                       t3.setBackgroundColor(Color.GRAY);
                       set.add("国际");
                       break;
                   case 3:
                       t4.setText("体育");
                       t4.setVisibility(View.VISIBLE);
                       t4.setBackgroundColor(Color.GRAY);
                       set.add("体育");
                       break;
                   case 4:
                       t5.setText("财经");
                       t5.setVisibility(View.VISIBLE);
                       t5.setBackgroundColor(Color.GRAY);
                       set.add("财经");
                       break;
                   case 5:
                       t6.setText("时尚");
                       t6.setVisibility(View.VISIBLE);
                       t6.setBackgroundColor(Color.GRAY);
                       set.add("时尚");
                       break;
               }
                btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPFManager spfManager=new SPFManager(TopActivity.this);
                spfManager.SaveType(TopActivity.this, set);
                finish();

            }
        });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
                t1.setVisibility(View.GONE);
                t1.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv2:
                t2.setVisibility(View.GONE);
                t2.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv3:
                t3.setVisibility(View.GONE);
                t3.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv4:
                t4.setVisibility(View.GONE);
                t4.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv5:
                t5.setVisibility(View.GONE);
                t5.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv6:
                t6.setVisibility(View.GONE);
                t6.setBackgroundColor(Color.WHITE);
                break;
        }
    }
}
