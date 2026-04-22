package com.example.attendance.dao;

import com.example.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(String studentId);

    List<Attendance> findByStudentIdAndCourseName(String studentId, String courseName);

    List<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);

    List<Attendance> findByStatus(String status);
}