package com.instaclone.instaclone.controller;

import com.instaclone.instaclone.dto.post.PostDto;
import com.instaclone.instaclone.dto.user.*;
import com.instaclone.instaclone.service.ProfileService;
import com.instaclone.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    public UserDto updateUser(@RequestBody UpdateUserDto updateUserDto, Authentication authentication) {
        updateUserDto.setUsername(authentication.getName());
        return profileService.updateUser(updateUserDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto, Authentication authentication) {
        changePasswordDto.setUsername(authentication.getName());
        userService.changePassword(changePasswordDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/follow-unfollow")
    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    public void followUnfollow(@RequestBody ChangeFollowingStatusDto changeFollowingStatusDto, Authentication authentication) {
        changeFollowingStatusDto.setMyUsername(authentication.getName());
        profileService.followUnfollow(changeFollowingStatusDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam("email") String email) {
        userService.sendMailForResetPassword(email);
    }

    @PostMapping("/change-password-token")
    public Boolean changePasswordWithToken(@RequestParam("token") String token, @RequestBody ResetPasswordDto resetPasswordDto) {
        userService.resetPassword(resetPasswordDto, token);
        return true;
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @GetMapping("")
    public List<UserSearchResultDto> searchUser(@RequestParam("query") String query) {
        return userService.findUsers(query);
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @GetMapping(value = "/profile-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfileInfoDto getProfileInfo(@RequestParam String username) {
        return userService.getProfileInfo(username);
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @GetMapping(value = "/follow-check", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean checkIfUserFollowsUser(@RequestParam String username, @RequestParam String followedUsername) {
        return userService.checkIfUserFollowsUser(username, followedUsername);
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @GetMapping(value = "/suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfileInfoDto> getSuggestions(Authentication authentication) {
        return profileService.getSuggestions(authentication.getName());
    }


}
