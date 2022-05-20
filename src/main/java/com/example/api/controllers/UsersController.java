package com.example.api.controllers;

import com.example.api.models.entities.Users;
import com.example.api.services.Userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UsersController {
    
    @Autowired
    private Userservice userservice;

    @PostMapping(path = "")
    public Users createrUsers(@RequestBody Users user){
        return userservice.saveUsers(user);
    }
    @GetMapping(path = "")
    public Iterable<Users> findAllusers(){
        return userservice.findAllUsers();
    }
    @GetMapping(path = "/{id}")
    public Users findOnUser(@PathVariable("id") long id){
        return userservice.findOneUser(id);
    }
    @PutMapping(path = "")
    public Users updateUser (@RequestBody Users users){
        return userservice.saveUsers(users);
    }
    @DeleteMapping(path = "/{id}")
    public String destroyUser(@PathVariable("id") long id){
        return userservice.deleteUser(id);
    }
}
