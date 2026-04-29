package com.example.attendance.service;

import com.example.attendance.entity.User;
import java.util.List;

public interface UserService {
    User findByUsername(String username);
    User findById(Long id);
    List<User> findAll();
    void insert(User user);
    void update(User user);
    void deleteById(Long id);
    User register(User user);
}