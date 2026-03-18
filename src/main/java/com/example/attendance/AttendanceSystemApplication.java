package com.example.attendance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class AttendanceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceSystemApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello() {
        return "欢迎来到班级考勤管理系统！";
    }
    @GetMapping("/about")
    public String about() {
        return "我的名字是[王悦]，我的专业是[计算机科学与技术]";
    }
}
