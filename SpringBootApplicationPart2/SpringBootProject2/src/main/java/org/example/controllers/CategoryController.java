package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.CategoryDTO;
import org.example.services.CRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CRUDService<CategoryDTO> categoryService;

    // Получение списка всех категорий
    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        Collection<CategoryDTO> listCategory = categoryService.getAll();
        return new ResponseEntity<>(listCategory, HttpStatus.OK);
    }

    // Получение категории по id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return (categoryService.getById(id) != null)? new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK) :
                new ResponseEntity<>("Категория с ID " + id + " не найдена." ,HttpStatus.NOT_FOUND);

    }

    // Создание категории
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.create(categoryDTO), HttpStatus.CREATED);

    }

    // Редактирование категории
    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return new ResponseEntity<>(categoryService.getById(categoryDTO.getId()), HttpStatus.OK);
    }

    // Удаление категории по id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(categoryService.getById(id) != null){
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Категория с ID " + id + " не найдена." ,HttpStatus.NOT_FOUND);
    }


}
