package com.example.api.models.repositories.coba;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.api.models.entities.tryning.Students;

public interface StudentsRepository extends CrudRepository<Students,Long> {
   
   public List<Students> findByNameContains(String name);
}
