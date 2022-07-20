package com.example.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.api.models.entities.Products;
import com.example.api.models.entities.Suplier;
import com.example.api.models.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * service disini untuk mengelola bisnis logic dari app
 * misal seperti pembayaran suatu barang atau suatu proses transaksi atar bank
 */

@Service
public class ProductServices {

    /**
     * jika ingi melakukan interaksi ke database maka 
     * kita harus memangill terlebih dahulu 
     * repository entity nya 
     * dan meng inject nya menggunakan 
     * @Autowired
     * 
     * productRepo adalah object yang kita buat di dan object tersebut meng extends kelas CrudRepository
     */
    @Autowired
    private ProductRepo repoProduct;

    @Autowired
    private SuplierService supplierService;

    public Products save (Products products){
        /**
         * method save adalah method milik class CrudRepository kita bisa memakai nya karna interface 
         * ProductRepo meng extends class CrudRepository
         * method save disini bisa digunakan untuk 2 action 
         * mengupdate dan meng insert data 
         * jpa secara default akan memahami aksi mana yang akan di lakukan
         * jikalau data tersebut belum memiliki id maka data tersebut akan di insert dan jika 
         * data tersebut telah memiliki id maka data tersebut akan di update
         * */ 
        return repoProduct.save(products);
    }

    /**
     * ini adalah method untuk find data berdasarkan id
     * method findById() adalah method milik CrudRepository
     * @param id
     * @return
     */
    public Products findOne(long id) {
        Optional<Products> product = repoProduct.findById(id);
        if (product.isEmpty()){
            return null;
        }
        return repoProduct.findById(id).get();
    }

    /** @GetMapping(path = "/search/category/{categoryId}")
    public List<Products> findProductByCategoryId(@RequestBody @PathVariable("categoryId") long categoryId){
        return repoProduct.findProductByCategoryId(categoryId);
    }
     * ini adalah method untuk find all data didalam database
     * method findAll() adalah method dari class CrudRepository
     * @return
     */
    public Iterable<Products> findAll(){
        return repoProduct.findAll();
    }
    /**
     * method ini untuk menghapus data berdasarkan id 
     * deleteById() adalah method dari class CrudRepository
     * @param id
     * @return
     */
    public String removeOne(long id) {
        repoProduct.deleteById(id);
        return "produck has dele ";
    }
    /**
     * method ini untuk find data berdasarkan nama data
     * method findByNameContains() adalah method milik ProductRepo
     * @param name
     * @return
     */
    public List<Products> findByName(String name){
        return repoProduct.findByNameContains(name);
    }

    public void addSuplier(Suplier suplier,long procuctId){
        Products product = findOne(procuctId);
        if(product == null) throw new RuntimeException("id "+ product + "not found");
        else
        product.getSuplier().add(suplier);
        save(product);
    }

    public Products findProductByName(String name) {
        return repoProduct.findProductByName(name);
    }

    public Set<Products> findProductByNameLike(String name){
        return repoProduct.findByNameLike("%"+name+"%");
    }

   public List<Products> findProductBycategoryId(long categoryId){
       return repoProduct.findProductByCategoryId(categoryId);
   }

   public List<Products> findProductBySuplierId(long id){
    Suplier suplier = supplierService.findById(id);
    if(suplier == null) return new ArrayList<Products>();
    else
    return repoProduct.findProductBySupplier(suplier);
   }
}
