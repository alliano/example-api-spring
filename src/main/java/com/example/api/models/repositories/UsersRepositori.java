package com.example.api.models.repositories;

import java.util.List;

import com.example.api.models.entities.Users;

import org.springframework.data.repository.CrudRepository;


public interface UsersRepositori extends CrudRepository<Users,Long> {

    List<Users> findByNameContains(String name);
}
