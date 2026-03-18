package com.example.attendance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.attendance.Result;
import com.example.attendance.Student;
import java.util.List;
import java.util.ArrayList;
import com.example.attendance.Attendance;

@RestController
public class StudentController {

    @GetMapping("/student/info")
    public String getStudentInfo() {
        return "姓名：王悦，学号：42411035，班级：计科3班";
    }

    @PostMapping("/student/attendance")
    public String attendance(@RequestBody String studentId) {
        return "学号为 " + studentId + " 的学生打卡成功！";
    }

    @GetMapping("/student/courses")
    public List<String> getCourseList() {
        return List.of("语文", "数学", "英语", "JavaEE 开发实践");
    }

    @GetMapping("/student/{id}")
    public String getStudentById(@PathVariable String id) {
        return "查询学号为 " + id + " 的学生信息";
    }

    @GetMapping("/student/search")
    public String searchStudent(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int page) {
        return "搜索学生姓名: " + name + ", 页码: " + page;
    }

    @GetMapping("/student/info/{studentId}")
    public Result<Student> getStudentInfoById(@PathVariable String studentId) {
        // 这里只是演示，实际应该从数据库查询
        // 我们暂时用 if-else 模拟不同学号返回不同学生
        if ("42411035".equals(studentId)) {
            Student student = new Student("王悦", "42411035", "计科3班");
            return Result.success(student);
        } else if ("2023001".equals(studentId)) {
            Student student = new Student("张三", "2023001", "软件1班");
            return Result.success(student);
        } else {
            return Result.error("未找到学号为 " + studentId + " 的学生");
        }
    }

    @GetMapping("/student/list")
    public Result<List<Student>> getStudentList(
            @RequestParam String className,
            @RequestParam(defaultValue = "1") int page) {

        // 这里只是演示，实际应该从数据库查询
        // 我们暂时用 if-else 模拟不同班级返回不同学生列表
        List<Student> students = new ArrayList<>();

        if ("计科3班".equals(className)) {
            students.add(new Student("王悦", "42411035", "计科3班"));
            students.add(new Student("张三", "2023001", "计科3班"));
            students.add(new Student("李四", "2023002", "计科3班"));
        } else if ("计科1班".equals(className)) {
            students.add(new Student("赵六", "2023005", "计科1班"));
            students.add(new Student("钱七", "2023006", "计科1班"));
        } else {
            // 如果班级不存在，返回空列表
            return Result.success(students);
        }

        // 可以在这里根据 page 做分页处理，但先简单返回所有
        return Result.success(students);
    }

    @PostMapping("/attendance/update")
    public Result<String> updateAttendance(@RequestBody Attendance attendance) {
        // 这里只是演示，实际应该更新数据库
        System.out.println("更新考勤记录：");
        System.out.println("学号：" + attendance.getStudentId());
        System.out.println("日期：" + attendance.getDate());
        System.out.println("状态：" + attendance.getStatus());

        return Result.success("考勤记录更新成功，学号：" + attendance.getStudentId());
    }
}
