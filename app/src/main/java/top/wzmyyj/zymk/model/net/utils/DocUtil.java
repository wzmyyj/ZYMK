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
import top.wzmyyj.wzm_sdk.utils.RandomSortUtil;
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

    //------------------------------ home ----------------------------------------//

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
            RandomSortUtil.sort(books);
            item.setBooks(books);
            items.add(item);
        }
        RandomSortUtil.sort(items, 0, 3);
        RandomSortUtil.sort(items, 4, items.size() - 2);
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
            StringBuilder sb = new StringBuilder(star);
            sb.insert(1, ".");
            star = sb.toString();
        }
        BookBean book = new BookBean();
        book.setId(id);
        book.setDataSrc(data_src);
        book.setTitle(title);
        book.setChapter(chapter);
        book.setDesc(desc);
        book.setStar(star);
        return book;
    }

    public static List<BoBean> transToBo(Element ele) {
        if (ele == null) return null;
        List<BoBean> data = new ArrayList<>();
        Element top = ele.getElementsByClass("swiper-banner").get(0);
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
        return new HomeBox(bos, items);
    }

    //------------------------------ type ----------------------------------------//

    public static TypeBox transToType(Element body) {
        Elements sw = body.getElementsByClass("mk-class-list");
        List<TypeBean> typeList1 = new ArrayList<>();
        Elements items1 = sw.get(0).getElementsByClass("item");
        if (items1 != null && items1.size() > 0) {
            for (Element item : items1) {
                typeList1.add(getTypeItem(item));
            }
        }
        List<TypeBean> typeList2 = new ArrayList<>();
        Elements items2 = sw.get(1).getElementsByClass("item");
        if (items2 != null && items2.size() > 0) {
            for (Element item : items2) {
                typeList2.add(getTypeItem(item));
            }
        }
        List<TypeBean> typeList3 = new ArrayList<>();
        Elements items3 = sw.get(2).getElementsByClass("item");
        if (items3 != null && items3.size() > 0) {
            for (Element item : items3) {
                typeList3.add(getTypeItem(item));
            }
        }
        List<TypeBean> typeList4 = new ArrayList<>();
        Elements items4 = sw.get(3).getElementsByClass("item");
        if (items4 != null && items4.size() > 0) {
            for (Element item : items4) {
                typeList4.add(getTypeItem(item));
            }
        }
        return new TypeBox(typeList1, typeList2, typeList3, typeList4);
    }

    private static TypeBean getTypeItem(Element item) {
        Element a = item.getElementsByTag("a").get(0);
        String title = a.attr("title");
        String href = a.absUrl("href");
        String data_src = item.getElementsByTag("img").get(0)
                .absUrl("data-src");
        return new TypeBean(title, href, data_src);
    }

    //------------------------------ new ----------------------------------------//

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
        RandomSortUtil.sort(books1);
        RandomSortUtil.sort(books2);
        return new NewBox(books1, books2);
    }

    //------------------------------ rank ----------------------------------------//

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
        return new RankBox(bookList1, bookList2, bookList3);
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
        book.setDataSrc(data_src);
        book.setTitle(title);
        book.setNum(num);
        book.setIft(ift);
        book.setTags(ts);
        return book;
    }

    //------------------------------ more ----------------------------------------//

    public static MoreBox transToMore(Element body) {
        String href = body.baseUri();
        String top = body.getElementsByClass("mk-crumb").get(0)
                .getElementsByTag("strong").text();
        String figure = body.getElementsByClass("figure").attr("data-src");
        String content = body.getElementsByClass("book-desc").get(0)
                .getElementsByClass("content").text();
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
            book.setDataSrc(data_src);
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
        RandomSortUtil.sort(books);
        return new MoreBox(href, top, content, figure, books);
    }

    //------------------------------ details ----------------------------------------//

    public static DetailsBox transToDetails(Element body) {
        Element mk = body.getElementsByClass("mk-detail").get(0);
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
        mainBook.setDataSrc(data_src);
        mainBook.setTags(ts);
        Elements tabs = body.getElementsByClass("tab-item");
        XiBean xi = getXi(tabs.get(0));
        MuBean mu = getMu(tabs.get(1));
        ZiBean zi = getZi(tabs.get(2));
        HuaBean laseHua = mu.getHuaList().get(mu.getHuaList().size() - 1);
        long chapter_id = laseHua.getId();
        String chapter_name = laseHua.getName();
        long update_time = laseHua.getUptime();
        mainBook.setChapterId(chapter_id);
        mainBook.setChapter(chapter_name);
        mainBook.setDesc(xi.getPlot());
        mainBook.setUpdateTime(update_time);
        xi.getAuthor().getBookList().add(mainBook);
        mu.setBook_id(mainBook.getId());
        List<BookBean> bookList = new ArrayList<>();
        Element mk2 = body.getElementsByClass("mk-recommend").get(0);
        Elements items = mk2.getElementsByClass("comic-item");
        if (items != null && items.size() > 0) {
            for (Element item : items) {
                bookList.add(getBook(item));
            }
        }
        RandomSortUtil.sort(bookList);
        return new DetailsBox(mainBook, xi, mu, zi, bookList);
    }

    private static XiBean getXi(Element element) {
        String plot = element.getElementsByClass("comic-detail").get(0)
                .getElementsByClass("content").get(0).text();
        Element info = element.getElementsByClass("author-info").get(0);
        String author_avatar = info.getElementsByClass("author-avatar").get(0)
                .absUrl("data-src");
        String author_name = info.getElementsByClass("author-name").text();
        String fans_num = info.getElementsByClass("fans-num").text();
        String author_say = element.getElementsByClass("content").get(2).text();
        List<BookBean> books = new ArrayList<>();
        Elements items = element.getElementsByClass("comic-item");
        if (items != null && items.size() > 0) {
            for (Element item : items) {
                BookBean book = getBook(item);
                books.add(book);
            }
        }
        AuthorBean author = new AuthorBean(author_avatar, author_name, fans_num, author_say, books);
        return new XiBean(author, plot);
    }

    private static MuBean getMu(Element element) {
        Element update = element.getElementById("updateTime");
        String time_desc = update.text();
        List<HuaBean> huaList = new ArrayList<>();
        Elements items = element.getElementsByClass("item");
        if (items != null && items.size() > 0) {
            int size = items.size();
            for (int i = size - 1; i >= 0; i--) {
                Element item = items.get(i);
                long id = Long.parseLong(item.attr("data-id"));
                long uptime = Long.parseLong(item.attr("data-uptime"));
                String name = item.getElementsByClass("chapterBtn").get(0)
                        .attr("title");
                Elements lock = item.getElementsByClass("lockIcon");
                boolean isLock = lock != null && lock.size() > 0;
                Elements updot = item.getElementsByClass("updot");
                boolean isDot = updot != null && updot.size() > 0;
                HuaBean hua = new HuaBean(id, name, uptime, isLock, isDot);
                hua.setIndex(i);
                huaList.add(hua);
            }
        }
        return new MuBean(time_desc, huaList);
    }

    private static ZiBean getZi(Element element) {
        Element support = element.getElementsByClass("support").get(0);
        Elements numList = support.getElementsByClass("num");
        String[] su = new String[]{
                numList.get(0).text(),
                numList.get(1).text(),
                numList.get(2).text(),
                numList.get(3).text(),
                numList.get(4).text(),
                numList.get(5).text(),
        };
        List<FansBean> fs = new ArrayList<>();
        Element influence = element.getElementsByClass("mk-influencerank-tabs").get(0);
        try {
            Element top1 = influence.getElementsByClass("rank-1st").get(0);
            Element top2 = influence.getElementsByClass("rank-2st").get(0);
            Element top3 = influence.getElementsByClass("rank-3st").get(0);
            fs.add(getFans(top1));
            fs.add(getFans(top2));
            fs.add(getFans(top3));
            Elements items = influence.getElementsByClass("item");
            if (items != null && items.size() > 0) {
                for (Element item : items) {
                    fs.add(getFans2(item));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ZiBean(fs, su);
    }

    private static FansBean getFans(Element ele) {
        String data_src = ele.getElementsByTag("img").get(0).absUrl("data-src");
        String name = ele.getElementsByClass("name").text();
        String num = ele.getElementsByTag("span").get(0).text();
        return new FansBean(data_src, name, num);
    }

    private static FansBean getFans2(Element ele) {
        String data_src = ele.getElementsByTag("img").get(0).absUrl("data-src");
        String name = ele.getElementsByClass("name").text();
        String num = ele.getElementsByClass("num").get(0).text();
        return new FansBean(data_src, name, num);
    }

    //------------------------------ ty ----------------------------------------//

    public static TyBox transToTy(Element body) {
        String base = body.baseUri();
        String title = body.getElementsByClass("mk-header").get(0)
                .getElementsByClass("title").get(0).text();
        String next = body.getElementsByClass("mk-pages").get(0)
                .getElementsByClass("next").get(0).absUrl("href");
        Element comic_sort = body.getElementsByClass("comic-sort").get(0);
        List<BookBean> books = new ArrayList<>();
        Elements items = comic_sort.getElementsByClass("comic-item");
        if (items != null && items.size() > 0) {
            for (Element item : items) {
                books.add(getBook(item));
            }
        }
        return new TyBox(base, title, next, books);
    }
}