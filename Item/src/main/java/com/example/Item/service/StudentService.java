package com.example.Item.service;

import com.example.Item.model.Student;
import com.example.Item.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository  repository) {
        this.repository=repository;
    }

    public Page<Student> getStudents(int pageNumber,int size) {
        return repository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<Student> getStudent(int id) {
        return repository.findById(id);
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }



}
