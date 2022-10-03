package com.salmanhaljido.demo.domain.news.service;

import com.salmanhaljido.demo.domain.news.dto.NewsListResponseDto;
import com.salmanhaljido.demo.domain.news.entity.Category;
import com.salmanhaljido.demo.domain.news.entity.News;
import com.salmanhaljido.demo.domain.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    private final String PARENTING_AND_EDUCATION_URL = "https://www.ibabynews.com/news/articleList.html?sc_section_code=S1N4&view_type=sm";
    private final String SOCIAL_AND_POLICY_URL = "https://www.ibabynews.com/news/articleList.html?sc_section_code=S1N1&view_type=sm";
    private final String PREGNANT_AND_DELIVERY_URL = "https://www.ibabynews.com/news/articleList.html?sc_section_code=S1N2&view_type=sm";
    private final String LIFE_AND_HEALTH_URL = "https://www.ibabynews.com/news/articleList.html?sc_section_code=S1N3&view_type=sm";
    private final String REAL_ESTATE_URL = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&";

    @Override
    public NewsListResponseDto getNews(Category category, int pageNo, String search) {
        PageRequest pageRequest = PageRequest.of(pageNo, 8);
        Page<News> newsPage;
        if(search==null)
            newsPage = newsRepository.findAllByCategory(category, pageRequest);
        else{
            List<String> list = new ArrayList<>();
            list.add("서울");
            list.add("부산");
            list.add("대구");
            list.add("인천");
            list.add("광주");
            list.add("대전");
            list.add("울산");
            list.add("세종");
            list.add("경기");
            list.add("강원");
            list.add("충북");
            list.add("충남");
            list.add("전북");
            list.add("전남");
            list.add("경북");
            list.add("경남");
            list.add("제주");
            String tokens[] =search.split(" ");
            String str = search;
            for(String s : list){
                if(tokens[0].contains(s)) {
                    str = s;
                    break;
                }
            }
            if(tokens.length==1){
                newsPage = newsRepository.findAllByCategoryOrTitleOrSummaryWithPagination(category.name(), "%" + str + "%", "%" + str + "%", pageRequest);
            }else{
                newsPage = newsRepository.findAllByCategoryOrTitleOrSummaryWithPagination(category.name(), "%" + str + "%", "%" + tokens[1] + "%", pageRequest);
            }
        }
        return NewsListResponseDto.of(newsPage);
    }

    @Override
    @Transactional
   //@PostConstruct
    @Scheduled(cron = "0 0 * * * ?")
    public void crawlingParentingAndEducation() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:SS");
        int pageNo = 1;
        while(true) {
            Connection connection = Jsoup.connect(PARENTING_AND_EDUCATION_URL + "&page=" + pageNo);
            try {
                Document document = connection.get();
                Elements listBlock = document.getElementsByClass("list-block");
                for (Element e : listBlock) {
                    String title = e.select(".list-titles").text();
                    String summary = e.select(".list-summary").text();
                    String url = e.select(".list-titles > a").attr("abs:href");
                    Date createdAt = simpleDateFormat.parse(e.select(".list-dated").text().split("\\|")[2].trim());

                    Connection imagePathConnection = Jsoup.connect(url);
                    Document imagePathDocument = imagePathConnection.get();
                    String imagePath = imagePathDocument.head().select("meta[property=og:image]").attr("abs:content");
                    if(createdAt.before(time)){
                        return;
                    }
                    News news = News.builder()
                            .title(title)
                            .summary(summary)
                            .category(Category.PARENTING_AND_EDUCATION)
                            .url(url)
                            .createdAt(createdAt)
                            .imagePath(imagePath)
                            .build();
                    newsRepository.save(news);
                }
                pageNo++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    //@PostConstruct
    @Scheduled(cron = "0 0 * * * ?")
    public void crawlingSocialAndPolicy() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:SS");
        int pageNo = 1;
        while(true) {
            Connection connection = Jsoup.connect(SOCIAL_AND_POLICY_URL + "&page=" + pageNo);
            try {
                Document document = connection.get();
                Elements listBlock = document.getElementsByClass("list-block");
                for (Element e : listBlock) {
                    String title = e.select(".list-titles").text();
                    String summary = e.select(".list-summary").text();
                    String url = e.select(".list-titles > a").attr("abs:href");
                    Date createdAt = simpleDateFormat.parse(e.select(".list-dated").text().split("\\|")[2].trim());

                    Connection imagePathConnection = Jsoup.connect(url);
                    Document imagePathDocument = imagePathConnection.get();
                    String imagePath = imagePathDocument.head().select("meta[property=og:image]").attr("abs:content");
                    if(createdAt.before(time)){
                        return;
                    }
                    News news = News.builder()
                            .title(title)
                            .summary(summary)
                            .category(Category.SOCIAL_AND_POLICY)
                            .url(url)
                            .createdAt(createdAt)
                            .imagePath(imagePath)
                            .build();
                    newsRepository.save(news);
                }
                pageNo++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
   // @PostConstruct
    @Scheduled(cron = "0 0 * * * ?")
    public void crawlingPregnantAndDelivery() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:SS");
        int pageNo = 1;
        while(true) {
            Connection connection = Jsoup.connect(PREGNANT_AND_DELIVERY_URL + "&page=" + pageNo);
            try {
                Document document = connection.get();
                Elements listBlock = document.getElementsByClass("list-block");
                for (Element e : listBlock) {
                    String title = e.select(".list-titles").text();
                    String summary = e.select(".list-summary").text();
                    String url = e.select(".list-titles > a").attr("abs:href");
                    Date createdAt = simpleDateFormat.parse(e.select(".list-dated").text().split("\\|")[2].trim());

                    Connection imagePathConnection = Jsoup.connect(url);
                    Document imagePathDocument = imagePathConnection.get();
                    String imagePath = imagePathDocument.head().select("meta[property=og:image]").attr("abs:content");
                    if(createdAt.before(time)){
                        return;
                    }
                    News news = News.builder()
                            .title(title)
                            .summary(summary)
                            .category(Category.PREGNANT_AND_DELIVERY)
                            .url(url)
                            .createdAt(createdAt)
                            .imagePath(imagePath)
                            .build();
                    newsRepository.save(news);
                }
                pageNo++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    //@PostConstruct
    @Scheduled(cron = "0 0 * * * ?")
    public void crawlingLifeAndHealth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:SS");
        int pageNo = 1;
        while(true) {
            Connection connection = Jsoup.connect(LIFE_AND_HEALTH_URL + "&page=" + pageNo);
            try {
                Document document = connection.get();
                Elements listBlock = document.getElementsByClass("list-block");
                for (Element e : listBlock) {
                    String title = e.select(".list-titles").text();
                    String summary = e.select(".list-summary").text();
                    String url = e.select(".list-titles > a").attr("abs:href");
                    Date createdAt = simpleDateFormat.parse(e.select(".list-dated").text().split("\\|")[2].trim());

                    Connection imagePathConnection = Jsoup.connect(url);
                    Document imagePathDocument = imagePathConnection.get();
                    String imagePath = imagePathDocument.head().select("meta[property=og:image]").attr("abs:content");
                    if(createdAt.before(time)){
                        return;
                    }
                    News news = News.builder()
                            .title(title)
                            .summary(summary)
                            .category(Category.LIFE_AND_HEALTH)
                            .url(url)
                            .createdAt(createdAt)
                            .imagePath(imagePath)
                            .build();
                    newsRepository.save(news);
                }
                pageNo++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    //@PostConstruct
    public void crawlingRealEstate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        Date now = new Date();
        Date createdAt = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        int pageNo = 1;
        while(true) {
            Connection connection = Jsoup.connect(REAL_ESTATE_URL + "&date=" + simpleDateFormat.format(now) + "&page=" + pageNo);
            System.out.println(REAL_ESTATE_URL + "&date=" + simpleDateFormat.format(now) + "&page=" + pageNo);
            try {
                Document document = connection.get();
                Elements list = document.getElementsByClass("type06_headline").select("li");
                for (Element e : list) {
                    String url = e.select("dt:last-of-type > a").attr("abs:href");
                    String title = e.select("dt:last-of-type > a").text();
                    String summary = e.select("dd").select(".lede").text();
                    Connection imagePathConnection = Jsoup.connect(url);
                    Document imagePathDocument = imagePathConnection.get();
                    String imagePath = imagePathDocument.head().select("meta[property=og:image]").attr("abs:content");

                    if(e.select("dd").select(".date").text().equals("1시간전")) return;

                    News news = News.builder()
                            .title(title)
                            .summary(summary)
                            .category(Category.REAL_ESTATE)
                            .url(url)
                            .createdAt(createdAt)
                            .imagePath(imagePath)
                            .build();
                    newsRepository.save(news);
                }
                pageNo++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
