package com.example.newpark.repository;

import com.example.newpark.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findMemberById(Long id);

    Member findMemberByCarNumber(String carNumber);

    List<Member> findMemberByMemberGrade(String grade);





}
