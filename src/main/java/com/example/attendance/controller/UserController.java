package com.example.attendance.controller;

import com.example.attendance.entity.User;
import com.example.attendance.service.UserService;
import com.example.attendance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 查询所有用户
    @GetMapping("/list")
    public Result<List<User>> findAll() {
        return Result.success(userService.findAll());
    }

    // 根据ID查询
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Long id) {
        return Result.success(userService.findById(id));
    }

    // 新增用户
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody User user) {
        userService.insert(user);
        return Result.success("添加成功");
    }

    // 更新用户
    @PutMapping("/update")
    public Result<String> update(@RequestBody User user) {
        userService.update(user);
        return Result.success("更新成功");
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public Result<String> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return Result.success("删除成功");
    }
}