/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import model.Assignment;
import model.Course;

/**
 *
 * @author Dell
 */
public class AssignmentPerCourse {
    private Assignment assignment;
    private Course course;

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public AssignmentPerCourse(Assignment assignment, Course course) {
        this.assignment = assignment;
        this.course = course;
    }

    public AssignmentPerCourse() {
    }

    @Override
    public String toString() {
        return "AssignmentPerCourse {" + "assignment=" + assignment + ", course=" + course + '}';
    }
    
}
