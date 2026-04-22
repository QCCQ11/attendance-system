package com.example.attendance.service;

import com.example.attendance.entity.Student;
import java.util.List;

public interface StudentService {
    Student save(Student student);
    Student findById(Long id);
    Student findByStudentId(String studentId);
    List<Student> findAll();
    List<Student> findByClassName(String className);
    void deleteById(Long id);
}