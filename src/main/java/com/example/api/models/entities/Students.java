package com.example.api.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "students")
public class Students implements Serializable {

   private static final long serialVersionUID = 3L;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotEmpty(message = "name can be empt")
   @Column(length = 50, nullable = false)
   private String name;

   @Column(length = 15,nullable = false,unique = true)
   private int nip;

   @Column(length = 2)
   private int clas;

   @NotEmpty(message = "homeroom_teacher can't emmpty")
   @Column(length = 50,nullable = false)
   private String homeroom_teacher;

   public Students(Long id, String name, int nip,int clas, String homeroom_teacher) {
      this.id = id;
      this.name = name;
      this.nip = nip;
      this.clas = clas;
      this.homeroom_teacher = homeroom_teacher;
   }

   public Students(){}

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getNip() {
      return nip;
   }

   public void setNip(int nip) {
      this.nip = nip;
   }

   public int getClas() {
      return clas;
   }

   public void setClas(int clas) {
      this.clas = clas;
   }

   public String getHomeroom_teacher() {
      return homeroom_teacher;
   }

   public void setHomeroom_teacher(String homeroom_teacher) {
      this.homeroom_teacher = homeroom_teacher;
   }
   


}
