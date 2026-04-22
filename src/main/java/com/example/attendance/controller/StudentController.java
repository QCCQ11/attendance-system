package com.example.attendance.controller;

import com.example.attendance.entity.Student;
import com.example.attendance.service.StudentService;
import com.example.attendance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/insert")
    public Result<Student> insert(@RequestBody Student student) {
        return Result.success(studentService.save(student));
    }

    @GetMapping("/{id}")
    public Result<Student> findById(@PathVariable Long id) {
        return Result.success(studentService.findById(id));
    }

    @GetMapping("/studentId/{studentId}")
    public Result<Student> findByStudentId(@PathVariable String studentId) {
        return Result.success(studentService.findByStudentId(studentId));
    }

    @GetMapping("/list")
    public Result<List<Student>> findAll() {
        return Result.success(studentService.findAll());
    }

    @GetMapping("/class/{className}")
    public Result<List<Student>> findByClassName(@PathVariable String className) {
        return Result.success(studentService.findByClassName(className));
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return Result.success("删除成功");
    }
}