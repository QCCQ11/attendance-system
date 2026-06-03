package com.example.attendance.controller;

import com.example.attendance.entity.User;
import com.example.attendance.service.UserService;
import com.example.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("title", "用户登录");
        if (error != null) {
            model.addAttribute("errorMsg", "用户名或密码错误");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        User user = userService.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return "redirect:/login?error=true";
        }
        session.setAttribute("loginUser", user);
        return "redirect:/student/list";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String role,
                           Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMsg", "两次输入的密码不一致");
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            return "register";
        }
        if (userService.findByUsername(username) != null) {
            model.addAttribute("errorMsg", "用户名已存在");
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name != null ? name : username);
        user.setRole(role != null ? role : "STUDENT");
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
