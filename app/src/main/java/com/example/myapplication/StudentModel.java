package com.example.myapplication;

public class StudentModel {

    private String studentId;
    private String studentName;
    private String email;
    private String phone;

    // ✅ Constructor kosong (dibutuhkan Firestore)
    public StudentModel() {}

    // ✅ Constructor lengkap
    public StudentModel(String studentId, String studentName, String email, String phone) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.phone = phone;
    }

    // ✅ Getter & Setter
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
