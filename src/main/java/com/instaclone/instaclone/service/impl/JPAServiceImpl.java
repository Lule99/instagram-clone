package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.model.BaseEntity;
import com.instaclone.instaclone.service.JPAService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class JPAServiceImpl<T extends BaseEntity> extends CRUDServiceImpl<T> implements JPAService<T> {
    @Override
    public Iterable<T> findAll(Sort sorter) {
        return getEntityRepository().findAll(sorter);
    }

    @Override
    public Page<T> findAll(Pageable page) {
        return getEntityRepository().findAll(page);
    }
}
