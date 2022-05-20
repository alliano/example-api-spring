package com.example.api.services;

import java.util.List;

import com.example.api.models.entities.Products;
import com.example.api.models.repositories.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServices {

    /**
     * jika ingi melakukan interaksi ke database maka 
     * kita harus memangill terlebih dahulu 
     * repository entity nya 
     * dan meng inject nya menggunakan 
     * @Autowired
     */
    @Autowired
    private ProductRepo repoProduct;

    public Products save (Products products){
        return repoProduct.save(products);
    }

    public Products findOne(long id) {
        return repoProduct.findById(id).get();
    }
    public Iterable<Products> findAll(){
        return repoProduct.findAll();
    }
    public String removeOne(long id) {
        repoProduct.deleteById(id);
        return "produck has deleted";
    }

    public List<Products> findByName(String name){
        return repoProduct.findByNameContains(name);
    }
}
