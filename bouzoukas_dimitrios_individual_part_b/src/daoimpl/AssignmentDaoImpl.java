/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import daoint.AssignmentDaoInt;
import daoint.StudentDaoInt;
import dto.AssignmentPerCourse;
import dto.AssignmentPerStudentPerCourse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Assignment;
import model.Course;
import model.CourseStream;
import model.CourseType;
import model.Student;
import utils.Utils;

/**
 *
 * @author Dell
 */
public class AssignmentDaoImpl implements AssignmentDaoInt {

    Scanner sc = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    @Override
    public List<Assignment> getAssignmentList() {
        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<Assignment> assignmentList = new ArrayList<>();
        String sql = "SELECT * FROM Assignment";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int assignmentId = rs.getInt("AssignmentID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String submissionString = rs.getString("SubmissionDateTime");
                String[] splitSub = submissionString.split(" ");
                String submissionParseReady = splitSub[0].trim() + "T" + splitSub[1].trim();

                LocalDateTime submissionDateTime = LocalDateTime.parse(submissionParseReady);
                int oralMark = rs.getInt("Oral Mark");
                int totalMark = rs.getInt("Total Mark");

                Assignment assig = new Assignment(assignmentId, title, description, submissionDateTime, oralMark, totalMark);

                assignmentList.add(assig);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                st.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return assignmentList;

    }

    @Override
    public List<AssignmentPerCourse> getAssignmentPerCourseList() {
        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<AssignmentPerCourse> assignmentPerCourseList = new ArrayList<>();
        String sql = "SELECT Assignment.*, belongs.*, course.*, coursestream.*, coursetype.* "
                + "FROM Assignment, belongs, course, coursestream, coursetype "
                + "WHERE assignment.assignmentid = belongs.assignmentid "
                + "AND belongs.courseid = course.courseid "
                + "AND course.stream = coursestream.streamid "
                + "AND course.type = coursetype.typeid "
                + "ORDER BY assignment.assignmentid;";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int assignmentId = rs.getInt("AssignmentID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String submissionString = rs.getString("SubmissionDateTime");
                String[] splitSub = submissionString.split(" ");
                String submissionParseReady = splitSub[0].trim() + "T" + splitSub[1].trim();
                LocalDateTime submissionDateTime = LocalDateTime.parse(submissionParseReady);
                int oralMark = rs.getInt("Oral Mark");
                int totalMark = rs.getInt("Total Mark");

                Assignment assig = new Assignment(assignmentId, title, description, submissionDateTime, oralMark, totalMark);

                int courseId = rs.getInt("CourseID");
                String courseTitle = rs.getString("Title");
                int streamId = rs.getInt("StreamId");
                String streamName = rs.getString("StreamName");
                int typeId = rs.getInt("TypeId");
                String typeName = rs.getString("TypeName");
                String startDateString = rs.getString("StartDate");
                LocalDate startDate = LocalDate.parse(startDateString.trim());
                String endDateString = rs.getString("EndDate");
                LocalDate endDate = LocalDate.parse(endDateString.trim());

                CourseStream cs = new CourseStream(streamId, streamName);
                CourseType ct = new CourseType(typeId, typeName);
                Course course = new Course(courseId, courseTitle, cs, ct, startDate, endDate);

                AssignmentPerCourse apc = new AssignmentPerCourse(assig, course);

                assignmentPerCourseList.add(apc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                st.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return assignmentPerCourseList;

    }

    @Override
    public List<AssignmentPerStudentPerCourse> getAssignmentPerStudentPerCourseList() {
        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<AssignmentPerStudentPerCourse> assignmentPerStudentPerCourseList = new ArrayList<>();

        String sql = "SELECT Assignment.*, student.*, course.*, coursestream.*, coursetype.* "
                + "FROM Assignment, hasToSubmit, student, course, coursestream, coursetype "
                + "WHERE assignment.assignmentid = hasToSubmit.assignmentid "
                + "AND hasToSubmit.courseid = course.courseid "
                + "AND hasToSubmit.studentid = student.studentid "
                + "AND course.stream = coursestream.streamid "
                + "AND course.type = coursetype.typeid "
                + "ORDER BY hasToSubmit.assignmentid";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int assignmentId = rs.getInt("AssignmentID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String submissionString = rs.getString("SubmissionDateTime");
                String[] splitSub = submissionString.split(" ");
                String submissionParseReady = splitSub[0].trim() + "T" + splitSub[1].trim();
                LocalDateTime submissionDateTime = LocalDateTime.parse(submissionParseReady);
                int oralMark = rs.getInt("Oral Mark");
                int totalMark = rs.getInt("Total Mark");

                Assignment assig = new Assignment(assignmentId, title, description, submissionDateTime, oralMark, totalMark);

                int studentId = rs.getInt("StudentID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String dateOfBirthString = rs.getString("DateOfBirth");
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString.trim());
                int tuitionFees = rs.getInt("TuitionFees");

                Student student = new Student(studentId, firstName, lastName, dateOfBirth, tuitionFees);

                int courseId = rs.getInt("CourseID");
                String courseTitle = rs.getString("Title");
                int streamId = rs.getInt("StreamId");
                String streamName = rs.getString("StreamName");
                int typeId = rs.getInt("TypeId");
                String typeName = rs.getString("TypeName");
                String startDateString = rs.getString("StartDate");
                LocalDate startDate = LocalDate.parse(startDateString.trim());
                String endDateString = rs.getString("EndDate");
                LocalDate endDate = LocalDate.parse(endDateString.trim());

                CourseStream cs = new CourseStream(streamId, streamName);
                CourseType ct = new CourseType(typeId, typeName);
                Course course = new Course(courseId, courseTitle, cs, ct, startDate, endDate);

                AssignmentPerStudentPerCourse apspc = new AssignmentPerStudentPerCourse(assig, student, course);

                assignmentPerStudentPerCourseList.add(apspc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                st.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return assignmentPerStudentPerCourseList;
    }

    @Override
    public void printAssignmentList() {
        List<Assignment> assignmentList = getAssignmentList();
        System.out.println("**********\nHere is the list of all the Assignments:\n**********");
        for (Assignment a : assignmentList) {
            System.out.println(a.toString());
        }
    }

    @Override
    public void printAssignmentPerCourseList() {
        List<AssignmentPerCourse> assignmentPerCourseList = getAssignmentPerCourseList();
        System.out.println("**********\nHere is the list of the Assignments per Course:\n**********");
        for (AssignmentPerCourse apc : assignmentPerCourseList) {
            System.out.println(apc.toString());
        }
    }

    @Override
    public void printAssignmentPerStudentPerCourseList() {
        List<AssignmentPerStudentPerCourse> assignmentPerStudentPerCourseList = getAssignmentPerStudentPerCourseList();
        System.out.println("**********\nHere is the list of the Assignments per Student per Course:\n**********");
        for (AssignmentPerStudentPerCourse apspc : assignmentPerStudentPerCourseList) {
            System.out.println(apspc.toString());
        }
    }

    @Override
    public int getNextAssignmentId() {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "SELECT MAX(AssignmentID) "
                + "FROM Assignment";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer assignmentId = null;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {

                assignmentId = rs.getInt(1) + 1;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return assignmentId;
    }

    @Override
    public Assignment createAssignment() {
        int assignmentId;
        String title;
        String description;
        LocalDateTime subDateTime = null;
        int oralMark = 20;
        int totalMark = 80;

        assignmentId = getNextAssignmentId();

        System.out.println("Please enter the Assignment's title.");
        title = sc.next().trim();

        System.out.println("Please enter the Assignment's description.");
        description = sc.next().trim();

        subDateTime = askUserForAssignmentDateTime();

        Assignment as = new Assignment(assignmentId, title, description, subDateTime, oralMark, totalMark);

        return as;

    }

    @Override
    public void insertAssignment(Assignment as) {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "INSERT INTO Assignment VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        Timestamp subDate;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, as.getAssignmentID());

            ps.setString(2, as.getTitle());

            ps.setString(3, as.getDescription());

            subDate = utils.convertLocalDateTimeToDate(as.getSubDateTime());
            ps.setTimestamp(4, subDate);

            ps.setInt(5, as.getOralMark());

            ps.setInt(6, as.getTotalMark());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }

    @Override
    public LocalDateTime askUserForAssignmentDateTime() {
        LocalDateTime dateTime = null;
        String dateString;
        boolean wrongFormat = true;

        while (wrongFormat == true) {
            System.out.println("Please enter the Assignment's submission date and time in exactly the following format: yy-mm-ddThh:mm:ss."
                    + "\nExample: 2020-10-12T23:59:59");
            dateString = sc2.nextLine().trim();

            try {
                dateTime = LocalDateTime.parse(dateString);
                wrongFormat = false;
            } catch (RuntimeException ex) {
                System.out.println("Sorry! Wrong date format!");
            }
        }
        return dateTime;

    }

    @Override
    public void assignAssignmentToStudentPerCourse(AssignmentPerStudentPerCourse apspc) {
        Connection con = null;
        Utils utils = new Utils();

        String sql = "INSERT INTO hastosubmit "
                + " VALUES (?,?,?) ";
        PreparedStatement ps = null;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, apspc.getAssignment().getAssignmentID());
            ps.setInt(2, apspc.getCourse().getCourseId());
            ps.setInt(3, apspc.getStudent().getStudentId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }

    @Override
    public AssignmentPerStudentPerCourse askUserToAssignAssignment() {
        List<AssignmentPerCourse> assignmentPerCourseList = getAssignmentPerCourseList();
        StudentDaoInt studentdao = new StudentDaoImpl();
        List<Student> studentList = studentdao.getStudentList();
        AssignmentPerStudentPerCourse apspc = new AssignmentPerStudentPerCourse();
        int assignmentAnswer;
        int studentAnswer;

        System.out.println("This is a list of the existing Assignments per Course: \n ");

        for (int i = 0; i < assignmentPerCourseList.size(); i++) {

            System.out.println((i + 1) + ".     " + assignmentPerCourseList.get(i).toString());
        }

        System.out.println("\nEnter a number between 1 - " + assignmentPerCourseList.size() + " to choose an Assignment to assign to a Student.\n ");
        assignmentAnswer = sc.nextInt() - 1;

        System.out.println("This is a list of the existing Students: \n ");

        for (int k = 0; k < studentList.size(); k++) {

            System.out.println((k + 1) + ".     " + studentList.get(k).toString());

        }
        System.out.println("\nEnter one of the course numbers between 1 - " + studentList.size() + " to assign the Assignment: \n"
                + assignmentPerCourseList.get(assignmentAnswer) + "\nto the desired Student.");

        studentAnswer = sc.nextInt() - 1;

        apspc.setAssignment(assignmentPerCourseList.get(assignmentAnswer).getAssignment());
        apspc.setStudent(studentList.get(studentAnswer));
        apspc.setCourse(assignmentPerCourseList.get(assignmentAnswer).getCourse());

        return apspc;
    }

    @Override
    public boolean checkForDuplicateEntries(AssignmentPerStudentPerCourse apspc) {
        List<AssignmentPerStudentPerCourse> assignmentPerStudentPerCourseList = getAssignmentPerStudentPerCourseList();
        boolean duplicateEntry = true;

        for (int i = 0; i < assignmentPerStudentPerCourseList.size(); i++) {
            boolean check = assignmentPerStudentPerCourseList.get(i).getAssignment().getAssignmentID() == apspc.getAssignment().getAssignmentID()
                    && assignmentPerStudentPerCourseList.get(i).getStudent().getStudentId() == apspc.getStudent().getStudentId();
                    
            if (check == true) {
                return duplicateEntry = true;

            } else {
                return duplicateEntry = false;
            }

    }
        return duplicateEntry;
}
}