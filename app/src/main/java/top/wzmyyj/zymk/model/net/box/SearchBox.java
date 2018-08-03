package top.wzmyyj.zymk.model.net.box;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/07/31. email: 2209011667@qq.com
 */

public class SearchBox {

    private int status;
    private String msg;
    private String key;
    private List<BookBean> bookList;

    public SearchBox() {
    }

    public SearchBox(int status, String msg, List<BookBean> bookList) {
        this.status = status;
        this.msg = msg;
        this.bookList = bookList;
    }

    public SearchBox(int status, String msg, String key, List<BookBean> bookList) {
        this.status = status;
        this.msg = msg;
        this.key = key;
        this.bookList = bookList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BookBean> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookBean> bookList) {
        this.bookList = bookList;
    }

    // 定义静态内部类-->序列化器
    public static class Deserializer implements JsonDeserializer<SearchBox> {

        @Override
        public SearchBox deserialize(JsonElement json, Type arg1,
                                     JsonDeserializationContext arg2) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();
            int status = jsonObject.get("status").getAsInt();
            String msg = jsonObject.get("msg").getAsString();
            List<BookBean> list = new ArrayList<>();

            JsonObject data = jsonObject.getAsJsonObject("data");
            JsonArray array = data.getAsJsonArray("list");

            for (int i = 0; i < array.size(); i++) {
                JsonObject obj = array.get(i).getAsJsonObject();
                String title = obj.get("comic_name").getAsString();
                int id = obj.get("comic_id").getAsInt();
                String chapter = obj.get("last_chapter").getAsJsonObject()
                        .get("name").toString();
                BookBean bean = new BookBean();
                bean.setTitle(title);
                bean.setId(id);
                bean.setChapter(chapter);
                list.add(bean);
            }

            SearchBox box = new SearchBox(status, msg, list);
            return box;
        }
    }

    public static class Deserializer2 implements JsonDeserializer<SearchBox> {

        @Override
        public SearchBox deserialize(JsonElement json, Type arg1,
                                     JsonDeserializationContext arg2) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();
            int status = jsonObject.get("status").getAsInt();
            String msg = jsonObject.get("msg").getAsString();
            List<BookBean> list = new ArrayList<>();

            JsonObject data = jsonObject.getAsJsonObject("data");

            String key = data.get("key").getAsString();

            JsonArray array = data.get("page").getAsJsonObject().getAsJsonArray("comic_list");

            for (int i = 0; i < array.size(); i++) {
                JsonObject obj = array.get(i).getAsJsonObject();
                String title = obj.get("comic_name").getAsString();
                int id = obj.get("comic_id").getAsInt();
                String chapter = obj.get("last_chapter").getAsJsonObject()
                        .get("name").toString();
                BookBean bean = new BookBean();
                bean.setTitle(title);
                bean.setId(id);
                bean.setChapter(chapter);
                list.add(bean);
            }

            SearchBox box = new SearchBox(status, msg, key, list);
            return box;
        }
    }
}
