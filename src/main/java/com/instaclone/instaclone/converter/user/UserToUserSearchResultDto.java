package com.instaclone.instaclone.converter.user;

import com.instaclone.instaclone.converter.Converter;
import com.instaclone.instaclone.dto.user.UserSearchResultDto;
import com.instaclone.instaclone.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserSearchResultDto extends Converter<User, UserSearchResultDto> {
    @Override
    public UserSearchResultDto convert(User source) {
        UserSearchResultDto resultDto = new UserSearchResultDto();
        resultDto.setUsername(source.getUsername());
        resultDto.setProfilePicture(source.getProfile().getProfilePicture());
        resultDto.setName(source.getProfile().getName());
        return resultDto;
    }
}
