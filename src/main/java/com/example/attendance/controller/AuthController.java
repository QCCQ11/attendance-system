package com.example.attendance.controller;

import com.example.attendance.entity.User;
import com.example.attendance.service.UserService;
import com.example.attendance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UserService userService;

    // 注册接口
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return Result.error("用户名已存在");
        }
        userService.register(user);
        return Result.success("注册成功");
    }

    // 登录接口（带 Session 保存）
    @PostMapping("/login")
    public Result<String> login(@RequestBody User loginUser, HttpSession session) {
        User user = userService.findByUsername(loginUser.getUsername());
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (!user.getPassword().equals(loginUser.getPassword())) {
            return Result.error("密码错误");
        }
        session.setAttribute("loginUser", user);
        return Result.success("登录成功");
    }
}