package com.example.api.models.entities;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * kelas ini nanti nya akan dijadikan superclass 
 * jikalau ada entity yang ingin menerapkan auidit tentang datanya maka bisa meng extend class ini
 * kelas ini akan memuat beberapa field untuk informasi dari suatu data 
 * seperti field createdBy updatedBy dan lain lain 
 */


@MappedSuperclass
/**
 * @MappedSuperClass 
 * annotasi ini digunakan untuk mewariskan kelas manapun yang mengextend ke kelas ini akan memiliki 
 * proprti yang dimiliki kelas ini 
 * 
*/
@EntityListeners(AuditingEntityListener.class)
/**
 * @EntitiyListener
 * adalah annotasi untuk mentriger kelas ini jikalau ada proses validasi atau prosers Insert Update Delete
 * dan jikalau dalam proses tersebut terjadi Error maka JPA akan membatalkan proses 
 * persistance(aksi mengubah atau menghapus adatau mengdelete data) ke database
 * 
 */
public class BaseEntity<T> {

   @CreatedBy
   protected T createdBy;

   @CreatedDate
   @Temporal(TemporalType.TIMESTAMP)
   protected Date createdDate;

   @LastModifiedBy
   protected T updateBy;

   @LastModifiedDate
   @Temporal(TemporalType.TIMESTAMP)
   protected Date updateDate;

   public T getCreatedBy() {
      return createdBy;
   }

   public void setCreatedBy(T createdBy) {
      this.createdBy = createdBy;
   }

   public Date getCreatedDate() {
      return createdDate;
   }

   public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
   }

   public T getUpdateBy() {
      return updateBy;
   }

   public void setUpdateBy(T updateBy) {
      this.updateBy = updateBy;
   }

   public Date getUpdateDate() {
      return updateDate;
   }

   public void setUpdateDate(Date updateDate) {
      this.updateDate = updateDate;
   }

}
