package com.ss2.taskmanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ss2.taskmanagement.models.User;

@Repository 
public class UserRepository {
    private List<User> users = new ArrayList<>();
    
    public UserRepository(){
        User u1 = new User(1, "john_doe", "[EMAIL_ADDRESS]", "Admin");
        User u2 = new User(2, "jane_doe", "[EMAIL_ADDRESS]", "User");
        User u3 = new User(3, "peter_pan", "[EMAIL_ADDRESS]", "User");
        users.add(u1);
        users.add(u2);
        users.add(u3);
    }

    public List<User> findAll(){
        return users;
    }

    public User findById(int id){
        for(User u:users){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }
}
