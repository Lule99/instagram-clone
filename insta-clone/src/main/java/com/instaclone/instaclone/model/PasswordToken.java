package com.instaclone.instaclone.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PasswordToken extends BaseEntity {
    private String token;

    @OneToOne(targetEntity = User.class)
    private User user;

    private LocalDate expiryDate;
}