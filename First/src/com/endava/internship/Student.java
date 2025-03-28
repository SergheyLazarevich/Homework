package com.endava.internship;

import java.time.LocalDate;
import java.util.Objects;

public class Student {


    private String name;
    private LocalDate dateOfBirth;
    private String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getDetails() { return details; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return name != null && name.equals(student.name) &&
               dateOfBirth != null && dateOfBirth.equals(student.dateOfBirth) ;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", dateOfBirth=" + dateOfBirth + ", details=";
    }


}
