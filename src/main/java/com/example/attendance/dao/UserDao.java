package com.example.attendance.dao;

import com.example.attendance.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 查询所有用户
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    // 根据ID查询
    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    // 插入用户
    public void insert(User user) {
        String sql = "INSERT INTO user (username, password, role, name, student_id, teacher_id, email, phone, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getName(),
                user.getStudentId(),
                user.getTeacherId(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus());
    }

    // 更新用户
    public void update(User user) {
        String sql = "UPDATE user SET password = ?, name = ?, role = ?, email = ?, phone = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                user.getPassword(),
                user.getName(),
                user.getRole(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus(),
                user.getId());
    }

    // 删除用户
    public void deleteById(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}