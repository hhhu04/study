package com.example.newpark.repository;

import com.example.newpark.entity.ParkLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkLogsRepository extends JpaRepository<ParkLogs,Long> {

    List<ParkLogs> findByMemberIdOrderByIdDesc(Long id);



}
