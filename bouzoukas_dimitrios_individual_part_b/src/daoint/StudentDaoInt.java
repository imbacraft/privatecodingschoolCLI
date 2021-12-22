/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoint;

import dto.StudentPerCourse;
import java.time.LocalDate;
import java.util.List;
import model.Student;

/**
 *
 * @author Dell
 */
public interface StudentDaoInt {

    public List<Student> getStudentList();

    public void printStudentList();

    public List<StudentPerCourse> getStudentPerCourseList();

    public void printStudentPerCourseList();

    public List<StudentPerCourse> getDoubleStudentList();

    public void printDoubleStudentList();

    public Student createStudent();

    public void insertStudent(Student st);

    public int getNextStudentId();

    public LocalDate askUserForDateOfBirth();

    public StudentPerCourse askUserToAssignStudent();

    public void assignStudentToCourse(StudentPerCourse spc);

    public boolean checkForDuplicateEntries(StudentPerCourse spc);

}
