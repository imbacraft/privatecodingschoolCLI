/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import daoint.CourseDaoInt;
import daoint.StudentDaoInt;
import dto.CourseDto;
import dto.StudentPerCourse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Course;
import model.CourseStream;
import model.CourseType;
import model.Student;
import utils.Utils;

/**
 *
 * @author Dell
 */
public class StudentDaoImpl implements StudentDaoInt {

    Scanner sc = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    @Override
    public List<Student> getStudentList() {
        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int studentId = rs.getInt("StudentID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String dateOfBirthString = rs.getString("DateOfBirth");
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString.trim());
                int tuitionFees = rs.getInt("TuitionFees");

                Student student = new Student(studentId, firstName, lastName, dateOfBirth, tuitionFees);

                studentList.add(student);
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
        return studentList;

    }

    @Override
    public List<StudentPerCourse> getStudentPerCourseList() {
        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<StudentPerCourse> studentPerCourseList = new ArrayList<>();
        String sql = "SELECT student.*, attends.*, course.*, courseStream.*, courseType.* "
                + "FROM Student, attends, Course, courseStream, courseType "
                + "WHERE student.studentid = attends.studentid "
                + "AND attends.courseid = course.courseid "
                + "AND course.stream = coursestream.streamid "
                + "AND course.type = coursetype.typeid "
                + "ORDER BY student.studentid";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int studentId = rs.getInt("StudentID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String dateOfBirthString = rs.getString("DateOfBirth");
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString.trim());
                int tuitionFees = rs.getInt("TuitionFees");

                Student student = new Student(studentId, firstName, lastName, dateOfBirth, tuitionFees);

                int courseId = rs.getInt("CourseID");
                String title = rs.getString("Title");
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
                Course course = new Course(courseId, title, cs, ct, startDate, endDate);

                StudentPerCourse spc = new StudentPerCourse(student, course);

                studentPerCourseList.add(spc);
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
        return studentPerCourseList;
    }

    @Override
    public List<StudentPerCourse> getDoubleStudentList() {
        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<StudentPerCourse> doubleStudentPerCourseList = new ArrayList<>();
        String sql = "SELECT student.*, attends.*, course.*, courseStream.*, courseType.* "
                + "FROM Student, attends, Course, courseStream, courseType "
                + "WHERE student.studentid = attends.studentid "
                + "AND attends.courseid = course.courseid "
                + "AND course.stream = coursestream.streamid "
                + "AND course.type = coursetype.typeid "
                + "GROUP BY attends.studentid "
                + "HAVING COUNT(attends.StudentID)>1;";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int studentId = rs.getInt("StudentID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String dateOfBirthString = rs.getString("DateOfBirth");
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString.trim());
                int tuitionFees = rs.getInt("TuitionFees");

                Student student = new Student(studentId, firstName, lastName, dateOfBirth, tuitionFees);

                int courseId = rs.getInt("CourseID");
                String title = rs.getString("Title");
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
                Course course = new Course(courseId, title, cs, ct, startDate, endDate);

                StudentPerCourse spc = new StudentPerCourse(student, course);

                doubleStudentPerCourseList.add(spc);
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
        return doubleStudentPerCourseList;

    }

    @Override
    public void printStudentList() {
        List<Student> studentList = getStudentList();
        System.out.println("**********\nHere is the list of the Students\n**********");
        for (Student st : studentList) {
            System.out.println(st.toString());
        }
    }

    @Override
    public void printStudentPerCourseList() {
        List<StudentPerCourse> studentPerCourseList = getStudentPerCourseList();
        System.out.println("**********\nHere is the list of the Students per Course:\n**********");
        for (StudentPerCourse spc : studentPerCourseList) {
            System.out.println(spc.toString());
        }
    }

    @Override
    public void printDoubleStudentList() {
        List<StudentPerCourse> doubleStudentList = getDoubleStudentList();
        System.out.println("**********\nHere is the list of the Students that belong to more than one course:\n**********");
        if (doubleStudentList.size() != 0) {
            for (StudentPerCourse dsl : doubleStudentList) {
                System.out.println(dsl.getStudent().toString());
            }
        } else {
            System.out.println("Sorry! There are no Students that belong to more than one course!");
        }
    }

