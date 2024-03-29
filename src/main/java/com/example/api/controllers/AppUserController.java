package com.example.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.AppUserDto;
import com.example.api.dto.ResponsData;
import com.example.api.models.entities.AppUser;
import com.example.api.models.entities.AppUserRole;
import com.example.api.services.AppUserService;

import eye2web.modelmapper.ModelMapper;


@RestController
@RequestMapping(path = "/api/users")
public class AppUserController {

   @Autowired
   private AppUserService appUserService;

   @Autowired
   private ModelMapper modelMapper;

   @PostMapping(path = "/register")
   public ResponseEntity<ResponsData<AppUser>> register(@RequestBody @Valid AppUserDto userDto, Errors errors){

      ResponsData<AppUser> responsHttp = new ResponsData<AppUser>();
      if(errors.hasErrors()){
         for (ObjectError error : errors.getAllErrors()) {
            responsHttp.getMessagesList().add(error.getDefaultMessage());
         }
         responsHttp.setStatus(false);
         responsHttp.setPaylooad(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsHttp);
      }else{
         responsHttp.setStatus(true);
         responsHttp.getMessagesList().add("success registered");
         AppUser user = modelMapper.map(userDto, AppUser.class);
         if(userDto.getAppUserRole().equalsIgnoreCase("ADMIN")){
            user.setAppUserRole(AppUserRole.ADMIN);
         }else if(userDto.getAppUserRole().equalsIgnoreCase("USER") || 
         !userDto.getAppUserRole().equalsIgnoreCase("USER") || 
         !userDto.getAppUserRole().equalsIgnoreCase("ADMIN") ||
         userDto.getAppUserRole().equalsIgnoreCase(null)){
            user.setAppUserRole(AppUserRole.USER);
         }
         responsHttp.setPaylooad(appUserService.registerAppUser(user));
         return ResponseEntity.status(HttpStatus.OK).body(responsHttp);
      }
       /**
        * setelah kita membuat controler ini maka kita perlu mememberi tau spring secruity bahwa 
        * endpoin ini jagan di proteksi agar saat mengakses endpoin ini tidak melakukan
        * login terlebih dahulu ,,karna endpoint ini berfungsi untuk register
        */
   }

   @GetMapping(path = "")
   public List<AppUser> findAll(){
      return appUserService.findAll();
   }
}
