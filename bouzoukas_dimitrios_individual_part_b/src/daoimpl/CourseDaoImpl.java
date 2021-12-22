/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import daoint.CourseDaoInt;
import java.sql.Connection;
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
import utils.Utils;
import dto.CourseDto;
import java.sql.Date;

/**
 *
 * @author Dell
 */
public class CourseDaoImpl implements CourseDaoInt {

    Scanner sc = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    @Override
    public List<Course> getCourseList() {

        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT course.*, CourseStream.*, CourseType.* "
                + "FROM Course, CourseStream, CourseType"
                + " WHERE course.stream = coursestream.streamid "
                + "AND course.type = coursetype.typeid "
                + "ORDER BY course.courseid";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
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
                Course c = new Course(courseId, title, cs, ct, startDate, endDate);

                courseList.add(c);
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
        return courseList;
    }

    @Override
    public void printCourseList() {
        List<Course> courseList = getCourseList();
        System.out.println("**********\nHere is the list of all the Courses:\n**********");
        for (Course c : courseList) {
            System.out.println(c.toString());
        }
    }

    @Override
    public void insertCourse(CourseDto c) {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "INSERT INTO Course VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        Date startDate;
        Date endDate;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, c.getCourseId());
            ps.setString(2, c.getTitle());
            ps.setInt(3, c.getStream());
            ps.setInt(4, c.getType());
            startDate = utils.convertLocalDateToDate(c.getStartDate());
            ps.setDate(5, startDate);
            endDate = utils.convertLocalDateToDate(c.getEndDate());
            ps.setDate(6, endDate);

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
    public CourseDto createCourse() {
        int courseId;
        String title;
        int stream;
        int type;
        LocalDate startDate = null;
        LocalDate endDate = null;
        boolean validDates = false;

        courseId = getNextCourseId();

        System.out.println("Please enter a Course Title.");
        title = sc.nextLine().trim();

        stream = askUserForCourseStream();

        type = askUserForCourseType();

        while (validDates == false) {
            startDate = askUserForCourseStartDate();
            endDate = askUserForCourseEndDate();

            if (validateCourseDates(startDate, endDate) == true) {

                validDates = true;

            } else {

                System.out.println("Sorry, either Start Date is after End Date or they are the same date. Try again!\n");
            }

        }

        CourseDto c = new CourseDto(courseId, title, stream, type, startDate, endDate);

        return c;

    }

    @Override
    public int askUserForCourseStream() {
        int streamAnswer;
        Integer streamId = null;

        while (true) {
            System.out.println("Please enter a Course stream. \n"
                    + "1.   C# \n"
                    + "2.   Java \n"
                    + "3.   Javascript \n"
                    + "4.   Python \n"
                    + "Enter one of the numbers 1-4 to choose Stream.");

            streamAnswer = sc.nextInt();
            if (streamAnswer > 0 && streamAnswer < 5) {

                streamId = streamAnswer;
                break;

            } else {
                System.out.println("Sorry! Wrong Course Stream Number!");
            }
        }

        return streamId;

    }

    @Override
    public int getNextCourseId() {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "SELECT MAX(CourseID) "
                + "FROM Course";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer courseId = null;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {

                courseId = rs.getInt(1) + 1;

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
        return courseId;
    }

    @Override
    public int askUserForCourseType() {
        int typeAnswer;
        Integer type = null;

        while (true) {
            System.out.println("Please enter a Course Type. \n"
                    + "10.  Full time \n"
                    + "11.  Part time \n"
                    + "Enter the numbers 10 or 11 to choose Type.");

            typeAnswer = sc.nextInt();

            if (typeAnswer > 9 && typeAnswer < 12) {

                type = typeAnswer;
                break;

            } else {
                System.out.println("Sorry! Wrong Course Type Number!");
            }
        }

        return type;

    }

    @Override
    public LocalDate askUserForCourseStartDate() {
        LocalDate startDate = null;
        String startDateString;
        boolean wrongFormat = true;

        while (wrongFormat == true) {
            System.out.println("Please enter a Course Start Date in exactly the following format: yy-mm-dd.");
            startDateString = sc2.nextLine().trim();

            try {
                startDate = LocalDate.parse(startDateString);
                wrongFormat = false;
            } catch (RuntimeException ex) {
                System.out.println("Sorry! Wrong date format!");
            }
        }
        return startDate;

    }

    @Override
    public LocalDate askUserForCourseEndDate() {
        LocalDate endDate = null;
        String endDateString;

        boolean wrongFormat = true;

        while (wrongFormat == true) {
            System.out.println("Please enter a Course End Date in exactly the following format: yy-mm-dd.");

            endDateString = sc2.nextLine().trim();

            try {
                endDate = LocalDate.parse(endDateString);
                wrongFormat = false;
            } catch (RuntimeException ex) {
                System.out.println("Sorry! Wrong date format!");

            }
        }
        return endDate;
    }

    @Override
    public boolean validateCourseDates(LocalDate startDate, LocalDate endDate) {
        boolean validDates;

        if (startDate.isBefore(endDate)) {

            validDates = true;

        } else {
            validDates = false;

        }
        return validDates;
    }
}
