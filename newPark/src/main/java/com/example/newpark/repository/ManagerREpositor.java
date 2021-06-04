package com.example.newpark.repository;

import com.example.newpark.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerREpositor extends JpaRepository<Manager,Long> {

}
