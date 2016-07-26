package lzh.dongruan.com.news.Until;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/29.
 */
public class News implements Serializable {
    private String title;
    private String thumbnail_pic_s;
    private String url;
    private String date;
    private String author_name;
    private String thumbnail_pic_s03;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }


    public News(String title, String thumbnail_pic_s, String url, String date, String author_name, String thumbnail_pic_s03) {
        this.title = title;
        this.thumbnail_pic_s = thumbnail_pic_s;
        this.url = url;
        this.date = date;
        this.author_name = author_name;
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }
}
