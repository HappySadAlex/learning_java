package org.example.services;

import java.util.Collection;

public interface CRUDService<T> {
     T getById(Long id);
     Collection<T> getAll();
     T create(T item);
     T update(T item);
     void delete(Long id);
     Collection<T> getByAttr(Long id);

}
