/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Objects;
import model.Assignment;
import model.Course;
import model.Student;

/**
 *
 * @author Dell
 */
public class AssignmentPerStudentPerCourse {
    private Assignment assignment;
    private Student student;
    private Course course;
    
    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
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

    public AssignmentPerStudentPerCourse(Assignment assignment, Student student, Course course) {
        this.assignment = assignment;
        this.student = student;
        this.course = course;
    }

    public AssignmentPerStudentPerCourse() {
    }

    @Override
    public String toString() {
        return "AssignmentPerStudentPerCourse{" + "assignment=" + assignment + ", student=" + student + ", course=" + course + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.assignment);
        hash = 53 * hash + Objects.hashCode(this.student);
        hash = 53 * hash + Objects.hashCode(this.course);
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
        final AssignmentPerStudentPerCourse other = (AssignmentPerStudentPerCourse) obj;
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }
   

  
    
}
