package lzh.dongruan.com.news.Http;

/**
 * Created by lzh220 on 2016/7/14.
 */
public class Tab {
    String name;
    String path;

    public Tab(String name) {
        this.name = name;
    }

    public Tab(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
