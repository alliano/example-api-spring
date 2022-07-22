package com.example.api.models.entities;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * disini kita implementasi userdetails untuk mengintregasikan springBoot dengan springSecurity 
 * userDetails jga dia meng implement Serializable
 * jadi kita tidak perlu implement disini
 */
@Entity
@Table(name = "appUser")
public class AppUser implements UserDetails {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(nullable = false,length = 50)
   private String fullName;

   @Column(nullable = false, unique = true)
   @Email
   private String email;

   @Column(nullable = false,length = 255)
   private String password;

   //ini untuk meng convert firld menjadi bertipe enum

   @Enumerated(EnumType.STRING)
   private AppUserRole appUserRole;

   public AppUser(){}

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public AppUserRole getAppUserRole() {
      return appUserRole;
   }

   public void setAppUserRole(AppUserRole appUserRole) {
      this.appUserRole = appUserRole;
   }


   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      SimpleGrantedAuthority autority = new SimpleGrantedAuthority(appUserRole.name());
      return Collections.singletonList(autority);
   }

   @Override
   public String getPassword() {
      return this.password;
   }

   @Override
   public String getUsername() {
      return this.email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   
}
