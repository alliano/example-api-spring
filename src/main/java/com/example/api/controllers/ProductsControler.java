package com.example.api.controllers;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.example.api.dto.ResponsData;
import com.example.api.dto.SearchDto;
import com.example.api.models.entities.Products;
import com.example.api.models.entities.Suplier;
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
 * 
 * @RequestMapping dan @postMapping dan @GetMapping itu sebenar nya sama 
 * yang membedakan adalah @PostMapping dan @GetMapping itu mutlak methodnya
 * tetapi @RequestMapping itu kita bsia set methodnya
 */

@RestController
@RequestMapping(path = "/api/products")
public class ProductsControler {
    
    /**
     * ini akan kita gunnakan untuk memanggil service dan untuk melakukan proses crud
     */
    @Autowired
    private ProductServices productServices;

    /**
     * @RequestBody == data yang kita 
     * ambil nanti dari request body clien
     * data data akan di kirim melalui request body
     * @param products
     * @return
     * 
     * 
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


    /**
     * untuk memparsing id kita bisa memberi parameter didalam path nya
     * untuk contoh ini saya menggunakan @GetMapping(path = "{id}") dan method tersebut 
     * harus menagkap parameter yang di kirimklan dengan menggunkan @pathVariable("id")''
     * @param id
     * @return
     */
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

    @PostMapping(path = "/{id}")
    public void addSuplier(@RequestBody Suplier suplier,@PathVariable("id") long procuctId){
        productServices.addSuplier(suplier, procuctId);
    }

    @PostMapping(path = "/search/name")
    public Products findProductsByName(@RequestBody SearchDto searchDto){
        return productServices.findProductByName(searchDto.getKeyword());
    }

    @PostMapping(path = "search/nameLike")
    public Set<Products> findProductByNameLike(@RequestBody SearchDto searchDto){
        return productServices.findProductByNameLike(searchDto.getKeyword());
    }

    @GetMapping(path = "/search/category/{categoryId}")
    public List<Products> findProductByCategoryId(@RequestBody @PathVariable("categoryId") long categoryId){
        return productServices.findProductBycategoryId(categoryId);
    }

    @GetMapping(path = "/search/supplier/{supplierId}")
    public List<Products> findBySUpplierId(@RequestBody @PathVariable("supplierId") long supplierId){
        return productServices.findProductBySuplierId(supplierId);
    }

}
