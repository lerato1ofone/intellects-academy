package com.leratoletsepe.intellectsacademyapi.models;

import java.sql.Date;

public class Student extends User {
    private Date dob;
    private Double semesterMark;

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

    public Student(Integer userId, String title, String firstName, String lastName, String email, String password, Note[] notes, Course[] courses, Date dob, Double semesterMark) {
        super(userId, title, firstName, lastName, email, password, notes, courses);
        this.dob = dob;
        this.semesterMark = semesterMark;
    }
}