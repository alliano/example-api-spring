package com.example.api.models.entities.tryning;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "murit")
public class Murit implements Serializable {
   public static final long serialVersionUID = 7L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotEmpty(message = "nama tidak boleh kosong")
   private String nama;

   @OneToOne(cascade = {CascadeType.ALL})
   private Alamat alamat;

   @NotNull(message = "npm tidak boleh kosong")
   private int npm;

   @ManyToOne(cascade = {CascadeType.ALL})
   private Kelas kelas;
   

   public Kelas getKelas() {
      return kelas;
   }

   public void setKelas(Kelas kelas) {
      this.kelas = kelas;
   }

   public Murit(){}

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNama() {
      return nama;
   }

   public void setNama(String nama) {
      this.nama = nama;
   }

   public Alamat getAlamat() {
      return this.alamat;
   }

   public void setAlamat(Alamat alamat) {
      this.alamat = alamat;
   }

   public int getNpm() {
      return npm;
   }

   public void setNpm(int npm) {
      this.npm = npm;
   }

   


}
