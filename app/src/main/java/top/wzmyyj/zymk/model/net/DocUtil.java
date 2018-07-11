package top.wzmyyj.zymk.model.net;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ItemBean;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public class DocUtil {

    public static List<ItemBean> transToItem(Document doc) {
        List<ItemBean> data = new ArrayList<>();

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

            item.setBooks(books);
            data.add(item);

        }

        return data;
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


}
