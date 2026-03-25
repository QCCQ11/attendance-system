package com.example.attendance.controller;

import com.example.attendance.entity.Student;
import com.example.attendance.service.StudentService;
import com.example.attendance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 新增学生接口
    @PostMapping("/create")
    public Result<String> createStudent(@RequestBody Student student) {
        String result = studentService.createStudent(student);
        return Result.success(result);
    }

    // 测试接口
    @GetMapping("/info")
    public String getStudentInfo() {
        return "姓名：王悦，学号：42411035，班级：计科3班";
    }

    @PostMapping("/attendance")
    public String attendance(@RequestBody String studentId) {
        return "学号为 " + studentId + " 的学生打卡成功！";
    }

    @GetMapping("/courses")
    public java.util.List<String> getCourseList() {
        return java.util.List.of("语文", "数学", "英语", "JavaEE 开发实践");
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable String id) {
        return "查询学号为 " + id + " 的学生信息";
    }

    @GetMapping("/search")
    public String searchStudent(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int page) {
        return "搜索学生姓名：" + name + "，页码：" + page;
    }

    @GetMapping("/info/{studentId}")
    public Result<Student> getStudentInfoById(@PathVariable String studentId) {
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

    @GetMapping("/list")
    public Result<java.util.List<Student>> getStudentList(
            @RequestParam String className,
            @RequestParam(defaultValue = "1") int page) {

        java.util.List<Student> students = new java.util.ArrayList<>();

        if ("计科3班".equals(className)) {
            students.add(new Student("王悦", "42411035", "计科3班"));
            students.add(new Student("张三", "2023001", "计科3班"));
            students.add(new Student("李四", "2023002", "计科3班"));
        } else if ("计科1班".equals(className)) {
            students.add(new Student("赵六", "2023005", "计科1班"));
            students.add(new Student("钱七", "2023006", "计科1班"));
        }
        return Result.success(students);
    }
}