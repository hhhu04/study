package com.example.newpark.repository;

import com.example.newpark.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepositor extends JpaRepository<Manager,Long> {

    Manager findManagerByManagerIdAndPassword(String id,String password);

    Manager findDistinctByManagerIdAndPassword(String id,String password);


}
