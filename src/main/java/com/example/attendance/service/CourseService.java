package com.example.attendance.service;

import com.example.attendance.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(Integer id);
}