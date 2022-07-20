package com.example.api.controllers.coba;

import javax.validation.Valid;

import com.example.api.dto.coba.ResponseDataUser;
import com.example.api.models.entities.tryning.Users;
import com.example.api.services.coba.Userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
    public ResponseEntity<ResponseDataUser<Users>> createrUsers(@RequestBody @Valid Users user, Errors errors){
        ResponseDataUser<Users> responseDataUser = new ResponseDataUser<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseDataUser.getMessages().add(error.getDefaultMessage());
            }
            responseDataUser.setStatus(false);
            responseDataUser.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDataUser);
        }
        responseDataUser.setStatus(true);
        responseDataUser.setPayload(userservice.saveUsers(user));
        return ResponseEntity.status(HttpStatus.OK).body(responseDataUser);
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
    public ResponseEntity<ResponseDataUser<Users>> updateUser(@RequestBody @Valid Users users, Errors error){
        ResponseDataUser<Users> responsedata = new ResponseDataUser<>();
        if (error.hasErrors()) {
            for (ObjectError err : error.getAllErrors()) {
                responsedata.getMessages().add(err.getDefaultMessage());
            }
            responsedata.setStatus(false);
            responsedata.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsedata);
        }
        responsedata.setStatus(true);
        responsedata.setPayload(userservice.saveUsers(users));
        return ResponseEntity.status(HttpStatus.OK).body(responsedata);
    }
    @DeleteMapping(path = "/{id}")
    public String destroyUser(@PathVariable("id") long id){
        return userservice.deleteUser(id);
    }
}
