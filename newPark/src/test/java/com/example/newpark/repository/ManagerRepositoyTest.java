package com.example.newpark.repository;

import com.example.newpark.entity.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ManagerRepositoyTest {

    @Autowired
    ManagerRepositoy managerRepositoy;

    @Test
    public void create(){
        Manager manager = new Manager();
        manager.setManagerId("123");
        manager.setPassword("123");
        manager.setGrade("master");
        manager.setName("사장");
        manager.setPhone("010-0000-0000");
        manager.setCreatedAt(LocalDateTime.now());

        managerRepositoy.save(manager);

    }

}