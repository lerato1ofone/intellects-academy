package com.leratoletsepe.intellectsacademyapi.controllers;

import com.leratoletsepe.intellectsacademyapi.models.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.UserProfileDto;
import com.leratoletsepe.intellectsacademyapi.services.ProfileService;
import com.leratoletsepe.intellectsacademyapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDto> getUser(HttpServletRequest request, @PathVariable("userId") Integer userId) {
        UserProfileDto user = profileService.getUserProfileById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<UserProfileDto> updateUserProfile(HttpServletRequest request,
                                           @RequestBody User user){
        Integer userId = (Integer) request.getAttribute("userId");

        UserProfileDto updatedUser = profileService.updateProfile(userId, user);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, Boolean>> deleteAccount(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");

        userService.deleteAccount(userId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}