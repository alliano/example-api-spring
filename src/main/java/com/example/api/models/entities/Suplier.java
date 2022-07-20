package com.example.api.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "Suplier")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id") // => agar tidak infinity loop relation dan menamoilkan semua relasi
public class Suplier implements Serializable{
   
   private static final long serialVersionUID = 4L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long Id;

   @Column(nullable = false,length = 100)
   @NotNull(message = "name can't be empty")
   private String name;

   @Column(nullable = false,length = 255)
   @NotNull(message = "address can't empty")
   private String address;

   @Column(length = 255,nullable = false, unique = true)
   @NotNull(message = "email can't be empty")
   @Email
   private String email;

   /**
    * untuk many to many jangan lupa menambahkan parameter 
    * mappedBy = "nama field yang menyimpan konfigurassi untuk membuat table intermediate"
    */
   @ManyToMany(mappedBy = "suplier")
   @JsonBackReference // => agar annati tidak terjadi infinity loop relation saat mengquery subuah data
   private Set<Products> product;

   public void setProduct(Set<Products> product) {
      this.product = product;
   }
   public Set<Products> getProduct() {
      return product;
   }

   public Long getId() {
      return Id;
   }

   public void setId(Long id) {
      Id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }


   

}
