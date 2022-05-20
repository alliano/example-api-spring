package com.example.api.controllers;

import com.example.api.models.entities.Products;
import com.example.api.services.ProductServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/products")
public class ProductsControler {
    
    @Autowired
    private ProductServices productServices;

    /**
     * @RequestBody == data yang kita 
     * ambil nanti dari request body clien
     * @param products
     * @return
     */
    @PostMapping(path = "")
    public Products create (@RequestBody Products products){
        return productServices.save(products);
    }

    /**
     * mengkueri semua data si dalam database
     * @return
     */
    @GetMapping(path = "")
    public Iterable<Products> findAll(){
        return productServices.findAll();
    }


    @GetMapping(path = "/{id}")
    public Products findById(@PathVariable("id") long id){
        return productServices.findOne(id);
    }

    @PutMapping(path = "")
    public Products update(@RequestBody Products products){
        return productServices.save(products);
    }
    @DeleteMapping(path = "")
    public void deleteById(@PathVariable("id") long id){
        productServices.removeOne(id);
    }

}
