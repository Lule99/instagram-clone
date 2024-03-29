package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.converter.post.PostToPostDto;
import com.instaclone.instaclone.dto.post.NewPostDto;
import com.instaclone.instaclone.dto.post.PostDto;
import com.instaclone.instaclone.dto.post.UpdatePostDto;
import com.instaclone.instaclone.events.ChangeCompleteParametersEvent;
import com.instaclone.instaclone.events.LongScrollingEvent;
import com.instaclone.instaclone.events.ReloadEvent;
import com.instaclone.instaclone.events.UpdateParametersEvent;
import com.instaclone.instaclone.exception.NoCategorizationException;
import com.instaclone.instaclone.exception.NotFoundException;
import com.instaclone.instaclone.exception.OperationNotAllowedException;
import com.instaclone.instaclone.model.*;
import com.instaclone.instaclone.model.facts.*;
import com.instaclone.instaclone.repository.PostRepository;
import com.instaclone.instaclone.service.*;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequiredArgsConstructor
@Service
public class PostServiceImpl extends JPAServiceImpl<Post> implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final PostToPostDto postToPostDtoConverter;
    private final LocationService locationService;
    private final CategorizationService categorizationService;
    private final KieContainer kieContainer;


    @Autowired
    @Qualifier(value = "cepSession")
    private KieSession cepKieSession;


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

        if (dto.getLocation() != null) {
            Location newLocation = locationService.preprocessLocation(dto.getLocation());
            newPost.setLocation(newLocation);
        }

        PostPublished postPublished = new PostPublished(user.getProfile(), dto.getCategories());
        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.getAgenda().getAgendaGroup("post-published").setFocus();
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

        List<PostDto> explore = calculateExplore(userService.findByUsername(userName).getProfile())
                .stream()
                .map(postToPostDtoConverter::convert)
                .collect(Collectors.toList());

        page = Math.max(page, 0);
        size = Math.max(size, 1);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "timeCreated");
        if (!explore.isEmpty()) {
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), explore.size());
            return new PageImpl<>(explore.subList(start, end), pageable, explore.size());
        }
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

    @Override
    public List<Post> getViralPostsBefore(LocalDateTime beforeDate) {
        return postRepository.getAllByViralAndTimeCreatedBefore(true, beforeDate);
    }

    @Override
    public List<Post> getPostsAfter(LocalDateTime afterDate) {
        LocalDateTime weekBefore = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        return postRepository.getAllByTimeCreatedAfter(weekBefore);
    }

    @Override
    public Boolean reload(String name) {
        User user = userService.findByUsername(name);
        ReloadEvent re = new ReloadEvent(user.getProfile(), false);
        cepKieSession.insert(re);
        cepKieSession.fireAllRules();

        QueryResults results = cepKieSession.getQueryResults("getUpdateParametersEvent");
        for (QueryResultsRow row : results) {
            UpdateParametersEvent event = (UpdateParametersEvent) row.get("$result");
            Categorization cats = event.getProfile().getFollowCategorization();
            categorizationService.updateParameters(cats);
            FactHandle factHandle = cepKieSession.getFactHandle(event);
            event.setProcessed(true);
            cepKieSession.update(factHandle, event);
        }

        QueryResults results2 = cepKieSession.getQueryResults("getChangeCompleteParametersEvent");
        for (QueryResultsRow row : results2) {
            ChangeCompleteParametersEvent event = (ChangeCompleteParametersEvent) row.get("$result");
            Categorization cats = event.getProfile().getFollowCategorization();
            categorizationService.changeCompleteParameters(cats);
            FactHandle factHandle = cepKieSession.getFactHandle(event);
            event.setProcessed(true);
            cepKieSession.update(factHandle, event);
        }

        return null;
    }

    private boolean checkIfMyPost(String username, Post post) {
        User user = userService.findByUsername(username);
        return post.getPublisher().getUser().getId().equals(user.getId());
    }

    private Set<Post> calculateExplore(Profile profile) {

        calculateViralPosts();

        FinalCategorization finalCategorization = FinalCategorization
                .builder()
                .initilized(false)
                .categorization(List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0))
                .build();
        TimeDifferenceConstantCalculation tdc = new TimeDifferenceConstantCalculation(
                profile.getFollowCategorization().getLastUpdate(),
                profile.getPostCategorization().getLastUpdate());


        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.getAgenda().getAgendaGroup("explore").setFocus();
        kieSession.setGlobal("finalCategorization", finalCategorization);
        kieSession.insert(new ProfileFact(profile));
        kieSession.insert(tdc);
        kieSession.fireAllRules();
        kieSession.dispose();

        if (finalCategorization == null || finalCategorization.getCategorization() == null)
            throw new NoCategorizationException();

        TopCategories topCategories = getTopNCategories(finalCategorization, 4);

        List<Profile> profilesOfInterest = calculateProfilesOfInterest(finalCategorization, profile, topCategories);
        List<Post> similarPosts = postRepository.getTop50ByPublisherInAndTimeCreatedAfterOrderByTimeCreatedDesc(
                profilesOfInterest,
                LocalDateTime.now().minus(30, ChronoUnit.DAYS));
        List<Post> viralPosts = includePostsByViralProfiles(topCategories);

        return Stream.concat(similarPosts.stream(), viralPosts.stream())
                .collect(Collectors.toSet());

    }

    private List<Post> includePostsByViralProfiles(TopCategories topCategories) {

        List<Profile> viralProfiles = userService.getProfilesByViral(true);
        List<Post> viralPosts =
                postRepository.getTop100ByPublisherInAndTimeCreatedAfter(
                        viralProfiles,
                        LocalDateTime.now().minus(7, ChronoUnit.DAYS));
        List<ViralPost> virals = viralPosts.stream().map(ViralPost::new).collect(Collectors.toList());

        ReduceOldVirals reduceOldVirals = new ReduceOldVirals();

        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.getAgenda().getAgendaGroup("viral-posts").setFocus();
        kieSession.setGlobal("reduceOldVirals", reduceOldVirals);
        virals.forEach(kieSession::insert);
        kieSession.insert(topCategories);
        kieSession.fireAllRules();
        kieSession.dispose();

        Set<Long> idsToRemove = new HashSet<>();

        if(! reduceOldVirals.isEmpty()){
            Double max = reduceOldVirals.getMaxReactions();
            ViralPost maxViralPost = reduceOldVirals
                    .getViralPosts()
                    .stream()
                    .filter(vp -> vp.getNumOfReactions() == max)
                    .findFirst()
                    .orElse(null);
            if(maxViralPost != null) {
                double treshold = 0.5 * maxViralPost.getNumOfReactions() + 0.5 * maxViralPost.getNumOfShares();
                idsToRemove = reduceOldVirals
                        .getViralPosts()
                        .stream()
                        .filter(vp -> (vp.getNumOfShares()+vp.getNumOfReactions()) < treshold)
                        .map(ViralPost::getPostId)
                        .collect(Collectors.toSet());
            }
        }

        Set<Long> finalIdsToRemove = idsToRemove;
        Set<Long> viralIds = virals.stream()
                .filter(ViralPost::isTheChosenOne)
                .map(ViralPost::getPostId)
                .filter(id -> ! finalIdsToRemove.contains(id))
                .collect(Collectors.toSet());

        return viralPosts
                .stream()
                .filter(viralIds::contains)
                .collect(Collectors.toList());
    }

    private List<Profile> calculateProfilesOfInterest(FinalCategorization finalCategorization, Profile profile, TopCategories topCategories) {
        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.getAgenda().getAgendaGroup("profilesOfInterest").setFocus();

        ProfileInFocus mainProfile = ProfileInFocus.builder().profile(new ProfileFact(profile)).build();

        List<Profile> nonViralProfiles = userService.getProfilesByViral(false);

        Set<ProfileOfInterest> profiles = nonViralProfiles.stream()
                .map(p -> new ProfileOfInterest(p, finalCategorization.getCategorization()))
                .collect(Collectors.toSet());

        profiles.forEach(kieSession::insert);
        kieSession.insert(finalCategorization);
        kieSession.insert(mainProfile);
        kieSession.insert(topCategories);
        kieSession.fireAllRules();
        kieSession.dispose();

        Set<String> locationPairs = crossCheckLocation();

        Set<Long> idsOfInterest = profiles.stream()
                .filter(ProfileOfInterest::isOfInterest)
                .map(profileOfInterest -> profileOfInterest.getProfile().getId())
                .collect(Collectors.toSet());

        //svi profili koji dele istu lokaciju/ mesto/ okrug...
        Set<Long> additionalInterest = profiles.stream()
                .filter(p -> ! p.isOfInterest() &&
                        locationPairs.contains(p.getProfile().getLocation().getName()+"|"+profile.getLocation().getName()))
                .map(profileOfInterest -> profileOfInterest.getProfile().getId())
                .collect(Collectors.toSet());

        idsOfInterest.addAll(additionalInterest);

        return nonViralProfiles
                .stream()
                .filter(profile1 -> idsOfInterest.contains(profile1.getId()))
                .collect(Collectors.toList());
    }

    private TopCategories getTopNCategories(FinalCategorization categorization, int n) {
        List<Double> allCats = categorization.getCategorization();
        HashMap<Integer, Double> indexValuePair = new HashMap<>();
        for (int i = 0; i < allCats.size(); i++) {
            indexValuePair.put(i, allCats.get(i));
        }
        var sorted = indexValuePair
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        return new TopCategories(
                sorted.subList(Math.max(sorted.size() - n, 0), sorted.size())
                        .stream()
                        .map(Map.Entry::getKey)
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList()));

    }

    public void calculateViralPosts() {
        KieSession kieSession = kieContainer.newKieSession("testSession");

        List<Post> originalPosts = postRepository.findAll();

        List<PostFact> allPostFactss = originalPosts
                .stream()
                .map(PostFact::new)
                .collect(Collectors.toList());

        List<ProfileFact> allProfiles = userService
                .findAll()
                .stream()
                .map(user -> new ProfileFact(user.getProfile()))
                .collect(Collectors.toList());

        allPostFactss.forEach(kieSession::insert);
        allProfiles.forEach(kieSession::insert);

        QueryResults averageFollowers = kieSession.getQueryResults("getAverageFollowers");
        Double average = (Double) averageFollowers.toList().get(0).get("$avg");

        if (average == null || average < 1.0)
            average = 1.0;

        QueryResults res = kieSession.getQueryResults(
                "lastNDaysMoreThanKReactions",
                LocalDateTime.now().minus(7, ChronoUnit.DAYS),
                5000,
                10000);
        var posts = (List<PostFact>) (res.toList().get(0).get("$res"));

        QueryResults res2 = kieSession.getQueryResults(
                "viralByAvgFollowInteractionsRelation",
                LocalDateTime.now().minus(7, ChronoUnit.DAYS),
                average);
        var posts2 = (List<PostFact>) (res2.toList().get(0).get("$res"));

        QueryResults res3 = kieSession.getQueryResults(
                "viralByFollowNumOfInteractionsRelation",
                LocalDateTime.now().minus(7, ChronoUnit.DAYS));
        var posts3 = (List<PostFact>) (res3.toList().get(0).get("$res"));

        Set<Long> newViralIds = Stream
                .concat(Stream.concat(posts.stream(), posts2.stream()), posts3.stream())
                .map(PostFact::getId)
                .collect(Collectors.toSet());

        var result = originalPosts
                .stream()
                .filter(og -> newViralIds.contains(og.getId()))
                .peek(og -> og.setViral(true))
                .collect(Collectors.toList());

        postRepository.saveAll(result);
        kieSession.dispose();
    }

    @Override
    public void checkForEvent(String username, int page) {
        User user = userService.findByUsername(username);
        if (page == 5) {
            cepKieSession.insert(new LongScrollingEvent(user.getProfile(), false));
            cepKieSession.fireAllRules();
        }
    }

    private Set<String> crossCheckLocation() {

        Set<String> locationPairs = new HashSet<>();

        List<Location> locations = locationService.findAll();
        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.getAgenda().getAgendaGroup("backward").setFocus();
        locations.forEach(kieSession::insert);
        kieSession.setGlobal("locationPairs", locationPairs);
        kieSession.insert("runCrossCheck");
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("------------");
        return locationPairs;

    }
}
