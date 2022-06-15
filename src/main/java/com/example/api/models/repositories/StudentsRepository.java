package com.example.api.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.api.models.entities.Students;

public interface StudentsRepository extends CrudRepository<Students,Long> {
   
   public List<Students> findByNameContains(String name);
}
