package top.wzmyyj.zymk.model.db.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by yyj on 2018/08/14. email: 2209011667@qq.com
 */
@Entity
public class FavorDb {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String title;
    private long update_time;
    private String chapter_name;
    private long chapter_id;
    private boolean isReadNew;
    @Generated(hash = 1780244094)
    public FavorDb(Long id, @NotNull String title, long update_time,
            String chapter_name, long chapter_id, boolean isReadNew) {
        this.id = id;
        this.title = title;
        this.update_time = update_time;
        this.chapter_name = chapter_name;
        this.chapter_id = chapter_id;
        this.isReadNew = isReadNew;
    }
    @Generated(hash = 1955454265)
    public FavorDb() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }
    public String getChapter_name() {
        return this.chapter_name;
    }
    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }
    public long getChapter_id() {
        return this.chapter_id;
    }
    public void setChapter_id(long chapter_id) {
        this.chapter_id = chapter_id;
    }
    public boolean getIsReadNew() {
        return this.isReadNew;
    }
    public void setIsReadNew(boolean isReadNew) {
        this.isReadNew = isReadNew;
    }

}
