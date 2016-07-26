package lzh.dongruan.com.news.Until;

/**
 * Created by lzh220 on 2016/7/21.
 */
public class Set {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set(String name, int id) {

        this.name = name;
        this.id = id;
    }
}
