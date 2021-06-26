package com.leratoletsepe.intellectsacademyapi.models.dto;

import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.models.Note;
import com.leratoletsepe.intellectsacademyapi.models.enums.UserType;

import java.sql.Date;
import java.util.List;

public class UserProfileDto {
    private Integer userId;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private UserType.UserRole role;
    private Date dob;
    private Double semesterMark;
    private String officeNumber;
    private byte[] avatar;
    private List<Note> notes;
    private List<Course> courses;

    public UserProfileDto(Integer userId, String title, String firstName, String lastName, String email, UserType.UserRole role, Date dob, Double semesterMark, String officeNumber, byte[] avatar, List<Note> notes, List<Course> courses) {
        this.userId = userId;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public UserType.UserRole getUserRole() {
        return role;
    }

    public void setUserRole(UserType.UserRole userRole) {
        this.role = userRole;
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