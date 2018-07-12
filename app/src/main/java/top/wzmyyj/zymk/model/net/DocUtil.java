package top.wzmyyj.zymk.model.net;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.common.java.RandomSort;
import top.wzmyyj.zymk.model.box.HomeBox;
import top.wzmyyj.zymk.model.box.MoreBox;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public class DocUtil {

    public static List<ItemBean> transToItem(Document doc) {
        if (doc == null) return null;
        List<ItemBean> items = new ArrayList<>();
        Elements elements = doc.getElementsByClass("mk-floor");
        int l = elements.size();
        elements.remove(l - 1);
        elements.remove(l - 2);
        for (Element element : elements) {
            Element hd = element.getElementsByClass("hd").get(0);
            String title = hd.getElementsByClass("title").text();
            String summary = hd.getElementsByClass("summary").text();
            Element a = hd.getElementsByTag("a").get(0);
            String href = a.absUrl("href");


            ItemBean item = new ItemBean(title, summary, href);

            Elements slides = element.getElementsByClass("swiper-slide");
            List<BookBean> books = new ArrayList<>();
            for (Element slide : slides) {
                BookBean book = getBook(slide);
                books.add(book);
            }

            RandomSort.sort(books);

            item.setBooks(books);
            items.add(item);

        }
        RandomSort.sort(items, 0, 3);
        RandomSort.sort(items, 4, items.size() - 2);
        return items;
    }


    private static BookBean getBook(Element slide) {
        Element a = slide.getElementsByTag("a").get(0);
        String href = a.absUrl("href");
        String title = a.attr("title");
        String data_src = a.getElementsByTag("img").attr("data-src");
        String star = slide.getElementsByTag("span").get(1).text();
        String chapter = slide.getElementsByTag("span").get(0).text();
        String desc = slide.getElementsByClass("desc").text();


        BookBean book = new BookBean(title, data_src, star, chapter, href, desc);
        return book;
    }


    public static List<BoBean> transToBo(Document doc) {
        if (doc == null) return null;
        List<BoBean> data = new ArrayList<>();
        Elements elements = doc.getElementsByClass("swiper-slide");
        for (Element element : elements) {
            String href = element.absUrl("href");
            String data_src = element.absUrl("data-src");
            String title = element.attr("title");
            data.add(new BoBean(title, data_src, href));
        }
        return data;
    }


    public static HomeBox transToHome(Document doc) {
        List<ItemBean> items = transToItem(doc);
        List<BoBean> bos = transToBo(doc);
        HomeBox home = new HomeBox(bos, items);
        return home;
    }


    public static MoreBox transToMore(Document doc) {

        MoreBox more = new MoreBox();
        more.setHref(doc.baseUri());

        String top = doc.getElementsByClass("mk-crumb").get(0)
                .getElementsByTag("strong").text();
        String figure = doc.getElementsByClass("figure").attr("data-src");
        String content = doc.getElementsByClass("book-desc").get(0)
                .getElementsByClass("content").text();

        more.setTitle(top);
        more.setFigure(figure);
        more.setContent(content);

        List<BookBean> books = new ArrayList<>();

        Elements elements = doc.getElementsByClass("item");
        for (Element element : elements) {
            Element a = element.getElementsByTag("a").get(0);
            String href = a.absUrl("href");
            String title = a.attr("title");
            String data_src = a.getElementsByTag("img").get(0)
                    .absUrl("data-src");

            String star = a.getElementsByTag("span").get(0).text();

            String desc = element.getElementsByClass("content").text();

            BookBean book = new BookBean();
            book.setHref(href);
            book.setTitle(title);
            book.setData_src(data_src);
            book.setStar(star);
            book.setDesc(desc);

            List<String> ts = new ArrayList<>();

            Elements tags = element.getElementsByClass("tags-txt");
            for (Element tag : tags) {
                String t = tag.text();
                ts.add(t);
            }

            book.setTags(ts);
            books.add(book);
        }
        RandomSort.sort(books);
        more.setBookList(books);
        return more;

    }
}