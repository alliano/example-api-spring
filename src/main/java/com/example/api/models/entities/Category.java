package com.example.api.models.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * dengan meng extend class BaseEntity<T> maka class entity ini akan mewarisi beberapa field yang
 * telah ditentukan di class BaseEntity<T> dan kelas ini akan memiliki field auditing 
 * setelah ini kita perlu juga harus membuat class yang meng implementasi AuditorAware untuk
 * mendapatkan user mana yang sedang login atau user mna yang melakukan aksi tertentu 
 * source : com.example.api.configure.utils.AuditorAwareConfiguration.java
 */
@Entity
@Table(name = "category")
public class Category extends BaseEntity<String> implements Serializable {

   private static final long serialVersionUID = 2L;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 50,nullable = false,unique = true)
   private String name;

   public Category(){}

   public void setId(Long id) {
      this.id = id;
   }
   public Long getId() {
      return this.id;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getName() {
      return this.name;
   }

}
