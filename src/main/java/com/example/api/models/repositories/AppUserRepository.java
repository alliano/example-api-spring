package com.example.api.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.models.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
   
   /**
    * ini adlah method yang penting yang nantinya kan di gunakan oleh spring secruity untuk me load data
    * user berdasarkan user name nya ...di kasus ini saya menggunakan email sebagai username nya 
    * jika kalian menggunakan nama untuk usernamenya silakan sesuaikan method derived querynya
    * @param email
    * @return
    */
   public Optional<AppUser> findByEmail(String email);
}
