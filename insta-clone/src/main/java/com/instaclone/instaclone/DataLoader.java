package com.instaclone.instaclone;

import com.instaclone.instaclone.model.*;
import com.instaclone.instaclone.model.enums.*;
import com.instaclone.instaclone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PostRepository postRepository;
    private final ReactionRepository reactionRepository;
    private final PasswordEncoder encoder;
    private final LocationRepository locationRepository;
    private final CategorizationRepository categorizationRepository;

    @Override
    public void run(String... args) {

        Location l1 = Location.builder()
                .locationName("Novi Sad")
                .state("Liman Drzava")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l2 = Location.builder()
                .locationName("Novi Sad")
                .state("Liman Drzava")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l3 = Location.builder()
                .locationName("Novi Sad")
                .state("Liman Drzava")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl1 = Location.builder()
                .locationName("Kraljevica Marka 28b")
                .state("Liman2 Drzava")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl2 = Location.builder()
                .locationName("Vitezovi Salajke")
                .state("Srbija")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl3 = Location.builder()
                .locationName("Vjeternik")
                .state("Republika Srpska")
                .latitude(0.)
                .longitude(0.)
                .build();
        l1.setTimeCreated();
        l2.setTimeCreated();
        l3.setTimeCreated();
        postl1.setTimeCreated();
        postl2.setTimeCreated();
        postl3.setTimeCreated();
        locationRepository.saveAll(List.of(l1, l2, l3, postl1, postl2, postl3));


        //---------------
        Categorization pc1 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0))
                .build();
        Categorization fc1 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0))
                .build();

        Categorization pc2 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0))
                .build();
        Categorization fc2 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0))
                .build();

        Categorization pc3 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0))
                .build();
        Categorization fc3 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0))
                .build();
        pc1.setTimeCreated();
        pc2.setTimeCreated();
        pc3.setTimeCreated();
        fc1.setTimeCreated();
        fc2.setTimeCreated();
        fc3.setTimeCreated();


        categorizationRepository.saveAll(List.of(pc1, fc1, pc2, fc2, pc3, fc3));


        //---------------


        User u1 = User.builder()
                .username("regularUsername1")
                .email("regular1@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        User u2 = User.builder()
                .username("regularUsername2")
                .email("regular2@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        User u3 = User.builder()
                .username("regularUsername3")
                .email("regular3@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        u1.setTimeCreated(LocalDateTime.now());
        u2.setTimeCreated(LocalDateTime.now());
        u3.setTimeCreated(LocalDateTime.now());


        Profile profile1 = Profile.builder()
                .user(u1)
                .bio("Ja sam veoma uspjesan")
                .name("Luka Lule")
                .profilePicture("/static/users/1.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1999, 9, 22))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.MALE)
                .location(l1)
                .followCategorization(fc1)
                .postCategorization(pc1)
                .build();

        profile1.setTimeCreated(LocalDateTime.now());

        Profile profile2 = Profile.builder()
                .user(u2)
                .bio("Moj moto je rad, rad rad, stv njegose kakvi su ovo opisi... :(")
                .name("Mare Markovic")
                .profilePicture("/static/users/2.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1999, 2, 22))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.MALE)
                .location(l2)
                .followCategorization(fc2)
                .postCategorization(pc2)
                .build();
        profile2.setTimeCreated(LocalDateTime.now());


        Profile profile3 = Profile.builder()
                .user(u3)
                .bio("Volim zivotinje, btw jako glup bio, al ajde...")
                .name("Ljubica Ljubic")
                .profilePicture("/static/users/3.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1999, 9, 21))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.FEMALE)
                .location(l3)
                .followCategorization(fc3)
                .postCategorization(pc3)
                .build();
        profile3.setTimeCreated(LocalDateTime.now());


        //keca prate 2 i3, on prati samo 2
        profile1.getFollowers().addAll(List.of(profile2, profile3));
        profile1.getFollowing().add(profile2);

        //prati keca, kec njega
        profile2.getFollowers().addAll(List.of(profile1));
        profile2.getFollowing().add(profile1);

        //prati keca, njega niko
        profile3.getFollowers().addAll(List.of(profile1));


        userRepository.saveAll(List.of(u1, u2, u3));
        profileRepository.saveAll(List.of(profile1, profile2, profile3));

        u1 = userRepository.findByUsername(u1.getUsername());
        u2 = userRepository.findByUsername(u2.getUsername());
        u3 = userRepository.findByUsername(u3.getUsername());

        u1.setProfile(profile1);
        u2.setProfile(profile2);
        u3.setProfile(profile3);
        userRepository.saveAll(List.of(u1, u2, u3));


        //-------------------------------------------------------------

        Reaction reakc11 = Reaction.builder()
                .reactionType(ReactionType.LAUGH)
                .profile(profile1)
                .build();
        reakc11.setTimeCreated(LocalDateTime.now());

        Reaction reakc12 = Reaction.builder()
                .reactionType(ReactionType.DISLIKE)
                .profile(profile1)
                .build();
        reakc12.setTimeCreated(LocalDateTime.now());

        Reaction reakc21 = Reaction.builder()
                .reactionType(ReactionType.LIKE)
                .profile(profile1)
                .build();
        reakc21.setTimeCreated(LocalDateTime.now());

        Reaction reakc22 = Reaction.builder()
                .reactionType(ReactionType.LIKE)
                .profile(profile2)
                .build();
        reakc22.setTimeCreated(LocalDateTime.now());

        reactionRepository.saveAll(List.of(reakc11, reakc12, reakc21, reakc22));

        Post p1 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/1.jpg")
                .text("Post 1 neki text")
                .reactions(List.of(reakc11, reakc12))
                .location(postl1)
                .categories(List.of(Category.URBAN_LIFE))
                .build();

        Post p2 = Post.builder()
                .publisher(profile2)
                .picture("/static/posts/2.jpg")
                .text("Post 2 neki text")
                .reactions(List.of(reakc21, reakc22))
                .location(postl2)
                .categories(List.of(Category.ANIMALS, Category.NATURE, Category.HEALTH_BEAUTY, Category.SELFIE))
                .build();

        p1.setTimeCreated(LocalDateTime.now());
        p2.setTimeCreated(LocalDateTime.now());

        Post p3 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/3.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .location(postl3)
                .categories(List.of(Category.ANIMALS, Category.NATURE, Category.MEME))
                .build();
        p3.setTimeCreated(LocalDateTime.now());

        Post p4 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/4.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .categories(List.of(Category.ANIMALS, Category.NATURE))
                .build();
        p4.setTimeCreated(LocalDateTime.now());

        Post p5 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/5.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .build();
        p5.setTimeCreated(LocalDateTime.now());

        Post p6 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/6.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .build();
        p6.setTimeCreated(LocalDateTime.now());

        Post p7 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/7.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .build();
        p7.setTimeCreated(LocalDateTime.now());

        Post p8 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/8.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .build();
        p8.setTimeCreated(LocalDateTime.now());

        Post p9 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/9.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .build();
        p9.setTimeCreated(LocalDateTime.now());

        Post p10 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/10.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .build();
        p10.setTimeCreated(LocalDateTime.now());

        Post p11 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/11.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .build();
        p11.setTimeCreated(LocalDateTime.now());

        postRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
    }
}
