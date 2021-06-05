package com.example.newpark.service;

import com.example.newpark.domain.Manager;
import com.example.newpark.repository.ManagerRepositor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepositor managerRepositor;


    public int login(Manager manager) throws Exception{
        Long id = managerRepositor.findManagerByManagerIdAndPassword(manager.getManagerId(), manager.getPassword()).getId();
        System.out.println(manager);
        if(id == null) return 0;
        return 1;
    }





}
