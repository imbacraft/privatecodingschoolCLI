/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Objects;
import model.Course;
import model.Student;

/**
 *
 * @author Dell
 */
public class StudentPerCourse {
    private Student student;
    private Course course;

    public StudentPerCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public StudentPerCourse() {
    }

    @Override
    public String toString() {
        return "StudentPerCourse{" + "student=" + student + ", course=" + course + '}';
    }
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.student);
        hash = 41 * hash + Objects.hashCode(this.course);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentPerCourse other = (StudentPerCourse) obj;
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }
    
}
