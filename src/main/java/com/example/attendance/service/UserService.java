package com.example.attendance.service;

import com.example.attendance.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void insert(User user);
    void update(User user);
    void deleteById(Long id);
}