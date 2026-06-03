package com.example.attendance.controller;

import com.example.attendance.entity.Student;
import com.example.attendance.service.StudentService;
import com.example.attendance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String sort,
                       @RequestParam(required = false) String direction,
                       Model model) {
        List<Student> students;
        if ("studentId".equals(sort)) {
            if ("asc".equalsIgnoreCase(direction)) {
                students = studentService.findAllOrderByStudentIdAsc();
            } else {
                students = studentService.findAllOrderByStudentIdDesc();
            }
        } else if ("name".equals(sort)) {
            if ("asc".equalsIgnoreCase(direction)) {
                students = studentService.findAllOrderByNameAsc();
            } else {
                students = studentService.findAllOrderByNameDesc();
            }
        } else {
            if ("desc".equalsIgnoreCase(direction)) {
                students = studentService.findAllOrderByIdDesc();
            } else {
                students = studentService.findAllOrderByIdAsc();
            }
        }
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        if (student.getId() == null) {
            studentService.save(student);
        } else {
            studentService.update(student);
        }
        return "redirect:/student/list";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Long id) {
        studentService.deleteById(id);
        return Result.success("删除成功");
    }

    @DeleteMapping("/batchDelete")
    @ResponseBody
    public Result<String> batchDelete(@RequestParam String ids) {
        String[] idArray = ids.split(",");
        for (String idStr : idArray) {
            studentService.deleteById(Long.parseLong(idStr));
        }
        return Result.success("删除成功");
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result<Student> findById(@PathVariable Long id) {
        return Result.success(studentService.findById(id));
    }

    @GetMapping("/studentId/{studentId}")
    @ResponseBody
    public Result<Student> findByStudentId(@PathVariable String studentId) {
        return Result.success(studentService.findByStudentId(studentId));
    }

    @GetMapping("/api/list")
    @ResponseBody
    public Result<List<Student>> findAllApi() {
        return Result.success(studentService.findAll());
    }

    @GetMapping("/class/{className}")
    @ResponseBody
    public Result<List<Student>> findByClassName(@PathVariable String className) {
        return Result.success(studentService.findByClassName(className));
    }
}