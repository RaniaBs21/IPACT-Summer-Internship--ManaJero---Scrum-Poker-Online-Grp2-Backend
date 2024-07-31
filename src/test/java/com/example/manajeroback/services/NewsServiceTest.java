package com.example.manajeroback.services;

import com.example.manajeroback.entities.News;
import com.example.manajeroback.repositories.NewsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    private NewsRepository newsRepository;

    @Test
    void addNews() {
        News news = new News();
        news.setTitle("Test Title");
        news.setNewsDescription("Test Description");

        Mockito.when(newsRepository.save(Mockito.any(News.class))).thenReturn(news);

        News savedNews = newsService.addNews(news);

        Assertions.assertEquals("Test Title", savedNews.getTitle());
        Assertions.assertEquals("Test Description", savedNews.getNewsDescription());

        System.out.println("test addNews validé");
        System.out.println(savedNews);
    }

    @Test
    void getAllNewsTest() {
        List<News> newsList = Arrays.asList(
                new News("1", "Title1", "Description1"),
                new News("2", "Title2", "Description2")
        );

        Mockito.when(newsRepository.findAll()).thenReturn(newsList);

        List<News> result = newsService.getAllNews();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Title1", result.get(0).getTitle());
        Assertions.assertEquals("Title2", result.get(1).getTitle());

        System.out.println("Retrieved news: " + result);
        for (News news : result) {
            System.out.println("Title: " + news.getTitle());
            System.out.println("Description: " + news.getNewsDescription());
        }

        Mockito.verify(newsRepository).findAll();
    }

    @Test
    void getNewsByIdTest() {
        String newsId = "1";
        News news = new News("1", "Test Title", "Test Description");

        Mockito.when(newsRepository.findById(newsId)).thenReturn(Optional.of(news));

        News result = newsService.getNewsById(newsId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(newsId, result.getId());
        Assertions.assertEquals("Test Title", result.getTitle());
        Assertions.assertEquals("Test Description", result.getNewsDescription());

        System.out.println("Retrieved News: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Description: " + result.getNewsDescription());

        Mockito.verify(newsRepository).findById(newsId);
    }

    @Test
    void updateNews() {
        String id = "1";
        News existingNews = new News("1", "Old Title", "Old Description");
        News updatedNews = new News("1", "New Title", "New Description");

        Mockito.when(newsRepository.findById(id)).thenReturn(Optional.of(existingNews));
        Mockito.when(newsRepository.save(existingNews)).thenReturn(existingNews);

        News result = newsService.updateNews(updatedNews, id);

        Assertions.assertEquals("New Title", result.getTitle());
        Assertions.assertEquals("New Description", result.getNewsDescription());

        Mockito.verify(newsRepository).findById(id);
        Mockito.verify(newsRepository).save(existingNews);

        System.out.println("test updateNews validé");
        System.out.println("Updated News: " + result);
        System.out.println("Title: " + result.getTitle());
        System.out.println("Description: " + result.getNewsDescription());
    }

    @Test
    void deleteNews() {
        String id = "1";

        newsService.deleteNews(id);

        Mockito.verify(newsRepository).deleteById(id);

        System.out.println("test deleteNews validé");
        System.out.println("Deleted News with ID: " + id);
    }
}
