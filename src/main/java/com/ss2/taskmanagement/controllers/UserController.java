package com.ss2.taskmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss2.taskmanagement.models.User;
import com.ss2.taskmanagement.services.UserService;

@RestController 
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    @Autowired 
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> list(
        @RequestParam(value = "search", required = false) String search
    ){
        List<User> allUsers = userService.findAllUsers();
        if (search == null || search.trim().isEmpty()) {
            return ResponseEntity.ok(allUsers);
        }

        List<User> res = new ArrayList<>();
        String keyword = search.trim().toLowerCase();
        for (User u : allUsers) {
            if (u.getUsername() != null && u.getUsername().toLowerCase().contains(keyword)) {
                res.add(u);
            }
        }
        return ResponseEntity.ok(res);
    }
}
