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
import top.wzmyyj.zymk.model.box.NewBox;
import top.wzmyyj.zymk.model.box.RankBox;

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
        String data_src = a.getElementsByTag("img").attr("data-src");
        String title = slide.getElementsByClass("title").text();
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
        HomeBox box = new HomeBox(bos, items);
        return box;
    }


    // new
    public static NewBox transToNew(Document doc) {
        Elements elements = doc.getElementsByClass("mk-floor");
        int l = elements.size();
        // bookList1
        Element e1 = elements.get(l - 2);
        Elements slides1 = e1.getElementsByClass("swiper-slide");
        List<BookBean> books1 = new ArrayList<>();
        for (Element slide : slides1) {
            BookBean book = getBook(slide);
            books1.add(book);
        }
        // bookList2
        Element e2 = elements.get(l - 1);
        Elements slides2 = e2.getElementsByClass("swiper-slide");
        List<BookBean> books2 = new ArrayList<>();
        for (Element slide : slides2) {
            BookBean book = getBook(slide);
            books2.add(book);
        }

        RandomSort.sort(books1);
        RandomSort.sort(books2);

        NewBox box = new NewBox(books1, books2);

        return box;
    }


    //rank
    public static RankBox transToRank(Document doc) {
        String[] ifts = new String[]{"ift-fire", "ift-love_money", "ift-ticket"};
        Elements lists = doc.getElementsByClass("mk-rank-list");

        List<BookBean> bookList1 = new ArrayList<>();
        for (Element element : lists.get(0).getElementsByClass("item")) {
            BookBean book = getRankBook(element, ifts[0]);
            bookList1.add(book);
        }

        List<BookBean> bookList2 = new ArrayList<>();
        for (Element element : lists.get(1).getElementsByClass("item")) {
            BookBean book = getRankBook(element, ifts[1]);
            bookList2.add(book);
        }

        List<BookBean> bookList3 = new ArrayList<>();
        for (Element element : lists.get(2).getElementsByClass("item")) {
            BookBean book = getRankBook(element, ifts[2]);
            bookList3.add(book);
        }

        RankBox box = new RankBox(bookList1, bookList2, bookList3);

        return box;
    }

    private static BookBean getRankBook(Element element, String iftClass) {
        Element a = element.getElementsByTag("a").get(0);
        String href = a.absUrl("href");

        String data_src = a.getElementsByTag("img").get(0)
                .absUrl("data-src");

        String title = element.getElementsByClass("title").text();
        String ift = element.getElementsByClass(iftClass).text()
                .replace("人气值: ", "")
                .replace("共被打赏: ", "")
                .replace("个元宝", "")
                .replace("月票数: ", "");
        String num = element.getElementsByClass("num").text();

        List<String> ts = new ArrayList<>();
        Elements tags = element.getElementsByClass("tags");
        for (Element tag : tags) {
            String t = tag.text();
            ts.add(t);
        }

        BookBean book = new BookBean(title, data_src, href, num, ift, ts);
        return book;
    }


    // more
    public static MoreBox transToMore(Document doc) {

        MoreBox box = new MoreBox();
        box.setHref(doc.baseUri());

        String top = doc.getElementsByClass("mk-crumb").get(0)
                .getElementsByTag("strong").text();
        String figure = doc.getElementsByClass("figure").attr("data-src");
        String content = doc.getElementsByClass("book-desc").get(0)
                .getElementsByClass("content").text();

        box.setTitle(top);
        box.setFigure(figure);
        box.setContent(content);

        List<BookBean> books = new ArrayList<>();

        Elements elements = doc.getElementsByClass("item");
        for (Element element : elements) {
            Element a = element.getElementsByTag("a").get(0);
            String href = a.absUrl("href");
            String data_src = a.getElementsByTag("img").get(0)
                    .absUrl("data-src");

            String star = a.getElementsByTag("span").get(0).text();
            String title = element.getElementsByClass("title").text();
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
        box.setBookList(books);
        return box;

    }
}