package com.leratoletsepe.intellectsacademyapi.controllers;

import com.leratoletsepe.intellectsacademyapi.models.dto.User ;
import com.leratoletsepe.intellectsacademyapi.models.dto.enums.UserType.UserRole;
import com.leratoletsepe.intellectsacademyapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String title = (String) userMap.get("title");
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String role = (String) userMap.get("role");

        UserRole userRole = UserRole.valueOf(role.toUpperCase());

        User user = userService.registerUser(title, firstName, lastName, email, password, userRole);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user){
        Map<String, String> map = new HashMap<>();
        map.put("token", "to be implemented");
        return map;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId) {
        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}