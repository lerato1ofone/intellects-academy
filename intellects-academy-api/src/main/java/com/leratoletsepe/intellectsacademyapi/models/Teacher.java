package com.leratoletsepe.intellectsacademyapi.models;

public class Teacher extends User {
    private String officeNumber;

    public Teacher(Integer userId, String title, String firstName, String lastName, String email, String password, Note[] notes, Course[] courses, String officeNumber) {
        super(userId, title, firstName, lastName, email, password, notes, courses);
        this.officeNumber = officeNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }
}