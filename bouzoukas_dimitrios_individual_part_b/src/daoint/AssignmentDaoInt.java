/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoint;

import dto.AssignmentPerCourse;
import dto.AssignmentPerStudentPerCourse;
import java.time.LocalDateTime;
import java.util.List;
import model.Assignment;

/**
 *
 * @author Dell
 */
public interface AssignmentDaoInt {

    public List<Assignment> getAssignmentList();

    public void printAssignmentList();

    public List<AssignmentPerCourse> getAssignmentPerCourseList();

    public void printAssignmentPerCourseList();

    public List<AssignmentPerStudentPerCourse> getAssignmentPerStudentPerCourseList();

    public void printAssignmentPerStudentPerCourseList();

    public int getNextAssignmentId();

    public Assignment createAssignment();

    public void insertAssignment(Assignment as);

    public LocalDateTime askUserForAssignmentDateTime();

    public AssignmentPerStudentPerCourse askUserToAssignAssignment();

    public void assignAssignmentToStudentPerCourse(AssignmentPerStudentPerCourse apspc);

    public boolean checkForDuplicateEntries(AssignmentPerStudentPerCourse apspc);

}
