package com.instaclone.instaclone.model;

import com.instaclone.instaclone.model.enums.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "insta_user")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class User extends BaseEntity {

    @OneToOne
    private Profile profile;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
