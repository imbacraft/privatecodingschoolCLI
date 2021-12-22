/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import daoint.CourseDaoInt;
import daoint.TrainerDaoInt;
import dto.TrainerPerCourse;
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
import model.Trainer;
import utils.Utils;

/**
 *
 * @author Dell
 */
public class TrainerDaoImpl implements TrainerDaoInt {

    Scanner sc = new Scanner(System.in);

    @Override
    public List<Trainer> getTrainerList() {
        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<Trainer> trainerList = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM Trainer";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int trainerId = rs.getInt("TrainerID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                Trainer trainer = new Trainer(trainerId, firstName, lastName);

                trainerList.add(trainer);
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
        return trainerList;
    }

    @Override
    public List<TrainerPerCourse> getTrainerPerCourseList() {
        Connection con = null;
        Utils utils = new Utils();
        Statement st = null;
        ResultSet rs = null;
        List<TrainerPerCourse> trainerPerCourseList = new ArrayList<>();
        String sql = "SELECT Trainer.*, teaches.*, Course.*,CourseStream.*,CourseType.* "
                + "FROM Trainer, teaches, Course,CourseStream,CourseType "
                + "WHERE teaches.courseid = course.courseId "
                + "AND teaches.trainerid = trainer.trainerid "
                + "AND course.stream = coursestream.streamid "
                + "AND course.type = coursetype.typeid "
                + "ORDER BY trainer.trainerid";

        try {
            con = utils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int trainerId = rs.getInt("TrainerID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
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
                Trainer trainer = new Trainer(trainerId, firstName, lastName);

                TrainerPerCourse tpc = new TrainerPerCourse(trainer, c);

                trainerPerCourseList.add(tpc);
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
        return trainerPerCourseList;

    }

    @Override
    public void printTrainerList() {
        List<Trainer> trainerList = getTrainerList();
        System.out.println("**********\nHere is the list of all the Trainers:\n**********");
        for (Trainer tr : trainerList) {
            System.out.println(tr.toString());
        }
    }

    @Override
    public void printTrainerPerCourseList() {
        List<TrainerPerCourse> trainerPerCourseList = getTrainerPerCourseList();
        System.out.println("**********\nHere is the list of the Trainers per Course:\n**********");
        for (TrainerPerCourse tpc : trainerPerCourseList) {
            System.out.println(tpc.toString());
        }
    }

    @Override
    public Trainer createTrainer() {
        int trainerId;
        String firstName;
        String lastName;

        trainerId = getNextTrainerId();

        System.out.println("Please enter Trainer's first name.");
        firstName = sc.next().trim();

        System.out.println("Please enter Trainer's last name.");
        lastName = sc.next().trim();

        Trainer tr = new Trainer(trainerId, firstName, lastName);

        return tr;

    }

    @Override
    public int getNextTrainerId() {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "SELECT MAX(TrainerID) "
                + "FROM Trainer";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer trainerId = null;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {

                trainerId = rs.getInt(1) + 1;

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
        return trainerId;
    }

    @Override
    public void insertTrainer(Trainer tr) {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "INSERT INTO Trainer VALUES (?,?,?)";
        PreparedStatement ps = null;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, tr.getTrainerId());
            ps.setString(2, tr.getFirstName());
            ps.setString(3, tr.getLastName());

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
    public void assignTrainerToCourse(TrainerPerCourse tpc) {
        Connection con = null;
        Utils utils = new Utils();
        String sql = "UPDATE teaches "
                + " SET teaches.courseid = ? "
                + "WHERE teaches.trainerid = ?";

        PreparedStatement ps = null;

        try {
            con = utils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, tpc.getCourse().getCourseId());

            ps.setInt(2, tpc.getTrainer().getTrainerId());

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
    public TrainerPerCourse askUserToAssignTrainer() {
        List<Trainer> trainerList = getTrainerList();
        CourseDaoInt coursedao = new CourseDaoImpl();
        List<Course> courseList = coursedao.getCourseList();
        TrainerPerCourse tpc = new TrainerPerCourse();
        int trainerAnswer;
        int courseAnswer;

        System.out.println("This is a list of the existing Trainers: \n ");

        for (int i = 0; i < trainerList.size(); i++) {

            System.out.println((i + 1) + ".     " + trainerList.get(i).toString());
        }

        System.out.println("\nEnter a number between 1 - " + trainerList.size() + " to choose a Trainer to assign to a Course\n ");
        trainerAnswer = sc.nextInt() - 1;

        System.out.println("This is a list of the existing Courses: \n ");

        for (int k = 0; k < courseList.size(); k++) {

            System.out.println((k + 1) + ".     " + courseList.get(k).toString());

        }
        System.out.println("\nEnter one of the course numbers between 1 - " + courseList.size() + " to assign Trainer: \n" + trainerList.get(trainerAnswer) + "\nto the desired Course.");

        courseAnswer = sc.nextInt() - 1;

        tpc.setTrainer(trainerList.get(trainerAnswer));
        tpc.setCourse(courseList.get(courseAnswer));

        return tpc;
    }

    @Override
    public boolean checkForDuplicateEntries(TrainerPerCourse tpc) {
        List<TrainerPerCourse> trainerPerCourseList = getTrainerPerCourseList();
        boolean duplicateEntry = false;

        for (int i = 0; i < trainerPerCourseList.size(); i++) {
            boolean check = trainerPerCourseList.get(i).getCourse().getCourseId() == tpc.getCourse().getCourseId();

            if (check == true) {
                return duplicateEntry = true;

            } else {
                return duplicateEntry = false;
            }

        }
        return duplicateEntry;

    }
}
