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

    // никто не сортирует новость по id на фронте, сортируют обычно по дате создания или дате обновления
    @GetMapping("/news")
    public String findAll(Model model, @PageableDefault(sort = {"creationDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        //TODO: что за херня? почему два раза вызывается из базы данных все новости??
        //это дает огромную нагрузку на продакшене, на базу данных, такое никогда нельзя писать
        Page<News> page = newsService.findAll(pageable);
        Page<News> pageableNews = newsService.findAll(pageable);

        model.addAttribute("news", pageableNews);

        if (pageableNews.hasNext()) {
            model.addAttribute("nextPage", "/news?page=" + (pageable.getPageNumber() + 1));
        }

        if (pageableNews.hasPrevious()) {
            model.addAttribute("previousPage", "/news?page=" + (pageable.getPageNumber() - 1));
        }

        return "news";

    }

    @GetMapping("/news-create")
    public String createNewsForm(News news) {
        return "news-create";
    }

    @PostMapping("news-create")
    public String addNews(News news) {
        newsService.save(news);
        return "redirect:/news";

    }

    //нажимаем кнопку удалить и сюда передается из html id записи, которую нужно удалить из бд
    @GetMapping("/news-delete/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNewsById(id);
        return "redirect:/news";
    }

    // получаем новость для html формы редактирования чтобы ее поля можно было отредактировать
    @GetMapping("/news-edit/{id}")
    public String editNewsForm(@PathVariable("id") Long id, Model model) {
        News news = newsService.findNewsById(id);
        model.addAttribute("news", news);

        return "news-edit";
    }

    @PostMapping("/news-edit/{id}")
    public String editUser(News news) {
        newsService.update(news);
        return "redirect:/news";
    }



}





