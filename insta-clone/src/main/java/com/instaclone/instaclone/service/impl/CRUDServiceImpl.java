package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.exception.NotFoundException;
import com.instaclone.instaclone.model.BaseEntity;
import com.instaclone.instaclone.service.CRUDService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CRUDServiceImpl<T extends BaseEntity> implements CRUDService<T> {
    protected abstract JpaRepository<T, Long> getEntityRepository();

    @Override
    public List<T> findAll() {
        return getEntityRepository().findAll();
    }

    @Override
    public T findOne(Long id) {
        return findActiveEntity(id);
    }

    @Override
    public Optional<T> findById(Long id) {
        return getEntityRepository().findById(id);
    }

    @Override
    public T save(T entity) {
        return getEntityRepository().save(entity);
    }

    @Override
    public T update(T entity) {
        return getEntityRepository().save(entity);
    }

    @Override
    public void delete(Long id) {
        var entity = findActiveEntity(id);
        entity.setActive(false);
        save(entity);
    }

    private T findActiveEntity(Long id) throws RuntimeException {
        var entity = getEntityRepository().findById(id).orElseThrow(() -> new NotFoundException("Cannot find entity with id: "+ id));
        if(entity.getActive())
            return entity;

        throw new NotFoundException("Cannot find entity with id: " + id);
    }
}
