package com.leratoletsepe.intellectsacademyapi.models;

import com.leratoletsepe.intellectsacademyapi.models.dto.enums.UserType.UserRole;

import java.sql.Date;
import java.util.List;

public class User {
    private Integer userId;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
    private Date dob;
    private Double semesterMark;
    private String officeNumber;
    private byte[] avatar;
    private List<Note> notes;
    private List<Course> courses;

    public User(Integer userId, String title, String firstName, String lastName, String email, String password, UserRole role, Date dob, Double semesterMark, String officeNumber, byte[] avatar, List<Note> notes, List<Course> courses) {
        this.userId = userId;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dob = dob;
        this.semesterMark = semesterMark;
        this.officeNumber = officeNumber;
        this.avatar = avatar;
        this.notes = notes;
        this.courses = courses;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Double getSemesterMark() {
        return semesterMark;
    }

    public void setSemesterMark(Double semesterMark) {
        this.semesterMark = semesterMark;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public UserRole getUserType() {
        return role;
    }

    public void setUserType(UserRole userType) {
        this.role = userType;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}