/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoint;

import dto.CourseDto;
import java.time.LocalDate;
import java.util.List;
import model.Course;

/**
 *
 * @author Dell
 */
public interface CourseDaoInt {
    public List<Course> getCourseList();
    
    public void printCourseList();
    
    public CourseDto createCourse();
    
    public int askUserForCourseStream();
    
    public int askUserForCourseType();
    
    public LocalDate askUserForCourseStartDate();
    
    public LocalDate askUserForCourseEndDate();
    
    public boolean validateCourseDates(LocalDate startDate, LocalDate endDate);
    
    public void insertCourse(CourseDto c);
    
    public int getNextCourseId();
    
}
