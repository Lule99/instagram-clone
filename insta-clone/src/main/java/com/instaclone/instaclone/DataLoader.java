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
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l2 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l3 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l4 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l5 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l6 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l7 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l8 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l9 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location l10 = Location.builder()
                .locationName("Novi Sad")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl1 = Location.builder()
                .locationName("Kraljevica Marka 28b")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl2 = Location.builder()
                .locationName("Vitezovi Salajke")
                .name("Serbia")
                .parent("")
                .latitude(0.)
                .longitude(0.)
                .build();

        Location postl3 = Location.builder()
                .locationName("Vjeternik")
                .name("Serbia")
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
        l9.setTimeCreated();
        l10.setTimeCreated();

        postl1.setTimeCreated();
        postl2.setTimeCreated();
        postl3.setTimeCreated();
        locationRepository.saveAll(List.of(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, postl1, postl2, postl3));


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
                .categories(List.of(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 14.5, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc5 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(1.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 13.5, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc5 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(1.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 13.5, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc6 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 14.5, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc6 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 14.5, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc7 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(13.5, 0.1, 0.1, 0.1, 0.1, 0.1, 1.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc7 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(13.5, 0.1, 0.1, 0.1, 0.1, 0.1, 1.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc8 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(0.1, 0.1, 0.1, 14., 0.2, 0.2, 0.2, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc8 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(0.1, 0.1, 0.1, 14., 0.2, 0.2, 0.2, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc9 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(0.1, 0.1, 0.1, 0.2, 14., 0.2, 0.2, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc9 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(0.1, 0.1, 0.1, 0.2, 14., 0.2, 0.2, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization pc10 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(0.1, 0.1, 14., 0.2, 0.1, 0.2, 0.2, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        Categorization fc10 = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(0.1, 0.1, 14., 0.2, 0.1, 0.2, 0.2, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1)) //sum je 256/16 ova dva su isti
                .build();

        pc1.setTimeCreated();
        pc2.setTimeCreated();
        pc3.setTimeCreated();
        pc4.setTimeCreated();
        pc5.setTimeCreated();
        pc6.setTimeCreated();
        pc7.setTimeCreated();
        pc8.setTimeCreated();
        pc9.setTimeCreated();
        pc10.setTimeCreated();

        fc1.setTimeCreated();
        fc2.setTimeCreated();
        fc3.setTimeCreated();
        fc4.setTimeCreated();
        fc5.setTimeCreated();
        fc6.setTimeCreated();
        fc7.setTimeCreated();
        fc8.setTimeCreated();
        fc9.setTimeCreated();
        fc10.setTimeCreated();

        categorizationRepository.saveAll(List.of(pc1, fc1, pc2, fc2, pc3, fc3, pc4, fc4, pc5, fc5, pc6, fc6, pc7, fc7,
                pc8, fc8, pc9, fc9, pc10, fc10));


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

        User u9 = User.builder()
                .username("em_rata")
                .email("em_rata@email.com")
                .password(encoder.encode("123"))
                .role(Role.REGULAR_USER)
                .build();

        User u10 = User.builder()
                .username("lule")
                .email("lule@email.com")
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
        u9.setTimeCreated();
        u10.setTimeCreated();


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

        Profile profile9 = Profile.builder()
                .user(u9)
                .bio("Emily ;)")
                .name("Emily")
                .profilePicture("/static/users/9.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1990, 9, 21))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.FEMALE)
                .location(l9)
                .followCategorization(fc9)
                .postCategorization(pc9)
                .build();
        profile9.setTimeCreated(LocalDateTime.now());

        Profile profile10 = Profile.builder()
                .user(u10)
                .bio("Lule ovim putem apeluje na dobru volju prilikom ocenjivanja :D")
                .name("Njegos me zlostavlja")
                .profilePicture("/static/users/10.jpg")
                .followers(new HashSet<>())
                .following(new HashSet<>())
                .viral(false)
                .birthday(LocalDate.of(1999, 9, 22))
                .ageCategory(AgeCategory.ADULT)
                .gender(Gender.MALE)
                .location(l10)
                .followCategorization(fc10)
                .postCategorization(pc10)
                .build();
        profile10.setTimeCreated(LocalDateTime.now());

        //keca prate 2 i3, on prati samo 2
        profile1.getFollowers().addAll(List.of(profile2, profile3));
        profile1.getFollowing().addAll(List.of(profile2, profile3, profile10));

        //prati keca, kec njega
        profile2.getFollowers().addAll(List.of(profile1));
        profile2.getFollowing().addAll(List.of(profile1, profile10));

        //prati ga kec
        profile3.getFollowers().addAll(List.of(profile1));
        profile3.getFollowing().addAll(List.of(profile1, profile10));

        profile4.getFollowing().addAll(List.of(profile10));
        profile5.getFollowing().addAll(List.of(profile10));
        profile6.getFollowing().addAll(List.of(profile10));
        profile9.getFollowing().addAll(List.of(profile10));

        profile10.getFollowers().addAll(List.of(profile1, profile2, profile3, profile4, profile5, profile6, profile9));


        userRepository.saveAll(List.of(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10));
        profileRepository.saveAll(List.of(profile1, profile2, profile3, profile4, profile5, profile6, profile7, profile8, profile9, profile10));

        u1 = userRepository.findByUsername(u1.getUsername());
        u2 = userRepository.findByUsername(u2.getUsername());
        u3 = userRepository.findByUsername(u3.getUsername());
        u4 = userRepository.findByUsername(u4.getUsername());
        u5 = userRepository.findByUsername(u5.getUsername());
        u6 = userRepository.findByUsername(u6.getUsername());
        u7 = userRepository.findByUsername(u7.getUsername());
        u8 = userRepository.findByUsername(u8.getUsername());
        u9 = userRepository.findByUsername(u9.getUsername());
        u10 = userRepository.findByUsername(u10.getUsername());


        u1.setProfile(profile1);
        u2.setProfile(profile2);
        u3.setProfile(profile3);
        u4.setProfile(profile4);
        u5.setProfile(profile5);
        u6.setProfile(profile6);
        u7.setProfile(profile7);
        u8.setProfile(profile8);
        u9.setProfile(profile9);
        u10.setProfile(profile10);
        userRepository.saveAll(List.of(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10));


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

        Post p37 = Post.builder()
                .publisher(profile9)
                .picture("/static/posts/37.jpg")
                .categories(List.of(Category.FASHION))
                .text("Da ima i malo zenskih osoba na ig :D")
                .reactions(List.of())
                .build();
        p37.setTimeCreated(LocalDateTime.now());

        Post p38 = Post.builder()
                .publisher(profile9)
                .picture("/static/posts/38.jpg")
                .categories(List.of(Category.FASHION))
                .text("Nije sve mali beli ker")
                .reactions(List.of())
                .build();
        p38.setTimeCreated(LocalDateTime.now());

        Post p39 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/39.jpg")
                .categories(List.of(Category.TRAVEL))
                .text("Afrika ;)")
                .reactions(List.of())
                .build();
        p39.setTimeCreated(LocalDateTime.now());

        Post p40 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/40.jpg")
                .categories(List.of(Category.TRAVEL, Category.HISTORY_RELIGION))
                .text("Tutankamon")
                .reactions(List.of())
                .build();
        p40.setTimeCreated(LocalDateTime.now());

        Post p41 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/41.jpg")
                .categories(List.of(Category.TRAVEL))
                .text("Bolje na slici nego uzivo")
                .reactions(List.of())
                .build();
        p41.setTimeCreated(LocalDateTime.now());

        Post p42 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/42.jpg")
                .categories(List.of(Category.TRAVEL, Category.OTHER))
                .text("Da je riba valjala, vuk bi dunavom vladao")
                .reactions(List.of())
                .build();
        p42.setTimeCreated(LocalDateTime.now());

        Post p43 = Post.builder()
                .publisher(profile9)
                .picture("/static/posts/43.jpg")
                .categories(List.of(Category.FASHION))
                .text("ratatata")
                .reactions(List.of())
                .build();
        p43.setTimeCreated(LocalDateTime.now());

        Post p44 = Post.builder()
                .publisher(profile9)
                .picture("/static/posts/44.jpg")
                .categories(List.of(Category.FASHION, Category.URBAN_LIFE, Category.CLUBBING))
                .text("slomo :D")
                .reactions(List.of())
                .build();
        p44.setTimeCreated(LocalDateTime.now());

        Post p45 = Post.builder()
                .publisher(profile9)
                .picture("/static/posts/45.jpg")
                .categories(List.of(Category.FASHION, Category.URBAN_LIFE, Category.CLUBBING))
                .text("Komprosta za bolju cirkulaciju")
                .reactions(List.of())
                .build();
        p45.setTimeCreated(LocalDateTime.now());
//-----
        Post p46 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/46.jpg")
                .categories(List.of(Category.TRAVEL, Category.NATURE))
                .text("Pusku pa u frusku")
                .reactions(List.of())
                .build();
        p46.setTimeCreated(LocalDateTime.now());

        Post p47 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/47.jpg")
                .categories(List.of(Category.TRAVEL, Category.NATURE))
                .text("Majdan")
                .reactions(List.of())
                .build();
        p47.setTimeCreated(LocalDateTime.now());

        Post p48 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/48.jpg")
                .categories(List.of(Category.TRAVEL, Category.SELFIE))
                .text("Jahta")
                .reactions(List.of())
                .build();
        p48.setTimeCreated(LocalDateTime.now());

        Post p49 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/49.jpg")
                .categories(List.of(Category.TRAVEL, Category.URBAN_LIFE))
                .text("motivacioni govor here: _____")
                .reactions(List.of())
                .build();
        p49.setTimeCreated(LocalDateTime.now());

        Post p50 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/50.jpg")
                .categories(List.of(Category.TRAVEL, Category.URBAN_LIFE, Category.SELFIE))
                .text("U belgiji vise nema belaca")
                .reactions(List.of())
                .build();
        p50.setTimeCreated(LocalDateTime.now());

        Post p51 = Post.builder()
                .publisher(profile10)
                .picture("/static/posts/51.jpg")
                .categories(List.of(Category.TRAVEL, Category.URBAN_LIFE))
                .text("Neki kao duzi opis da eto postoji u svim ovim postovima.....")
                .reactions(List.of())
                .build();
        p51.setTimeCreated(LocalDateTime.now());


        postRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18,
                p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39,
                p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50, p51));
    }
}
