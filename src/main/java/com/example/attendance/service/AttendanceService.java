package com.example.attendance.service;

import com.example.attendance.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface AttendanceService {
    Attendance save(Attendance attendance);
    Page<Attendance> findByStudentId(String studentId, Specification<Attendance> spec, Pageable pageable);
}