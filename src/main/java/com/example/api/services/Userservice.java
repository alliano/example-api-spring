package com.example.api.services;

import java.util.List;
import java.util.Optional;

import com.example.api.models.entities.Users;
import com.example.api.models.repositories.UsersRepositori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Userservice {

    @Autowired
    private UsersRepositori userRepo;
    
    public Users saveUsers(Users users){
        return userRepo.save(users);
    }
    public Iterable<Users> findAllUsers(){
        return userRepo.findAll();
    }
    public Users findOneUser(long id){
        Optional<Users> user = userRepo.findById(id);
        if (user.isPresent()) {
            return null;
        }
        return userRepo.findById(id).get();
    }
    public String deleteUser(long id){
        userRepo.deleteById(id);
        return "user has deleted";
    }
    public List<Users> findByName(String name){
        return userRepo.findByNameContains(name);
    }
}
