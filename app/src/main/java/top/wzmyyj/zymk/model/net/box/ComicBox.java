package top.wzmyyj.zymk.model.net.box;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.app.bean.AuthorBean;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.FansBean;

/**
 * Created by yyj on 2018/08/02. email: 2209011667@qq.com
 */

public class ComicBox {
    private int status;
    private String msg;
    private BookBean book;
    private List<ChapterBean> chapterList;
    private List<BookBean> bookList;

    private AuthorBean author;

    private List<FansBean> fansList;


    // 定义静态内部类-->序列化器
    public static class Deserializer implements JsonDeserializer<ComicBox> {

        @Override
        public ComicBox deserialize(JsonElement json, Type arg1,
                                    JsonDeserializationContext arg2) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();
            int status = jsonObject.get("status").getAsInt();
            String msg = jsonObject.get("msg").getAsString();
            List<BookBean> list = new ArrayList<>();

            JsonObject data = jsonObject.getAsJsonObject("data");
            BookBean book = new BookBean();
            int id = data.get("comic_id").getAsInt();
            String title = data.get("comic_name").getAsString();
            int score = data.get("score").getAsInt() / 10;
            List<String> tags = new ArrayList<>();
            JsonArray ts = data.getAsJsonArray("comic_type");
            if (ts != null && ts.size() > 0) {
                for (int i = 0; i < ts.size(); i++) {
                    JsonObject t = ts.get(i).getAsJsonObject();
                    String tag = t.get("name").getAsString();
                    tags.add(tag);
                }
            }
            String desc = data.get("desc").getAsString();
            long update_time = data.get("update_time").getAsLong();


            book.setTitle(title);
            book.setId(id);
            book.setStar("" + score);
            book.setTags(tags);
            book.setDesc(desc);
            book.setUpdate_time(update_time);


            List<ChapterBean> chapterList = new ArrayList<>();
            JsonArray chapter_list = data.getAsJsonArray("chapter_list");
            if (chapter_list != null && chapter_list.size() > 0) {
                for (int i = 0; i < chapter_list.size(); i++) {
                    JsonObject obj = chapter_list.get(i).getAsJsonObject();
                    ChapterBean chapter = new Gson().fromJson(obj, ChapterBean.class);
                    chapterList.add(chapter);
                }
            }

            List<BookBean> relationList = new ArrayList<>();
            JsonArray relation_list = data.getAsJsonArray("relation_list");
            if (relation_list != null && relation_list.size() > 0) {
                for (int i = 0; i < relation_list.size(); i++) {
                    JsonObject obj = relation_list.get(i).getAsJsonObject();
                    int r_id = obj.get("comic_id").getAsInt();
                    String r_name = obj.get("comic_name").getAsString();
                    String r_desc = obj.get("comic_feature").getAsString();
                    String r_chapter = obj.get("last_chapter").getAsJsonObject()
                            .get("name").toString();
                    int r_score = data.get("score").getAsInt() / 10;
                    BookBean bean = new BookBean();
                    bean.setId(r_id);
                    bean.setTitle(r_name);
                    bean.setDesc(r_desc);
                    bean.setStar("" + r_score);
                    bean.setChapter(r_chapter);
                    relationList.add(bean);
                }
            }

            ComicBox box = new ComicBox(status, msg, book, chapterList, relationList);
            return box;
        }
    }


    public static class Deserializer2 implements JsonDeserializer<ComicBox> {

        @Override
        public ComicBox deserialize(JsonElement json, Type arg1,
                                    JsonDeserializationContext arg2) throws JsonParseException {

            JsonObject jsonObject = json.getAsJsonObject();
            int status = jsonObject.get("status").getAsInt();
            String msg = jsonObject.get("msg").getAsString();
            List<BookBean> list = new ArrayList<>();

            JsonObject data = jsonObject.getAsJsonArray("data").get(0).getAsJsonObject();
            BookBean book = new BookBean();
            int id = data.get("comicid").getAsInt();
            String title = data.get("comicname").getAsString();
            float score = 1.0f * (int)data.get("pingfen").getAsDouble() / 10;
            List<String> tags = new ArrayList<>();
            JsonArray ts = data.getAsJsonArray("comic_type");
            if (ts != null && ts.size() > 0) {
                for (int i = 0; i < ts.size(); i++) {
                    JsonObject t = ts.get(i).getAsJsonObject();
                    String tag = t.get("name").getAsString();
                    tags.add(tag);
                }
            }
            JsonObject last_chapter =data.getAsJsonObject("last_chapter");
            long chapter_id=last_chapter.get("id").getAsLong();
            String chapter_name=last_chapter.get("name").getAsString();


            book.setTitle(title);
            book.setId(id);
            book.setStar("" + score);
            book.setTags(tags);
            book.setChapter_id(chapter_id);
            book.setChapter(chapter_name);

            ComicBox box = new ComicBox(status, msg, book);
            return box;
        }
    }

    public ComicBox(int status, String msg, BookBean book) {
        this.status = status;
        this.msg = msg;
        this.book = book;
    }

    public ComicBox(int status, String msg, BookBean book, List<ChapterBean> chapterList, List<BookBean> bookList) {
        this.status = status;
        this.msg = msg;
        this.book = book;
        this.chapterList = chapterList;
        this.bookList = bookList;
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

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public List<ChapterBean> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ChapterBean> chapterList) {
        this.chapterList = chapterList;
    }

    public List<BookBean> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookBean> bookList) {
        this.bookList = bookList;
    }
}
