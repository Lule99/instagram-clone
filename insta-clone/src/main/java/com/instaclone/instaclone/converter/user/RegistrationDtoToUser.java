package com.instaclone.instaclone.converter.user;

import com.instaclone.instaclone.converter.Converter;
import com.instaclone.instaclone.dto.auth.RegistrationDto;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RegistrationDtoToUser extends Converter<RegistrationDto, User> {

    @Override
    public User convert(RegistrationDto source) {
        User user = getModelMapper().map(source, User.class);
        user.setRole(Role.REGULAR_USER);
        user.setTimeCreated(LocalDateTime.now());
        return user;
    }
}
