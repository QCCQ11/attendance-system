package com.example.attendance.dao;

import com.example.attendance.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {

    List<Attendance> findByStudentId(String studentId);

    List<Attendance> findByStudentIdAndCourseName(String studentId, String courseName);

    List<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);

    List<Attendance> findByStatus(String status);

    // 分页方法
    Page<Attendance> findByStudentId(String studentId, Pageable pageable);
    Page<Attendance> findByStatus(String status, Pageable pageable);
    Page<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}