    @Override
    public Student createStudent() {
        int studentId;
        String firstName;
        String lastName;
        LocalDate dateOfBirth = null;
        int tuitionFees;

        studentId = getNextStudentId();

        System.out.println("Please enter Student first name.");
        firstName = sc.next().trim();

        System.out.println("Please enter Student last name.");
        lastName = sc.next().trim();

        dateOfBirth = askUserForDateOfBirth();

        System.out.println("Please enter Student tuition fees in integer numbers. No decimal numbers allowed.");
        tuitionFees = sc.nextInt();

        Student st = new Student(studentId, firstName, lastName, dateOfBirth, tuitionFees);

        return st;
    }

    @Override
    public void insertStudent(Student st) {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "INSERT INTO Student VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        Date dateOfBirth;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, st.getStudentId());

            ps.setString(2, st.getFirstName());

            ps.setString(3, st.getLastName());

            dateOfBirth = utils.convertLocalDateToDate(st.getDateOfBirth());
            ps.setDate(4, dateOfBirth);

            ps.setInt(5, st.getTuitionFees());

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
    public int getNextStudentId() {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "SELECT MAX(StudentID) "
                + "FROM Student";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer studentId = null;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {

                studentId = rs.getInt(1) + 1;

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
        return studentId;
    }

    @Override
    public LocalDate askUserForDateOfBirth() {
        LocalDate dateOfBirth = null;
        String dateString;
        boolean wrongFormat = true;

        while (wrongFormat == true) {

            System.out.println("Please enter Student birth date in exactly the following format: yy-mm-dd.");
            dateString = sc2.nextLine().trim();

            try {
                dateOfBirth = LocalDate.parse(dateString);
                wrongFormat = false;

            } catch (RuntimeException ex) {
                System.out.println("Sorry! Wrong date format!");
            }
        }
        return dateOfBirth;
    }

    @Override
    public StudentPerCourse askUserToAssignStudent() {
        List<Student> studentList = getStudentList();
        CourseDaoInt coursedao = new CourseDaoImpl();
        List<Course> courseList = coursedao.getCourseList();
        StudentPerCourse spc = new StudentPerCourse();
        int studentAnswer;
        int courseAnswer;
        System.out.println("This is a list of the existing Students: \n ");

        for (int i = 0; i < studentList.size(); i++) {

            System.out.println((i + 1) + ".     " + studentList.get(i).toString());
        }

        System.out.println("\nEnter a number between 1 - " + studentList.size() + " to choose a Student to assign to a Course\n ");
        studentAnswer = sc.nextInt() - 1;

        System.out.println("This is a list of the existing Courses: \n ");

        for (int k = 0; k < courseList.size(); k++) {

            System.out.println((k + 1) + ".     " + courseList.get(k).toString());

        }
        System.out.println("\nEnter one of the course numbers between 1 - " + courseList.size() + " to assign Student: \n" + studentList.get(studentAnswer) + "\nto the desired Course.");

        courseAnswer = sc.nextInt() - 1;

        spc.setStudent(studentList.get(studentAnswer));
        spc.setCourse(courseList.get(courseAnswer));

        return spc;

    }

    @Override
    public void assignStudentToCourse(StudentPerCourse spc) {

        Connection con = null;
        Utils utils = new Utils();
        String sql = "UPDATE attends "
                + " SET attends.courseid = ? "
                + "WHERE attends.studentid = ?";

        PreparedStatement ps = null;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, spc.getCourse().getCourseId());

            ps.setInt(2, spc.getStudent().getStudentId());

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
    public boolean checkForDuplicateEntries(StudentPerCourse spc) {
        List<StudentPerCourse> studentPerCourseList = getStudentPerCourseList();
        boolean duplicateEntry = false;

        for (int i = 0; i < studentPerCourseList.size(); i++) {
            boolean check = studentPerCourseList.get(i).getCourse().getCourseId() == spc.getCourse().getCourseId();

            if (check == true) {
                return duplicateEntry = true;

            } else {
                return duplicateEntry = false;
            }

        }
        return duplicateEntry;

    }
}
