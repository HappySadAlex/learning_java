package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.NewsDTO;
import org.example.services.CRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final CRUDService<NewsDTO> newsCRUDService;

    // Получение новости по id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return (newsCRUDService.getById(id) != null)? new ResponseEntity<>(newsCRUDService.getById(id), HttpStatus.OK) :
                new ResponseEntity<>("Новость с ID " + id + " не найдена." ,HttpStatus.NOT_FOUND);

    }

    // Получение новостей по идентификатору категории
    @GetMapping(path = "/category/{id}")
    public ResponseEntity<?> getNewsByCatId(@PathVariable Long id){
        return new ResponseEntity<>(newsCRUDService.getByAttr(id), HttpStatus.OK);
    }

    // Получение всех новостей
    @GetMapping
    public ResponseEntity<?> getAllNews(){
        Collection<NewsDTO> listNews = newsCRUDService.getAll();
        return new ResponseEntity<>(listNews, HttpStatus.OK);
    }

    // Создание новости
    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody NewsDTO newsDTO){
        return new ResponseEntity<>(newsCRUDService.create(newsDTO), HttpStatus.CREATED);
    }

    // Редактирование новости
    @PutMapping
    public ResponseEntity<?> updateNews(@RequestBody NewsDTO newsDTO){
        newsCRUDService.update(newsDTO);
        return new ResponseEntity<>(newsCRUDService.getById(newsDTO.getId()), HttpStatus.OK);
    }

    // Удаление новости по id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(newsCRUDService.getById(id) != null){
            newsCRUDService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Новость с ID " + id + " не найдена." ,HttpStatus.NOT_FOUND);
    }




}
