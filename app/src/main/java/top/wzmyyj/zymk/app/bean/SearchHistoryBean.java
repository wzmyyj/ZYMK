package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/08/14. email: 2209011667@qq.com
 */

public class SearchHistoryBean {
    private long id;
    private String word;
    private long time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
