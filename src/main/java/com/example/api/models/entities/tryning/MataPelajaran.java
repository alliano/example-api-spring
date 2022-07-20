package com.example.api.models.entities.tryning;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "MataPelajaran")
public class MataPelajaran implements Serializable{
   
   public static final long serialVersionUID = 9L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @NotEmpty(message = "nama kelas tidak boleh kososng")
   private String namaMataPelajaran;

   @ManyToMany
   @JoinTable(name = "tbl_mapelId_kelasId",
   joinColumns = @JoinColumn(name = "mapelId"),
   inverseJoinColumns = @JoinColumn(name = "kelasId"))
   private List<Kelas> kelas;

   @OneToOne(cascade = {CascadeType.ALL})
   private Guru guru;

   @NotNull(message = "durasi belajar tidakboleh kosong")
   private double durasiBelajar;
   
   public MataPelajaran(){}

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getNamaMataPelajaran() {
      return namaMataPelajaran;
   }

   public void setNamaMataPelajaran(String namaMataPelajaran) {
      this.namaMataPelajaran = namaMataPelajaran;
   }

   public List<Kelas> getKelas() {
      return kelas;
   }

   public void setKelas(List<Kelas> kelas) {
      this.kelas = kelas;
   }

   public Guru getGuru() {
      return guru;
   }

   public void setGuru(Guru guru) {
      this.guru = guru;
   }
   
}
