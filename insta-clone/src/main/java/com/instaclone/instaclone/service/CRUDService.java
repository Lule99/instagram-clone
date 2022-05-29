package com.instaclone.instaclone.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {
    List<T> findAll();
    T findOne(Long id);
    Optional<T> findById(Long id);
    T save(T entity);
    void saveAll(List<T> entity);
    T update(T entity);
    void delete(Long id);
}
