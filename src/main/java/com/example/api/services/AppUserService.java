package com.example.api.services;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.models.entities.AppUser;
import com.example.api.models.repositories.AppUserRepository;

/**
 * di sini kita harus implement interface dari UserDetailService 
 * ini nanti kita gunakan  untuk meng inject kelas AppUserService ke dalam configurasinya spring secruity
 * dan untuk meload user
 */
@Service
@Transactional
public class AppUserService implements UserDetailsService{

   @Autowired
   private AppUserRepository appUserRepository;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      return this.appUserRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException(String.format("user with \s notfound", email)));
   }
   public AppUser registerAppUser(AppUser user){
       /**
        * ni di cek apakah email yang digunakan untuk reqister udah ada atau belum .klo ada maka akan di throw
        * error exception dan jika blum ada maka user bisa register
        */
      boolean userIsExist = appUserRepository.findByEmail(user.getEmail()).isPresent();
      if(userIsExist) {
         throw new RuntimeException(String.format("user with email \s already exist", user.getEmail()));
      }else{
         //enkripsi password
         if (user.getPassword().isEmpty() || user.getPassword() == null){
            throw new RuntimeErrorException(null, "pasword can't encription because password is empty!");
         }else{
            try {
               user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
               return appUserRepository.save(user);
            } catch (Exception e) {
               System.out.println(e.getMessage());
               return null;
            }
         }
      }
   }
   
}
