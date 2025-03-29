package com.endava.internship;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private final String name;
    private final LocalDate dateOfBirth;
    private final String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.dateOfBirth = Objects.requireNonNull(dateOfBirth, "Date of birth cannot be null");
        this.details = details == null ? "No details available" : details;
    }

    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getDetails() { return details; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(name, student.name) &&
                Objects.equals(dateOfBirth, student.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    @Override
    public String toString() {
        return String.format("Student[name=%s, dateOfBirth=%s, details=%s]", name, dateOfBirth, details);
    }
}