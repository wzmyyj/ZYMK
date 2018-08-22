package top.wzmyyj.zymk.model.net.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.app.bean.AuthorBean;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.FansBean;
import top.wzmyyj.zymk.app.bean.HuaBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.app.bean.XiBean;
import top.wzmyyj.zymk.app.bean.ZiBean;
import top.wzmyyj.zymk.common.java.RandomSort;
import top.wzmyyj.zymk.model.net.box.DetailsBox;
import top.wzmyyj.zymk.model.net.box.HomeBox;
import top.wzmyyj.zymk.model.net.box.MoreBox;
import top.wzmyyj.zymk.model.net.box.NewBox;
import top.wzmyyj.zymk.model.net.box.RankBox;
import top.wzmyyj.zymk.model.net.box.TyBox;
import top.wzmyyj.zymk.model.net.box.TypeBox;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public class DocUtil {


    ///////////////////////////////////////////////////////////////////////////////////////////////////////// home

    public static List<ItemBean> transToItem(Element ele) {
        if (ele == null) return null;
        List<ItemBean> items = new ArrayList<>();
        Elements elements = ele.getElementsByClass("mk-floor");
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
        int id = Integer.parseInt(a.getElementsByTag("img").attr("data-id"));
        String data_src = a.getElementsByTag("img").attr("data-src");
        String title = slide.getElementsByClass("title").text();
        String star = slide.getElementsByTag("span").get(1).text();
        String chapter = slide.getElementsByTag("span").get(0).text();
        String desc = slide.getElementsByClass("desc").text();

        if (!star.contains(".")) {
            StringBuffer sb = new StringBuffer(star);
            sb.insert(1, ".");
            star = sb.toString();
        }

        BookBean book = new BookBean();
        book.setId(id);
        book.setData_src(data_src);
        book.setTitle(title);
        book.setChapter(chapter);
        book.setDesc(desc);
        book.setStar(star);
        return book;
    }


    public static List<BoBean> transToBo(Element ele) {
        if (ele == null) return null;
        List<BoBean> data = new ArrayList<>();
        Element top=ele.getElementsByClass("swiper-banner").get(0);
        Elements elements = top.getElementsByClass("swiper-slide");
        for (Element element : elements) {
            String href = element.absUrl("href");
            String data_src = element.absUrl("data-src");
            String title = element.attr("title");
            data.add(new BoBean(title, data_src, href));
        }
        return data;
    }


    public static HomeBox transToHome(Element body) {
        List<ItemBean> items = transToItem(body);
        List<BoBean> bos = transToBo(body);
        HomeBox box = new HomeBox(bos, items);
        return box;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////// type

    public static TypeBox transToType(Element body) {

//        Elements tab = body.getElementsByClass("tab-toggle").get(0)
//                .getElementsByClass("item");
//        String[] strs = new String[4];
//        for (int i = 0; i < strs.length; i++) {
//            strs[i] = tab.get(i).text();
//        }
        Elements sw = body.getElementsByClass("mk-class-list");

        List<TypeBean> typeList1 = new ArrayList<>();
        Elements items1 = sw.get(0).getElementsByClass("item");
        if (items1 != null || items1.size() > 0) {
            for (Element item : items1) {
                typeList1.add(getTypeItem(item));
            }
        }

        List<TypeBean> typeList2 = new ArrayList<>();
        Elements items2 = sw.get(1).getElementsByClass("item");
        if (items2 != null || items2.size() > 0) {
            for (Element item : items2) {
                typeList2.add(getTypeItem(item));
            }
        }

        List<TypeBean> typeList3 = new ArrayList<>();
        Elements items3 = sw.get(2).getElementsByClass("item");
        if (items3 != null || items3.size() > 0) {
            for (Element item : items3) {
                typeList3.add(getTypeItem(item));
            }
        }

        List<TypeBean> typeList4 = new ArrayList<>();
        Elements items4 = sw.get(3).getElementsByClass("item");
        if (items4 != null || items4.size() > 0) {
            for (Element item : items4) {
                typeList4.add(getTypeItem(item));
            }
        }

        TypeBox box = new TypeBox(typeList1, typeList2, typeList3, typeList4);
        return box;
    }

    private static TypeBean getTypeItem(Element item) {
        Element a = item.getElementsByTag("a").get(0);
        String title = a.attr("title");
        String href = a.absUrl("href");
        String data_src = item.getElementsByTag("img").get(0)
                .absUrl("data-src");

        TypeBean bean = new TypeBean(title, href, data_src);
        return bean;

    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////// new

    public static NewBox transToNew(Element body) {
        Elements elements = body.getElementsByClass("mk-floor");
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////  rank
    public static RankBox transToRank(Element body) {
        String[] ifts = new String[]{"ift-fire", "ift-love_money", "ift-ticket"};
        Elements lists = body.getElementsByClass("mk-rank-list");

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
        int id = Integer.parseInt(a.getElementsByTag("img").get(0)
                .attr("data-id"));

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

        BookBean book = new BookBean();
        book.setId(id);
        book.setData_src(data_src);
        book.setTitle(title);
        book.setNum(num);
        book.setIft(ift);
        book.setTags(ts);
        return book;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////// more

    public static MoreBox transToMore(Element body) {

        MoreBox box = new MoreBox();
        box.setHref(body.baseUri());

        String top = body.getElementsByClass("mk-crumb").get(0)
                .getElementsByTag("strong").text();
        String figure = body.getElementsByClass("figure").attr("data-src");
        String content = body.getElementsByClass("book-desc").get(0)
                .getElementsByClass("content").text();

        box.setTitle(top);
        box.setFigure(figure);
        box.setContent(content);

        List<BookBean> books = new ArrayList<>();

        Elements elements = body.getElementsByClass("item");
        for (Element element : elements) {
            Element a = element.getElementsByTag("a").get(0);
            int id = Integer.parseInt(a.getElementsByTag("img").get(0)
                    .attr("data-id"));
            String data_src = a.getElementsByTag("img").get(0)
                    .absUrl("data-src");

            String star = a.getElementsByTag("span").get(0).text();
            String title = element.getElementsByClass("title").text();
            String desc = element.getElementsByClass("content").text();

            BookBean book = new BookBean();
            book.setId(id);
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// details

    public static DetailsBox transToDetails(Element body) {

        Element mk = body.getElementsByClass("mk-detail").get(0);
        // .getElementsByTag("strong").text();
        String title = mk.getElementsByClass("name").text();
        String author = mk.getElementsByClass("author").text();

        Element ci = mk.getElementsByClass("comic-item").get(0);
        int id = Integer.parseInt(ci.getElementsByTag("img").get(0)
                .attr("data-id"));
        String data_src = ci.getElementsByTag("img").get(0).attr("data-src");

        String star = mk.getElementsByClass("ift-xing").text();
        String ift = mk.getElementsByClass("hasread").text();
        Elements tags = mk.getElementsByClass("tags");
        List<String> ts = new ArrayList<>();
        for (Element tag : tags) {
            String t = tag.text();
            ts.add(t);
        }

        BookBean mainBook = new BookBean();
        mainBook.setId(id);
        mainBook.setTitle(title);
        mainBook.setAuthor(author);
        mainBook.setStar(star);
        mainBook.setIft(ift);
        mainBook.setData_src(data_src);
        mainBook.setTags(ts);


        Elements swipers = body.getElementsByClass("tab-item");
        XiBean xi = getXi(swipers.get(0));
        MuBean mu = getMu(swipers.get(1));
        ZiBean zi = getZi(swipers.get(2));

        HuaBean laseHua = mu.getHuaList().get(mu.getHuaList().size() - 1);
        long chapter_id = laseHua.getId();
        String chapter_name = laseHua.getName();
        long update_time = laseHua.getUptime();
        mainBook.setChapter_id(chapter_id);
        mainBook.setChapter(chapter_name);
        mainBook.setDesc(xi.getJuqing());
        mainBook.setUpdate_time(update_time);
        xi.getAuthor().getBookList().add(mainBook);

        mu.setBook_id(mainBook.getId());
        // //////////////
        List<BookBean> bookList = new ArrayList<>();
        Element mk2 = body.getElementsByClass("mk-recommend").get(0);
        Elements items = mk2.getElementsByClass("comic-item");
        if (items != null || items.size() > 0) {
            for (Element item : items) {
                bookList.add(getBook(item));
            }
        }
        RandomSort.sort(bookList);

        DetailsBox box = new DetailsBox(mainBook, xi, mu, zi, bookList);

        return box;
    }


    private static XiBean getXi(Element element) {

        String juqing = element.getElementsByClass("comic-detail").get(0)
                .getElementsByClass("content").get(0).text();
        Element info = element.getElementsByClass("author-info").get(0);
        String author_avatar =
                info.getElementsByClass("author-avatar").get(0)
                        .absUrl("data-src");
        String author_name = info.getElementsByClass("author-name").text();
        String fans_num = info.getElementsByClass("fans-num").text();

        String author_say = element.getElementsByClass("content").get(2).text();
        List<BookBean> books = new ArrayList<>();
        Elements items = element.getElementsByClass("comic-item");
        if (items != null || items.size() > 0) {
            for (Element item : items) {
                BookBean book = getBook(item);
                books.add(book);
            }
        }
        AuthorBean author = new AuthorBean(author_avatar, author_name, fans_num, author_say, books);
        XiBean xi = new XiBean(author, juqing);
        return xi;
    }

    private static MuBean getMu(Element element) {
        Element update = element.getElementById("updateTime");
        long time = Long.parseLong(update.attr("datetime"));
        String time_desc = update.text();

        List<HuaBean> huas = new ArrayList<>();
        Elements items = element.getElementsByClass("item");
        if (items != null || items.size() > 0) {
            int size = items.size();
            for (int i = size - 1; i >= 0; i--) {
                Element item = items.get(i);
                long id = Long.parseLong(item.attr("data-id"));
                long uptime = Long.parseLong(item.attr("data-uptime"));
//                String href = item.getElementsByClass("chapterBtn").get(0)
//                        .absUrl("href");
                String name = item.getElementsByClass("chapterBtn").get(0)
                        .attr("title");
                Elements lock = item.getElementsByClass("lockIcon");
                boolean isLock = lock != null && lock.size() > 0;
                Elements updot = item.getElementsByClass("updot");
                boolean isDot = updot != null && updot.size() > 0;
                HuaBean hua = new HuaBean(id, name, uptime, isLock, isDot);
                hua.setIndex(i);
                huas.add(hua);
            }
        }


        MuBean mu = new MuBean(time_desc, time, huas);
        return mu;
    }

    private static ZiBean getZi(Element element) {
        Element support = element.getElementsByClass("support").get(0);
        Elements nums = support.getElementsByClass("num");

        String[] su = new String[]{
                nums.get(0).text(),
                nums.get(1).text(),
                nums.get(2).text(),
                nums.get(3).text(),
                nums.get(4).text(),
                nums.get(5).text(),
        };


        List<FansBean> fs = new ArrayList<>();
        Element influence = element.getElementsByClass("mk-influencerank-tabs")
                .get(0);

        Element top1 = influence.getElementsByClass("rank-1st").get(0);
        Element top2 = influence.getElementsByClass("rank-2nd").get(0);
        Element top3 = influence.getElementsByClass("rank-3rd").get(0);

        fs.add(getFans(top1));
        fs.add(getFans(top2));
        fs.add(getFans(top3));

        Elements items = influence.getElementsByClass("item");
        if (items != null || items.size() > 0) {
            for (Element item : items) {
                fs.add(getFans2(item));
            }
        }
        ZiBean zi = new ZiBean(fs, su);
        return zi;
    }

    private static FansBean getFans(Element ele) {
        String data_src = ele.getElementsByTag("img").get(0).absUrl("data-src");
        String name = ele.getElementsByClass("name").text();
        String num = ele.getElementsByTag("span").get(0).text();
        FansBean fans = new FansBean(data_src, name, num);
        return fans;

    }

    private static FansBean getFans2(Element ele) {
        String data_src = ele.getElementsByTag("img").get(0).absUrl("data-src");
        String name = ele.getElementsByClass("name").text();
        String num = ele.getElementsByClass("num").get(0).text();
        FansBean fans = new FansBean(data_src, name, num);
        return fans;

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////// ty

    public static TyBox transToTy(Element body) {
        String base = body.baseUri();
        String title = body.getElementsByClass("mk-header").get(0)
                .getElementsByClass("title").get(0).text();

        String next = body.getElementsByClass("mk-pages").get(0)
                .getElementsByClass("next").get(0).absUrl("href");

        Element comic_sort = body.getElementsByClass("comic-sort").get(0);

        List<BookBean> books = new ArrayList<>();
        Elements items = comic_sort.getElementsByClass("comic-item");
        if (items != null || items.size() > 0) {
            for (Element item : items) {
                books.add(getBook(item));
            }
        }

        TyBox box = new TyBox(base, title, next, books);

        return box;
    }

}