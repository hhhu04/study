package com.example.newpark.repository;

import com.example.newpark.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepositoy extends JpaRepository<Manager,Long> {

    Manager findManagerByManagerIdAndPassword(String id,String password);

    Manager findDistinctByManagerIdAndPassword(String id,String password);

    List<Manager> findAll();

    boolean existsManagerByName(String name);

    Manager findManagerByName(String name);


    Manager findByManagerId(String managerId);

    boolean existsManagerByManagerId(String id);



}
