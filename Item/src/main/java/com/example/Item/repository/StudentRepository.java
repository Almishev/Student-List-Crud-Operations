package com.example.Item.repository;

import com.example.Item.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
/*
    @Query(value = "SELECT * from table_students t Where t.lastName like %?1%",nativeQuery = true)
    public List<Student> findAll(String keyword);

 */



}
