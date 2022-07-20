package com.example.api.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.api.models.entities.Category;

/**
 * JPArepository adalah kelas yang memiliki fitur paging and sorting and CRUD
 * pagingAndSortingRepositori memiliki fitur CRUD dan paging dan sorting
 * CudRepository kelas yang hanya memiliki fitur CRUD
 */

 
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long>{
   
  //contoh query dan pagination
  //ini menggunkan derived query dan pagination dengan menambahkan parameter Pageable
  //dengan kita meretrun dengan tipe Page maka kita akan mendapatkan informasi tambahan tentang paging
  public Page<Category> findByNameContaining(String name, Pageable pageable);


}
