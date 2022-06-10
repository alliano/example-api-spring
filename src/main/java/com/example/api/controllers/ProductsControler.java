package com.example.api.controllers;
import javax.validation.Valid;

import com.example.api.dto.ResponsData;
import com.example.api.models.entities.Products;
import com.example.api.services.ProductServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * kita harus menganotasi class nya sebagai @ReastController
 * dan mengelola path url nya dengan cara menggunakan @RequestMapping(path = "api/product")
 * ini maksudnya class ini akan berfugsi saat url api/products di panggil
 */

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
    public ResponseEntity<ResponsData<Products>> create (@RequestBody @Valid Products products, Errors error){
        ResponsData<Products> responsData = new ResponsData<>();
        if (error.hasErrors()) {
            for (ObjectError err : error.getAllErrors()) {
                responsData.getMessagesList().add(err.getDefaultMessage());
            }
          responsData.setStatus(false);
          responsData.setPaylooad(null);
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsData);
        }
        responsData.setStatus(true);
        responsData.setPaylooad(productServices.save(products));
        return ResponseEntity.status(HttpStatus.OK).body(responsData);
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
    public ResponseEntity<ResponsData<Products>> update(@RequestBody @Valid Products products, Errors error){
        ResponsData<Products> responsData = new ResponsData<>();
        if (error.hasErrors()) {
            for (ObjectError err : error.getAllErrors()) {
                responsData.getMessagesList().add(err.getDefaultMessage());
            }
          responsData.setStatus(false);
          responsData.setPaylooad(null);
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsData);
        }
        responsData.setStatus(true);
        responsData.setPaylooad(productServices.save(products));
        return ResponseEntity.status(HttpStatus.OK).body(responsData);
    }
    @DeleteMapping(path = "")
    public void deleteById(@PathVariable("id") long id){
        productServices.removeOne(id);
    }

}
