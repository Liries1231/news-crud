package com.kurs.lirie.service;

import com.kurs.lirie.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kurs.lirie.reposes.NewsRepository;

import java.util.Date;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;




    public Page<News> findAll(Pageable pageable) { //read
        //select *from news;
        return newsRepository.findAll(pageable);
    }

    public void deleteNewsById(Long id) { //delete
        newsRepository.deleteById(id);
    }


    public News newsAdd(News news) {
        news.setCreationDate(new Date());
        return newsRepository.save(news);
    }


    public News findNewsById(Long id){
     return newsRepository.getOne(id);

    }

    public News useDates(Long id){
        News news = newsRepository.getOne(id);
        news.setUpdateDate(new Date());
      return   newsRepository.save(news);

    }



}




