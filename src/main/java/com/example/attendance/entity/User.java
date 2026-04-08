package com.example.attendance.entity;

public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String studentId;
    private String teacherId;
    private String email;
    private String phone;
    private Integer status;
    private String lastLogin;

    // 无参构造
    public User() {}

    // 有参构造（可选）
    public User(Long id, String username, String password, String role, String name,
                String studentId, String teacherId, String email, String phone,
                Integer status, String lastLogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.lastLogin = lastLogin;
    }

    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getLastLogin() { return lastLogin; }
    public void setLastLogin(String lastLogin) { this.lastLogin = lastLogin; }
}