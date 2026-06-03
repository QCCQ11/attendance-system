package com.example.attendance.controller;

import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.Course;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private CourseService courseService;

    private String getCurrentStudentId(HttpSession session) {
        Object user = session.getAttribute("loginUser");
        if (user instanceof com.example.attendance.entity.User) {
            return ((com.example.attendance.entity.User) user).getStudentId();
        }
        return null;
    }

    private String getCurrentStudentName(HttpSession session) {
        Object user = session.getAttribute("loginUser");
        if (user instanceof com.example.attendance.entity.User) {
            return ((com.example.attendance.entity.User) user).getName();
        }
        return "学生";
    }

    @GetMapping("/checkIn")
    public String checkInPage(Model model, HttpSession session) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "attendance-checkin";
    }

    @PostMapping("/checkIn")
    public String checkIn(@RequestParam Integer courseId,
                          @RequestParam(required = false) String remark,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        String studentId = getCurrentStudentId(session);
        String studentName = getCurrentStudentName(session);

        if (studentId == null || studentId.isEmpty()) {
            studentId = "2024001";
            studentName = "测试学生";
        }

        Course course = courseService.findById(courseId);
        if (course == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "打卡失败：未找到该课程！");
            return "redirect:/attendance/checkIn";
        }

        Attendance attendance = new Attendance();
        attendance.setStudentId(studentId);
        attendance.setStudentName(studentName);
        attendance.setCourseId(courseId);
        attendance.setCourseName(course.getCourseName());
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setRecordTime(LocalDateTime.now());
        attendance.setRemark(remark);
        attendance.setCreateTime(LocalDateTime.now());

        // 判断打卡状态
        LocalTime now = LocalTime.now();
        LocalTime classStartTime = LocalTime.parse("08:00");
        LocalTime classEndTime = LocalTime.parse("17:00");

        if (now.isAfter(classStartTime.plusMinutes(30))) {
            attendance.setStatus("ABSENT");
            redirectAttributes.addFlashAttribute("successMsg", "打卡成功！状态：缺勤（迟到超过30分钟）");
        } else if (now.isAfter(classStartTime)) {
            attendance.setStatus("LATE");
            redirectAttributes.addFlashAttribute("successMsg", "打卡成功！状态：迟到");
        } else if (now.isBefore(classEndTime.minusHours(1))) {
            attendance.setStatus("EARLY");
            redirectAttributes.addFlashAttribute("successMsg", "打卡成功！状态：早退");
        } else {
            attendance.setStatus("NORMAL");
            redirectAttributes.addFlashAttribute("successMsg", "打卡成功！状态：正常");
        }

        attendanceService.save(attendance);
        return "redirect:/attendance/list";
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String startDate,
                       @RequestParam(required = false) String endDate,
                       @RequestParam(required = false) String status,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,
                       HttpSession session,
                       Model model) {
        String studentId = getCurrentStudentId(session);
        if (studentId == null || studentId.isEmpty()) {
            studentId = "2024001";
        }

        final String finalStudentId = studentId;
        final String finalStartDate = startDate;
        final String finalEndDate = endDate;
        final String finalStatus = status;

        Specification<Attendance> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("studentId"), finalStudentId));

            if (finalStartDate != null && !finalStartDate.isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("attendanceDate"), LocalDate.parse(finalStartDate)));
            }
            if (finalEndDate != null && !finalEndDate.isEmpty()) {
                predicates.add(cb.lessThanOrEqualTo(root.get("attendanceDate"), LocalDate.parse(finalEndDate)));
            }
            if (finalStatus != null && !finalStatus.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), finalStatus));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Attendance> attendancePage = attendanceService.findByStudentId(finalStudentId, spec, pageable);

        model.addAttribute("records", attendancePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", attendancePage.getTotalPages());
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("status", status);

        return "attendance-list";
    }
}