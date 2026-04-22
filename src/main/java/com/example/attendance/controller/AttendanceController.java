package com.example.attendance.controller;

import com.example.attendance.dao.AttendanceRepository;
import com.example.attendance.entity.Attendance;
import com.example.attendance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // 分页查询所有考勤记录（支持排序）
    @GetMapping("/list")
    public Result<Page<Attendance>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "attendanceDate") String sortField,
            @RequestParam(defaultValue = "desc") String sortDirection) {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Attendance> result = attendanceRepository.findAll(pageable);
        return Result.success(result);
    }

    // 多条件动态查询（支持分页和排序）
    @GetMapping("/search")
    public Result<Page<Attendance>> search(
            @RequestParam(required = false) String studentId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "attendanceDate") String sortField,
            @RequestParam(defaultValue = "desc") String sortDirection) {

        // 构建排序
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        // 动态条件查询
        Specification<Attendance> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (studentId != null && !studentId.isEmpty()) {
                predicates.add(cb.equal(root.get("studentId"), studentId));
            }
            if (status != null && !status.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (startDate != null && endDate != null) {
                predicates.add(cb.between(root.get("attendanceDate"), startDate, endDate));
            } else if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("attendanceDate"), startDate));
            } else if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("attendanceDate"), endDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Attendance> result = attendanceRepository.findAll(spec, pageable);
        return Result.success(result);
    }
}