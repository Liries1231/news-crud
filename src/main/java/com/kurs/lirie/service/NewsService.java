package com.kurs.lirie.service;

import com.kurs.lirie.model.News;
import com.kurs.lirie.reposes.NewsRepository;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

  private final NewsRepository newsRepository;

  public NewsService(NewsRepository newsRepository) {
    this.newsRepository = newsRepository;
  }

  public Page<News> findAll(Pageable pageable) {
    return newsRepository.findAll(pageable);
  }

  public void deleteNewsById(Long id) { //delete
    newsRepository.deleteById(id);
  }


  public void save(News news) {
    news.setCreationDate(new Date());
    newsRepository.save(news);
  }

  public News findNewsById(Long id) {
    return newsRepository.getOne(id);

  }


  @Deprecated(
      since = "какая-то хуйня, когда открываешь новость в форме редактирования " +
          " НОВОСТЬ УЖЕ СОХРАНЯЕТСЯ, а должна сохраняться после нажатия кнопки",
      forRemoval = true
  )
  public News useDates(Long id) {
    News news = newsRepository.getOne(id);
    news.setUpdateDate(new Date());
    return newsRepository.save(news);
  }

  public void update(News news) {
    news.setUpdateDate(new Date());
    newsRepository.save(news);
  }

}




