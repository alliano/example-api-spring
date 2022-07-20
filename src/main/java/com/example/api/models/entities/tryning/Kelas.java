package com.example.api.models.entities.tryning;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;


@Entity
@Table(name = "kelas")
public class Kelas implements Serializable {
   
   public static final long serialVersionUID = 10L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Min(value = 1L,message = "kelas tidak boleh kososng")
   private int kelas;

   @ManyToMany(mappedBy = "kelas")
   private List<MataPelajaran> mapel;

   @OneToOne(cascade = {CascadeType.ALL})
   private Guru walikelas;

   public Kelas(){}
   
   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public int getKelas() {
      return kelas;
   }

   public void setKelas(int kelas) {
      this.kelas = kelas;
   }

   public Guru getWalikelas() {
      return walikelas;
   }

   public void setWalikelas(Guru walikelas) {
      this.walikelas = walikelas;
   }

   public List<MataPelajaran> getMapel() {
      return mapel;
   }
   public void setMapel(List<MataPelajaran> mapel) {
      this.mapel = mapel;
   }

}
