package com.example.attendance;

public class Attendance {
    private String studentId;      // 学号
    private String date;           // 考勤日期
    private String status;         // 考勤状态（正常/迟到/缺勤等）

    // 无参构造（必须）
    public Attendance() {}

    // 有参构造（方便使用）
    public Attendance(String studentId, String date, String status) {
        this.studentId = studentId;
        this.date = date;
        this.status = status;
    }

    // Getter 和 Setter（必须！）
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
