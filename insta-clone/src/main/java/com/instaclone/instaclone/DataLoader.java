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
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l2 = Location.builder()
                .locationName("Novi Sad")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l3 = Location.builder()
                .locationName("Novi Sad")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l4 = Location.builder()
                .locationName("Novi Sad")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l5 = Location.builder()
                .locationName("Novi Sad")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l6 = Location.builder()
                .locationName("Novi Sad")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l7 = Location.builder()
                .locationName("Novi Sad")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l8 = Location.builder()
                .locationName("Novi Sad")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl1 = Location.builder()
                .locationName("Kraljevica Marka 28b")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl2 = Location.builder()
                .locationName("Vitezovi Salajke")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl3 = Location.builder()
                .locationName("Vjeternik")
                .name("Liman Drzava")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        l1.setTimeCreated();
        l2.setTimeCreated();
        l3.setTimeCreated();
        l4.setTimeCreated();
        l5.setTimeCreated();
        l6.setTimeCreated();
        l7.setTimeCreated();
        l8.setTimeCreated();

        postl1.setTimeCreated();
        postl2.setTimeCreated();
        postl3.setTimeCreated();
        locationRepository.saveAll(List.of(l1, l2, l3, l4, l5, l6, l7, l8, postl1, postl2, postl3));


        //---------------
        Categorization pc1 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(1.0 / 16.0, 9.0 / 16.0, 20.0 / 16.0, 30.0 / 16.0, 40.0 / 16.0, 33.0 / 16.0, 17.0 / 16.0,
                        11.0 / 16.0, 13.0 / 16.0, 1.0, 20.0 / 16.0, 18.0 / 16.0, 8.0 / 16.0, 10.0 / 16.0, 5.0 / 16.0, 5.0 / 16.0)) //sum je 256/16
                .build();
        Categorization fc1 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(9.0 / 16.0, 20.0 / 16.0, 30.0 / 16.0, 40.0 / 16.0, 33.0 / 16.0, 17.0 / 16.0, 1.0 / 16,
                        8.0 / 16.0, 10.0 / 16.0, 5.0 / 16.0, 5.0 / 16.0, 11.0 / 16.0, 13.0 / 16.0, 1.0, 20.0 / 16.0, 18.0 / 16.0)) //sum 256/16
                .build();

        Categorization pc2 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(9.0 / 16.0, 20.0 / 16.0, 30.0 / 16.0, 40.0 / 16.0, 33.0 / 16.0, 17.0 / 16.0, 1.0 / 16,
                        8.0 / 16.0, 10.0 / 16.0, 5.0 / 16.0, 5.0 / 16.0, 11.0 / 16.0, 13.0 / 16.0, 1.0, 20.0 / 16.0, 18.0 / 16.0)) //sum 256/16
                .build();
        Categorization fc2 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(1.0 / 16.0, 9.0 / 16.0, 20.0 / 16.0, 30.0 / 16.0, 40.0 / 16.0, 33.0 / 16.0, 17.0 / 16.0,
                        11.0 / 16.0, 13.0 / 16.0, 1.0, 20.0 / 16.0, 18.0 / 16.0, 8.0 / 16.0, 10.0 / 16.0, 5.0 / 16.0, 5.0 / 16.0)) //sum je 256/16
                .build();

        Categorization pc3 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(2.0 / 16.0, 8.0 / 16.0, 21.0 / 16.0, 29.0 / 16.0, 41.0 / 16.0, 32.0 / 16.0, 18.0 / 16.0,
                        10.0 / 16.0, 14.0 / 16.0, 17.0 / 16, 17.0 / 16.0, 19.0 / 16.0, 9.0 / 16.0, 9.0 / 16.0, 6.0 / 16.0, 4.0 / 16.0)) //sum je 256/16 ova dva su isti
                .build();
        Categorization fc3 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(2.0 / 16.0, 8.0 / 16.0, 21.0 / 16.0, 29.0 / 16.0, 41.0 / 16.0, 32.0 / 16.0, 18.0 / 16.0,
                        10.0 / 16.0, 14.0 / 16.0, 17.0 / 16, 17.0 / 16.0, 19.0 / 16.0, 9.0 / 16.0, 9.0 / 16.0, 6.0 / 16.0, 4.0 / 16.0)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc4 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 14.5, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc4 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(2.0 / 16.0, 8.0 / 16.0, 21.0 / 16.0, 29.0 / 16.0, 41.0 / 16.0, 32.0 / 16.0, 18.0 / 16.0,
                        10.0 / 16.0, 14.0 / 16.0, 17.0 / 16, 17.0 / 16.0, 19.0 / 16.0, 9.0 / 16.0, 9.0 / 16.0, 6.0 / 16.0, 4.0 / 16.0)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc5 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(1.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 13.5, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc5 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(2.0 / 16.0, 8.0 / 16.0, 21.0 / 16.0, 29.0 / 16.0, 41.0 / 16.0, 32.0 / 16.0, 18.0 / 16.0,
                        10.0 / 16.0, 14.0 / 16.0, 17.0 / 16, 17.0 / 16.0, 19.0 / 16.0, 9.0 / 16.0, 9.0 / 16.0, 6.0 / 16.0, 4.0 / 16.0)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc6 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 14.5, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc6 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(2.0 / 16.0, 8.0 / 16.0, 21.0 / 16.0, 29.0 / 16.0, 41.0 / 16.0, 32.0 / 16.0, 18.0 / 16.0,
                        10.0 / 16.0, 14.0 / 16.0, 17.0 / 16, 17.0 / 16.0, 19.0 / 16.0, 9.0 / 16.0, 9.0 / 16.0, 6.0 / 16.0, 4.0 / 16.0)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc7 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(13.5, 0.1, 0.1, 0.1, 0.1, 0.1, 1.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc7 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(2.0 / 16.0, 8.0 / 16.0, 21.0 / 16.0, 29.0 / 16.0, 41.0 / 16.0, 32.0 / 16.0, 18.0 / 16.0,
                        10.0 / 16.0, 14.0 / 16.0, 17.0 / 16, 17.0 / 16.0, 19.0 / 16.0, 9.0 / 16.0, 9.0 / 16.0, 6.0 / 16.0, 4.0 / 16.0)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc8 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(0.1, 0.1, 0.1, 14., 0.2, 0.2, 0.2, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc8 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(2.0 / 16.0, 8.0 / 16.0, 21.0 / 16.0, 29.0 / 16.0, 41.0 / 16.0, 32.0 / 16.0, 18.0 / 16.0,
                        10.0 / 16.0, 14.0 / 16.0, 17.0 / 16, 17.0 / 16.0, 19.0 / 16.0, 9.0 / 16.0, 9.0 / 16.0, 6.0 / 16.0, 4.0 / 16.0)) //sum je 256/16 ova dva su isti
                .build();

        pc1.setTimeCreated();
        pc2.setTimeCreated();
        pc3.setTimeCreated();
        pc4.setTimeCreated();
        pc5.setTimeCreated();
        pc6.setTimeCreated();
        pc7.setTimeCreated();
        pc8.setTimeCreated();

        fc1.setTimeCreated();
        fc2.setTimeCreated();
        fc3.setTimeCreated();
        fc4.setTimeCreated();
        fc5.setTimeCreated();
        fc6.setTimeCreated();
        fc7.setTimeCreated();
        fc8.setTimeCreated();

        categorizationRepository.saveAll(List.of(pc1, fc1, pc2, fc2, pc3, fc3, pc4, fc4, pc5, fc5, pc6, fc6, pc7, fc7, pc8, fc8));


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

        User u4 = User.builder()
                .username("galeb_p")
                .email("regular4@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        User u5 = User.builder()
                .username("pjer_antonovic")
                .email("regular5@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        User u6 = User.builder()
                .username("samo_smeh123")
                .email("regular6@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        User u7 = User.builder()
                .username("andjela_sumic")
                .email("regular7@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        User u8 = User.builder()
                .username("milorad_m")
                .email("regular8@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        u1.setTimeCreated(LocalDateTime.now());
        u2.setTimeCreated(LocalDateTime.now());
        u3.setTimeCreated(LocalDateTime.now());
        u4.setTimeCreated();
        u5.setTimeCreated();
        u6.setTimeCreated();
        u7.setTimeCreated();
        u8.setTimeCreated();


        Profile profile1 = Profile.builder()
                .user(u1)
                .bio("Ja sam veoma uspjesan")
                .name("Luka Lule")
                .profilePicture("/static/users/1.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(2013, 9, 22))
                .ageCategory(AgeCategory.CHILD)
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
                .birthday(LocalDate.of(2007, 9, 21))
                .ageCategory(AgeCategory.TEENAGER)
                .gender(Gender.FEMALE)
                .location(l3)
                .followCategorization(fc3)
                .postCategorization(pc3)
                .build();
        profile3.setTimeCreated(LocalDateTime.now());

        Profile profile4 = Profile.builder()
                .user(u4)
                .bio("National geographic reporter")
                .name("Galeb Paunovic")
                .profilePicture("/static/users/4.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1990, 9, 21))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.MALE)
                .location(l4)
                .followCategorization(fc4)
                .postCategorization(pc4)
                .build();
        profile4.setTimeCreated(LocalDateTime.now());

        Profile profile5 = Profile.builder()
                .user(u5)
                .bio("World famous designer")
                .name("Predrag Pjer Antonovic")
                .profilePicture("/static/users/5.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1960, 9, 21))
                .ageCategory(AgeCategory.ELDER)
                .gender(Gender.MALE)
                .location(l5)
                .followCategorization(fc5)
                .postCategorization(pc5)
                .build();
        profile5.setTimeCreated(LocalDateTime.now());

        Profile profile6 = Profile.builder()
                .user(u6)
                .bio("Samo zabava i jos malo smeha")
                .name("Deda Konj")
                .profilePicture("/static/users/6.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1990, 9, 21))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.MALE)
                .location(l6)
                .followCategorization(fc6)
                .postCategorization(pc6)
                .build();
        profile6.setTimeCreated(LocalDateTime.now());

        Profile profile7 = Profile.builder()
                .user(u7)
                .bio("Burn down the cities and take deep breath")
                .name("Andjela Andji Sumic")
                .profilePicture("/static/users/7.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1990, 9, 21))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.FEMALE)
                .location(l7)
                .followCategorization(fc7)
                .postCategorization(pc7)
                .build();
        profile7.setTimeCreated(LocalDateTime.now());

        Profile profile8 = Profile.builder()
                .user(u8)
                .bio("Prosecan SIIT-ovac.py")
                .name("Milorad Javac")
                .profilePicture("/static/users/8.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1990, 9, 21))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.MALE)
                .location(l8)
                .followCategorization(fc8)
                .postCategorization(pc8)
                .build();
        profile8.setTimeCreated(LocalDateTime.now());


        //keca prate 2 i3, on prati samo 2
        profile1.getFollowers().addAll(List.of(profile2, profile3));
        profile1.getFollowing().add(profile2);

        //prati keca, kec njega
        profile2.getFollowers().addAll(List.of(profile1));
        profile2.getFollowing().add(profile1);

        //prati keca, njega niko
        profile3.getFollowers().addAll(List.of(profile1));


        userRepository.saveAll(List.of(u1, u2, u3, u4, u5, u6, u7, u8));
        profileRepository.saveAll(List.of(profile1, profile2, profile3, profile4, profile5, profile6, profile7, profile8));

        u1 = userRepository.findByUsername(u1.getUsername());
        u2 = userRepository.findByUsername(u2.getUsername());
        u3 = userRepository.findByUsername(u3.getUsername());
        u4 = userRepository.findByUsername(u4.getUsername());
        u5 = userRepository.findByUsername(u5.getUsername());
        u6 = userRepository.findByUsername(u6.getUsername());
        u7 = userRepository.findByUsername(u7.getUsername());
        u8 = userRepository.findByUsername(u8.getUsername());


        u1.setProfile(profile1);
        u2.setProfile(profile2);
        u3.setProfile(profile3);
        u4.setProfile(profile4);
        u5.setProfile(profile5);
        u6.setProfile(profile6);
        u7.setProfile(profile7);
        u8.setProfile(profile8);
        userRepository.saveAll(List.of(u1, u2, u3, u4, u5, u6, u7, u8));


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
                .numOfShares(9999)
                .build();

        Post p2 = Post.builder()
                .publisher(profile3)
                .picture("/static/posts/2.jpg")
                .text("Post 2 neki text")
                .reactions(List.of(reakc21, reakc22))
                .location(postl2)
                .categories(List.of(Category.ANIMALS, Category.NATURE, Category.HEALTH_BEAUTY, Category.SELFIE))
                .numOfShares(9999)
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
                .numOfShares(5000)
                .build();
        p3.setTimeCreated(LocalDateTime.now());

        Post p4 = Post.builder()
                .publisher(profile1)
                .picture("/static/posts/4.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .categories(List.of(Category.ANIMALS, Category.NATURE))
                .numOfShares(5692)
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

        Post p12 = Post.builder()
                .publisher(profile4)
                .picture("/static/posts/12.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .categories(List.of(Category.ANIMALS, Category.NATURE))
                .build();
        p12.setTimeCreated(LocalDateTime.now());

        Post p13 = Post.builder()
                .publisher(profile4)
                .picture("/static/posts/13.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .categories(List.of(Category.ANIMALS))
                .build();
        p13.setTimeCreated(LocalDateTime.now());

        Post p14 = Post.builder()
                .publisher(profile4)
                .picture("/static/posts/14.jpg")
                .text("Post 1 neki text")
                .reactions(List.of())
                .categories(List.of(Category.ANIMALS))
                .build();
        p14.setTimeCreated(LocalDateTime.now());

        Post p15 = Post.builder()
                .publisher(profile4)
                .picture("/static/posts/15.jpg")
                .text("Post 1 neki text")
                .categories(List.of(Category.ANIMALS))
                .reactions(List.of())
                .build();
        p15.setTimeCreated(LocalDateTime.now());

        Post p16 = Post.builder()
                .publisher(profile4)
                .picture("/static/posts/16.jpg")
                .categories(List.of(Category.ANIMALS))
                .text("Post 1 neki text")
                .reactions(List.of())
                .build();
        p16.setTimeCreated(LocalDateTime.now());

        Post p17 = Post.builder()
                .publisher(profile5)
                .picture("/static/posts/17.jpg")
                .categories(List.of(Category.ART))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p17.setTimeCreated(LocalDateTime.now());

        Post p18 = Post.builder()
                .publisher(profile5)
                .picture("/static/posts/18.jpg")
                .categories(List.of(Category.ART))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p18.setTimeCreated(LocalDateTime.now());

        Post p19 = Post.builder()
                .publisher(profile5)
                .picture("/static/posts/19.jpg")
                .categories(List.of(Category.ART))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p19.setTimeCreated(LocalDateTime.now());

        Post p20 = Post.builder()
                .publisher(profile5)
                .picture("/static/posts/20.jpg")
                .categories(List.of(Category.ART))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p20.setTimeCreated(LocalDateTime.now());

        Post p21 = Post.builder()
                .publisher(profile5)
                .picture("/static/posts/21.jpg")
                .categories(List.of(Category.ART))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p21.setTimeCreated(LocalDateTime.now());

        Post p22 = Post.builder()
                .publisher(profile6)
                .picture("/static/posts/22.jpg")
                .categories(List.of(Category.MEME))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p22.setTimeCreated(LocalDateTime.now());

        Post p23 = Post.builder()
                .publisher(profile6)
                .picture("/static/posts/23.jpg")
                .categories(List.of(Category.MEME))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p23.setTimeCreated(LocalDateTime.now());

        Post p24 = Post.builder()
                .publisher(profile6)
                .picture("/static/posts/24.jpg")
                .categories(List.of(Category.MEME))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p24.setTimeCreated(LocalDateTime.now());

        Post p25 = Post.builder()
                .publisher(profile6)
                .picture("/static/posts/25.jpg")
                .categories(List.of(Category.MEME))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p25.setTimeCreated(LocalDateTime.now());

        Post p26 = Post.builder()
                .publisher(profile6)
                .picture("/static/posts/26.jpg")
                .categories(List.of(Category.MEME))
                .text("Neki umetnicki opis")
                .reactions(List.of())
                .build();
        p26.setTimeCreated(LocalDateTime.now());

        Post p27 = Post.builder()
                .publisher(profile7)
                .picture("/static/posts/27.jpg")
                .categories(List.of(Category.NATURE))
                .text("Spasimo sume")
                .reactions(List.of())
                .build();
        p27.setTimeCreated(LocalDateTime.now());

        Post p28 = Post.builder()
                .publisher(profile7)
                .picture("/static/posts/28.jpg")
                .categories(List.of(Category.NATURE))
                .text("Spasimo sume")
                .reactions(List.of())
                .build();
        p28.setTimeCreated(LocalDateTime.now());

        Post p29 = Post.builder()
                .publisher(profile7)
                .picture("/static/posts/29.jpg")
                .categories(List.of(Category.NATURE))
                .text("Spasimo sume")
                .reactions(List.of())
                .build();
        p29.setTimeCreated(LocalDateTime.now());

        Post p30 = Post.builder()
                .publisher(profile7)
                .picture("/static/posts/30.jpg")
                .categories(List.of(Category.NATURE))
                .text("Spasimo sume")
                .reactions(List.of())
                .build();
        p30.setTimeCreated(LocalDateTime.now());

        Post p31 = Post.builder()
                .publisher(profile7)
                .picture("/static/posts/31.jpg")
                .categories(List.of(Category.NATURE))
                .text("Spasimo sume")
                .reactions(List.of())
                .build();
        p31.setTimeCreated(LocalDateTime.now());

        Post p32 = Post.builder()
                .publisher(profile8)
                .picture("/static/posts/32.jpg")
                .categories(List.of(Category.TECHNOLOGY))
                .text("Ja volim skolu")
                .reactions(List.of())
                .build();
        p32.setTimeCreated(LocalDateTime.now());

        Post p33 = Post.builder()
                .publisher(profile8)
                .picture("/static/posts/33.jpg")
                .categories(List.of(Category.TECHNOLOGY))
                .text("Ja volim skolu")
                .reactions(List.of())
                .build();
        p33.setTimeCreated(LocalDateTime.now());

        Post p34 = Post.builder()
                .publisher(profile8)
                .picture("/static/posts/34.jpg")
                .categories(List.of(Category.TECHNOLOGY))
                .text("Ja volim skolu")
                .reactions(List.of())
                .build();
        p34.setTimeCreated(LocalDateTime.now());

        Post p35 = Post.builder()
                .publisher(profile8)
                .picture("/static/posts/35.jpg")
                .categories(List.of(Category.TECHNOLOGY))
                .text("Ja volim skolu")
                .reactions(List.of())
                .build();
        p35.setTimeCreated(LocalDateTime.now());

        Post p36 = Post.builder()
                .publisher(profile8)
                .picture("/static/posts/36.jpg")
                .categories(List.of(Category.TECHNOLOGY))
                .text("Ja volim skolu")
                .reactions(List.of())
                .build();
        p36.setTimeCreated(LocalDateTime.now());


        postRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36));
    }
}
