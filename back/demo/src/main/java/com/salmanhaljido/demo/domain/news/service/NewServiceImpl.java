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

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class NewServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    private String PARENTING_AND_EDUCATION_URL = "https://www.ibabynews.com/news/articleList.html?sc_section_code=S1N4&view_type=sm";

    @Override
    public NewsListResponseDto getNews(Category category, int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo, 10);
        Page<News> newsPage = newsRepository.findAllByCategory(category, pageRequest);
        return NewsListResponseDto.of(newsPage);
    }

    @Override
    @Transactional
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
}
