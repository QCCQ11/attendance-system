package com.example.attendance.dao;

import com.example.attendance.entity.Student;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {
    private static final List<Student> studentList = new ArrayList<>();

    public void save(Student student) {
        studentList.add(student);
        System.out.println("【模拟数据库】保存学生：" + student.getName());
    }

    public Student findByStudentId(String studentId) {
        for (Student s : studentList) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }
}
