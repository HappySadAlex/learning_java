package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.NewsDTO;
import org.example.entites.Category;
import org.example.entites.News;
import org.example.repositories.CategoryRepo;
import org.example.repositories.NewsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class NewsCRUDService implements CRUDService<NewsDTO> {

    private final NewsRepo newsRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public NewsDTO getById(Long id) {
        if(!newsRepo.existsById(id)){
            return null;
        }
        News news = newsRepo.findById(id).orElseThrow();
        return mapToDTO(news);
    }

    @Override
    public Collection<NewsDTO> getAll() {
        log.info("Get all");
        return newsRepo.findAll()
                .stream()
                .map(NewsCRUDService::mapToDTO)
                .toList();
    }


    @Override
    public NewsDTO create(NewsDTO newsDTO) {
        log.info("Create...");
        News news = new News();
        String categoryName = newsDTO.getCategory();
        Long catId = categoryRepo.findAll().stream().filter(e->categoryName.equals(e.getTitle())).findFirst().map(Category::getId).orElseThrow();
        news.setCategory(categoryRepo.findById(catId).orElseThrow());
        news.setText(newsDTO.getText());
        news.setTitle(newsDTO.getTitle());
        news.setId(newsDTO.getId());
        newsRepo.save(news);
        return mapToDTO(news);
    }


    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        log.info("Update");
        News news = new News();
        news.setId(newsDTO.getId());
        String categoryName = newsDTO.getCategory();
        Long catId = categoryRepo.findAll().stream().filter(e->categoryName.equals(e.getTitle())).findFirst().map(Category::getId).orElseThrow();
        news.setCategory(categoryRepo.findById(catId).orElseThrow());
        news.setText(newsDTO.getText());
        news.setTitle(newsDTO.getTitle());
        news.setId(newsDTO.getId());
        newsRepo.save(news);
        return mapToDTO(news);
    }

    @Override
    public void delete(Long id) {
        log.info("Delete " + id);
        newsRepo.deleteById(id);
    }

    @Override
    public Collection<NewsDTO> getByAttr(Long id) {
        Collection<NewsDTO> newsWithId = new ArrayList<>();
        if(categoryRepo.existsById(id)){
            String categoryName = categoryRepo.findById(id).get().getTitle();
            newsRepo.findAll().stream().filter(news -> categoryName.equals(news.getCategory().getTitle())).map(NewsCRUDService::mapToDTO).forEach(newsWithId::add);
        }
        return newsWithId;
    }

    public static NewsDTO mapToDTO(News news){
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setText(news.getText());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setDate(news.getDate());
        if(news.getCategory() != null) {
            newsDTO.setCategory(CategoryCRUDService.mapToDTO(news.getCategory()).getTitle());
        }
        else{
            newsDTO.setCategory("");
        }
        return newsDTO;
    }

    public static News mapToEntity(NewsDTO newsDTO){
        News news = new News();
        news.setId(newsDTO.getId());
        news.setText(newsDTO.getText());
        news.setTitle(newsDTO.getTitle());
        news.setDate(newsDTO.getDate());
        return news;
    }

}
