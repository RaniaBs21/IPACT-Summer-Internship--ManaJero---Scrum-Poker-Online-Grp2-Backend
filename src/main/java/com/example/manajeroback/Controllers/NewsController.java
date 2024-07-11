
package com.example.manajeroback.Controllers;


import com.example.manajeroback.entities.News;
import com.example.manajeroback.services.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class NewsController {
    NewsService newsService;


    @PostMapping("/addNews")
    News addNews(@RequestBody News news) {
        return  newsService.addNews(news);
    }


    @GetMapping("/getNews")
    List<News> getNews(){
        return newsService.getAllNews();
    }

    @GetMapping("/getNews/{id}")
    public News getNewsById(@PathVariable String id) {
        return newsService.getNewsById(id);
    }

    @PutMapping("/updateNew/{id}")
    public News updateNew(@PathVariable String id, @RequestBody News news) {
        return newsService.updateNews(news, id);

    }

    @DeleteMapping("/deleteNews/{id}")
    void deleteStep(@PathVariable String id) {
        newsService.deleteNews(id);
    }
}
