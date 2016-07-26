package lzh.dongruan.com.news.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import lzh.dongruan.com.news.Until.News;


/**
 * Created by lzh220 on 2016/5/11.
 */
public class NewsDb {
    Context con;
    SQLiteDatabase db;

    public NewsDb(Context con) {
        this.con = con;
        //拿创建的数据表
        NewsDbHelper helper = new NewsDbHelper(con);
        db = helper.getReadableDatabase();
    }

    //增加
    public void Add(String title, String thumbnail_pic_s, String url, String date, String author_name, String thumbnail_pic_s03) {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("thumbnail_pic_s", thumbnail_pic_s);
        cv.put("url", url);
        cv.put("date", date);
        cv.put("author_name", author_name);
        cv.put("thumbnail_pic_s03", thumbnail_pic_s03);
        db.insert("news", null, cv);

    }

    //查询所有
    public List<News> findall() {
        Cursor cursor = null;
        List<News> list = new ArrayList<News>();
        cursor = db.rawQuery("select *from news", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String stitle = cursor.getString(cursor.getColumnIndex("title"));
                String sthumbnail_pic_s = cursor.getString(cursor.getColumnIndex("thumbnail_pic_s"));
                String surl = cursor.getString(cursor.getColumnIndex("url"));
                String sdate = cursor.getString(cursor.getColumnIndex("date"));
                String sauthor_name = cursor.getString(cursor.getColumnIndex("author_name"));
                String sthumbnail_pic_s03 = cursor.getString(cursor.getColumnIndex("thumbnail_pic_s03"));
                News news = new News(stitle, sthumbnail_pic_s, surl, sdate, sauthor_name, sthumbnail_pic_s03);
                list.add(news);
            }
        }
        return list;
    }



    //删除所有
    public void deleteall() {
        db.delete("news", null, null);
    }
    public void delete(News news){
        db.delete("news","title=?",new String[]{news.getTitle()});
    }
}