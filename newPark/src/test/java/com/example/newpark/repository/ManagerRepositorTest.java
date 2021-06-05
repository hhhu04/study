package com.example.newpark.repository;

import com.example.newpark.domain.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManagerRepositorTest {

    @Autowired
    ManagerRepositor managerRepositor;

    @Test
    public void create(){
        Manager manager = new Manager();
        manager.setManagerId("123");
        manager.setPassword("123");
        manager.setGrade("master");
        manager.setName("사장");
        manager.setPhone("010-0000-0000");
        manager.setCreatedAt(LocalDateTime.now());

        managerRepositor.save(manager);

    }

}