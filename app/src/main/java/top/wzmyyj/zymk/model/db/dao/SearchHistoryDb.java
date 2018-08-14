package top.wzmyyj.zymk.model.db.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yyj on 2018/08/14. email: 2209011667@qq.com
 */
@Entity
public class SearchHistoryDb {
    @Id
    private int id;
    private String search_word;
    private long search_time;
    @Generated(hash = 529323503)
    public SearchHistoryDb(int id, String search_word, long search_time) {
        this.id = id;
        this.search_word = search_word;
        this.search_time = search_time;
    }
    @Generated(hash = 422215229)
    public SearchHistoryDb() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSearch_word() {
        return this.search_word;
    }
    public void setSearch_word(String search_word) {
        this.search_word = search_word;
    }
    public long getSearch_time() {
        return this.search_time;
    }
    public void setSearch_time(long search_time) {
        this.search_time = search_time;
    }
}
