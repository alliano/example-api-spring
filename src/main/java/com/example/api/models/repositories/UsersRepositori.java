package com.example.api.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.api.models.entities.tryning.Users;


public interface UsersRepositori extends CrudRepository<Users,Long> {

    List<Users> findByNameContains(String name);
}
