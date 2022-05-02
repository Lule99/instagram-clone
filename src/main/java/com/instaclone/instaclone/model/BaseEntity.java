package com.instaclone.instaclone.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@Where(clause = "active = true")
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Boolean active;
    @Column(nullable = false)
    private LocalDateTime timeCreated;

    public BaseEntity()
    {
        active = true;
    }
}
