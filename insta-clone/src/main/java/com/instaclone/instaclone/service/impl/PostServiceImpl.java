package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.converter.post.PostToPostDto;
import com.instaclone.instaclone.dto.post.NewPostDto;
import com.instaclone.instaclone.dto.post.PostDto;
import com.instaclone.instaclone.dto.post.UpdatePostDto;
import com.instaclone.instaclone.exception.NotFoundException;
import com.instaclone.instaclone.exception.OperationNotAllowedException;
import com.instaclone.instaclone.model.Location;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.model.facts.PostPublished;
import com.instaclone.instaclone.repository.PostRepository;
import com.instaclone.instaclone.service.*;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PostServiceImpl extends JPAServiceImpl<Post> implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final PostToPostDto postToPostDtoConverter;
    private final LocationService locationService;
    private final KieContainer kieContainer;
    private final CategorizationService categorizationService;

    @PostConstruct
    public void init() {
        postToPostDtoConverter.setPostService(this);
    }

    @Override
    protected JpaRepository<Post, Long> getEntityRepository() {
        return postRepository;
    }

    @Override
    public PostDto publishPost(NewPostDto dto, String username) {
        User user = userService.findByUsername(username);

        Post newPost = Post.builder()
                .text(dto.getText())
                .publisher(user.getProfile())
                .categories(dto.getCategories())
                .numOfShares(0)
                .viral(false)
                .build();
        newPost.setTimeCreated(LocalDateTime.now());

        if(dto.getLocation() != null)
        {
            Location newLocation = new Location();
            newLocation.setTimeCreated();
            newLocation.setLocationName(dto.getLocation().getLocationName());
            newLocation.setLongitude(dto.getLocation().getLongitude());
            newLocation.setLatitude(dto.getLocation().getLatitude());
            newLocation.setState(dto.getLocation().getState());
            newLocation.setRegion(dto.getLocation().getRegion());

            locationService.save(newLocation);
            newPost.setLocation(newLocation);
        }

        PostPublished postPublished = new PostPublished(user.getProfile(), dto.getCategories());

        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.getAgenda().getAgendaGroup( "post-published" ).setFocus();
        kieSession.insert(postPublished);
        kieSession.fireAllRules();
        kieSession.dispose();

        categorizationService.save(user.getProfile().getPostCategorization());

        if (dto.getPicture().equals("")) {
            newPost.setPicture("/static/posts/default.jpg");
            return postToPostDtoConverter.convert(save(newPost));
        }

        save(newPost);

        newPost.setPicture(imageService.uploadImage(dto.getPicture(), newPost.getId(), "posts"));
        return postToPostDtoConverter.convert(save(newPost));

    }

    @Override
    public void deletePost(String username, Long postId) {

        User user = userService.findByUsername(username);
        Post p = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post Not found!"));
        if (!p.getPublisher().getId().equals(user.getId())) throw new NotFoundException("Post Not found!");

        delete(postId);
    }

    @Override
    public PostDto updatePost(UpdatePostDto updatePostDto, String username) {

        Post p = postRepository.findById(updatePostDto.getPostToUpdateId())
                .orElseThrow(() -> new NotFoundException("Post Not found!"));

        if (checkIfMyPost(username, p)) {
            p.setPicture(updatePostDto.getPicture());
            p.setText(updatePostDto.getText());
            return postToPostDtoConverter.convert(save(p));
        }

        throw new OperationNotAllowedException("Post update not allowed.");

    }

    @Override
    public PostDto getOnePost(Long id) {
        Post p = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post Not found!"));
        return postToPostDtoConverter.convert(p);
    }

    @Override
    public Page<PostDto> getFeed(String username, int page, int size) {
        User user = userService.findByUsername(username);

        page = Math.max(page, 0);
        size = Math.max(size, 1);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "timeCreated");

        Set<Profile> getFeedFrom = user.getProfile().getFollowing();
        getFeedFrom.add(user.getProfile());

        Page<Post> posts = postRepository.getFeed(getFeedFrom, pageable);
        return posts.map(postToPostDtoConverter::convert);
    }

    @Override
    public Page<PostDto> getUserPosts(String username, int page, int size) {
        User user = userService.findByUsername(username);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "timeCreated");
        Page<Post> myPosts = postRepository.getPostByPublisher(user.getProfile(), pageable);
        return myPosts.map(postToPostDtoConverter::convert);
    }

    @Override
    @Transactional
    public int getNumOfReactions(Post post) {
        if (post.getReactions() == null)
            return 0;
        return post.getReactions().size();
    }

    @Override
    public Page<PostDto> getExplore(String userName, int page, int size) {
        /**
         * Trenutno dobavlja sve zivo u sistemu
         * */

        page = Math.max(page, 0);
        size = Math.max(size, 1);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "timeCreated");

        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(postToPostDtoConverter::convert);
    }

    @Override
    public Boolean sharePost(String username, long id) {
        // logika obradjuje sta user sharuje?
        // logika za sharovan post?
        // ...
        Post p = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        if (p.getNumOfShares() == null)
            p.setNumOfShares(1);
        else p.setNumOfShares(p.getNumOfShares() + 1);

        postRepository.save(p);
        return true;
    }

    private boolean checkIfMyPost(String username, Post post) {
        User user = userService.findByUsername(username);
        return post.getPublisher().getUser().getId().equals(user.getId());
    }
}
