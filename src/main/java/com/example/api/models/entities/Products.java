package com.example.api.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;



/**
 * @Id -> orm
 * @GeneratedValue -> orm
 * @Column -> orm
 * @Id -> orm
 * @Table() -> ini untuk menamai entity 
 * @NotEmpery -> validator
 * class ini adalahg class untuk membuat entity database atau tabel didalam database kita
 * meng inplements abstarc interface serializable untuk mengubah class ini menjadi 
 * byte stream agar dapat dikirim melalui network karna rest api mengirim dan menerima data melalui 
 * network
 */
@Entity
@Table(name = "tbl_products")
public class Products implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * annotasi @Id untuk menjadikan field ini menjadi primary key
     * anotasi @GenerateValue(strategy = GenerationType.IDENTITY) agar primary key
     * ini auto increment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name can't be null")
    @Column(length = 100)
    private String name;

    @NotEmpty(message = "description can't be empty")
    @Column(length = 1000)
    private String description;

    @Min(value = 1,message = "price can't be empty")
    @Column(length = 100)
    private double price;

    @ManyToOne()
    private Category category;

    /**
     * untuk relasi many to many tentunay nanti kita akan membuat
     * tabel intermediate yang menyimpan key dari dua table 
     * untuk membuat table intermediate kita bisa menggunakan 
     * anotasi @joinTable() dan di dalam join table kita akan memberi
     * nama tabel intermediate dengan parameter( name = "" ) 
     * setelah itu untuk membuat field didalam table intermediate
     * kita bisa menambahkan parameter (joinColumns = ) dan kita 
     * harus menambahkan annotasi @joinColumns(name = "nama field")
     * inverstJoinColumns = @joinColumns(name = "nama field")
     * 
     * Contoh seperti di bawah ini : 
     */

    @ManyToMany
    @JoinTable(
        name = "product_suplier",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "suplier_id"))
    private Set<Suplier> suplier;

    public Products(Long id,String name,String description,double price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Products(){}
 
    public void setSuplier(Set<Suplier> suplier) {
       this.suplier = suplier;
    }
 
    public Set<Suplier> getSuplier() {
       return suplier;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setCategory(Category category) {
       this.category = category;
    }
    public Category getCategory() {
       return this.category;
    }

    @Override
    public String toString() {
        return "Products [description=" + description + ", id=" + id + ", name=" + name + ", price=" + price + "]";
    }

}
