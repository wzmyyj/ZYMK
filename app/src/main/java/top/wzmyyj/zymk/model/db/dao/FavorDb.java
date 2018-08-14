package top.wzmyyj.zymk.model.db.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yyj on 2018/08/14. email: 2209011667@qq.com
 */
@Entity
public class FavorDb {
    @Id
    private int id;

    @Generated(hash = 1966188710)
    public FavorDb(int id) {
        this.id = id;
    }

    @Generated(hash = 1955454265)
    public FavorDb() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
