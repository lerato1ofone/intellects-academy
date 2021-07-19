package com.leratoletsepe.intellectsacademyapi.controllers;

import com.leratoletsepe.intellectsacademyapi.models.User;
import com.leratoletsepe.intellectsacademyapi.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(HttpServletRequest request, @PathVariable("userId") Integer userId) {
        User user = profileService.getUserProfileById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<User> updateUserProfile(HttpServletRequest request,
                                           @RequestBody User user){
        Integer userId = (Integer) request.getAttribute("userId");

        User updatedUser = profileService.updateProfile(userId, user);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}