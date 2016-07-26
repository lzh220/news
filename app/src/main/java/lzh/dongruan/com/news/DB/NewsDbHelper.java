package lzh.dongruan.com.news.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lzh220 on 2016/5/15.
 */
public class NewsDbHelper extends SQLiteOpenHelper {
    static  final String STUDENT_DB_NAME="news.db";
    static final int VERSION=1;

    public NewsDbHelper(Context context) {
        super(context, STUDENT_DB_NAME, null,VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news(title text,thumbnail_pic_s text,url text,date text,author_name text,thumbnail_pic_s03 text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
