package top.wzmyyj.zymk.model.db.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yyj on 2018/08/14. email: 2209011667@qq.com
 */
@Entity
public class HistoryDb {
    @Id
    private int id;
    private String title;
    private long history_chapter_id;
    private String history_chapter_name;
    private long history_read_time;
    @Generated(hash = 673569480)
    public HistoryDb(int id, String title, long history_chapter_id,
            String history_chapter_name, long history_read_time) {
        this.id = id;
        this.title = title;
        this.history_chapter_id = history_chapter_id;
        this.history_chapter_name = history_chapter_name;
        this.history_read_time = history_read_time;
    }
    @Generated(hash = 1549631443)
    public HistoryDb() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getHistory_chapter_id() {
        return this.history_chapter_id;
    }
    public void setHistory_chapter_id(long history_chapter_id) {
        this.history_chapter_id = history_chapter_id;
    }
    public String getHistory_chapter_name() {
        return this.history_chapter_name;
    }
    public void setHistory_chapter_name(String history_chapter_name) {
        this.history_chapter_name = history_chapter_name;
    }
    public long getHistory_read_time() {
        return this.history_read_time;
    }
    public void setHistory_read_time(long history_read_time) {
        this.history_read_time = history_read_time;
    }



    
}
