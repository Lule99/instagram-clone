package com.instaclone.instaclone.converter.post;

import com.instaclone.instaclone.converter.Converter;
import com.instaclone.instaclone.dto.post.PostDto;
import com.instaclone.instaclone.exception.NotFoundException;
import com.instaclone.instaclone.model.Location;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.model.enums.Category;
import com.instaclone.instaclone.service.PostService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PostToPostDto extends Converter<Post, PostDto> {


    private PostService postService;

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Override
    @Transactional
    public PostDto convert(Post source) {
        User user = source.getPublisher().getUser();
        if (user == null) throw new NotFoundException("User Not Found");

        PostDto dto = PostDto.builder()
                .username(user.getUsername())
                .userProfilePicture(source.getPublisher().getProfilePicture())
                .id(source.getId())
                .dateTime(source.getTimeCreated())
                .picture(source.getPicture())
                .text(source.getText())
                .categories(source.getCategories())
                .time(source.getTimeCreated())
                .viral(source.getViral() != null && source.getViral())
                .build();
        Location location = source.getLocation();
        if (location != null)
            dto.setLocation(location.getLocationName());

        if (source.getNumOfShares() == null)
            dto.setNumOfShares(0);
        else dto.setNumOfShares(source.getNumOfShares());

        dto.setNumOfReactions(postService.getNumOfReactions(source));


        return dto;
    }


}
