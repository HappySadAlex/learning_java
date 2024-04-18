package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CategoryDTO;
import org.example.entites.Category;
import org.example.repositories.CategoryRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;


@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryCRUDService implements CRUDService<CategoryDTO> {

    private final CategoryRepo categoryRepo;

    @Override
    public CategoryDTO getById(Long id) {
        if(id == null){
            return mapToDTO(categoryRepo.findAll().stream().max((o1, o2) -> o1.getId().compareTo(o2.getId())).orElseThrow());
        }
        else if(categoryRepo.existsById(id)) {
            return mapToDTO(categoryRepo.findById(id).orElseThrow());
        }
        return null;
    }

    @Override
    public Collection<CategoryDTO> getAll() {
        return categoryRepo.findAll()
                .stream()
                .map(CategoryCRUDService::mapToDTO)
                .toList();
    }


    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        log.info("Create category");
        Category category = new Category();
        category.setId((long)(categoryRepo.findAll().stream().max((o1, o2) -> o1.getId().compareTo(o2.getId())).orElseThrow().getId() + 1));
        category.setTitle(categoryDTO.getTitle());
        categoryRepo.save(category);
        return mapToDTO(category);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        log.info("Update category");
        Category category = mapToEntity(categoryDTO);
        categoryRepo.save(category);
        return mapToDTO(category);
    }

    @Override
    public void delete(Long id) {
        log.info("Delete category " + id);
        categoryRepo.deleteById(id);
    }

    @Override
    public Collection<CategoryDTO> getByAttr(Long id) {
        return null;
    }

    public static CategoryDTO mapToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        return categoryDTO;
    }

    public static Category mapToEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setTitle(categoryDTO.getTitle());
        return category;
    }

}
