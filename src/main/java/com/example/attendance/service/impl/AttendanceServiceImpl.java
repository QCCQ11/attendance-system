package com.example.attendance.service.impl;

import com.example.attendance.dao.AttendanceRepository;
import com.example.attendance.entity.Attendance;
import com.example.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Page<Attendance> findByStudentId(String studentId, Specification<Attendance> spec, Pageable pageable) {
        Specification<Attendance> finalSpec = (root, query, cb) ->
                cb.and(cb.equal(root.get("studentId"), studentId), spec.toPredicate(root, query, cb));
        return attendanceRepository.findAll(finalSpec, pageable);
    }
}