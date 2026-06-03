package com.example.attendance.dao;

import com.example.attendance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByStudentId(String studentId);

    List<Student> findByClassName(String className);

    List<Student> findByNameContaining(String keyword);

    // 排序方法
    List<Student> findAllByOrderByIdAsc();
    List<Student> findAllByOrderByIdDesc();
    List<Student> findAllByOrderByStudentIdAsc();
    List<Student> findAllByOrderByStudentIdDesc();
    List<Student> findAllByOrderByNameAsc();
    List<Student> findAllByOrderByNameDesc();
}