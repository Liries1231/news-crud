package com.kurs.lirie.controller;

import com.kurs.lirie.model.News;
import com.kurs.lirie.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class NewsController {





    private final NewsService newsService;


    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String findAll(Model model,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable) {
        Page<News> news = newsService.findAll(pageable);

        model.addAttribute("news", news);

        return "news";

    }

    @GetMapping("/news-create")
    public String createNewsForm(News news) {
        return "news-create";
    }

    @PostMapping("news-create")
    public String addNews(News news) {
        newsService.newsAdd(news);
        return "redirect:/news";

    }

    @GetMapping("/news-delete/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNewsById(id);
        return "redirect:/news";

    }

    @GetMapping("/news-edit/{id}")
    public String editNewsForm(@PathVariable("id") Long id, Model model) {
        News news = newsService.useDates(id);
        model.addAttribute("news", news);

        return "news-edit";
    }

    @PostMapping("/news-edit/{id}")
    public String editUser() {
        return "redirect:/news";
    }



}





