/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import daoimpl.*;
import daoint.*;
import dto.*;
import java.util.List;
import java.util.Scanner;
import model.*;
import utils.Utils;

/**
 *
 * @author Dell
 */
public class Menu {

    //Two Scanner objects because of Scanner malfunctions.
    public static Scanner sc = new Scanner(System.in);
    public static Scanner sc2 = new Scanner(System.in);

    static StudentDaoInt studentdao = new StudentDaoImpl();
    static TrainerDaoInt trainerdao = new TrainerDaoImpl();
    static AssignmentDaoInt assignmentdao = new AssignmentDaoImpl();
    static CourseDaoInt coursedao = new CourseDaoImpl();
    static Utils utils = new Utils();

    public static void mainMenu() {
        int mainAnswer;
        String repeatAnswer;
        boolean exitMainLoop = false;

        System.out.println("Welcome! In this program, we will simulate aspects of the design of a private coding school structure. \n");

        while (exitMainLoop == false) {

            System.out.println("The program connects to the local database and has the following functionality: \n"
                    + "1.   Print lists of data from the database.\n"
                    + "2.   Insert new data to the database.\n"
                    + "3.   Exit program.\n"
                    + "Type a number between 1-3 to choose the next step.");

            mainAnswer = sc.nextInt();

            if (mainAnswer == 1) {

                printListsMenu();

                System.out.println("Do you want to exit program? (yes / no)");
                repeatAnswer = sc2.nextLine();

                if (repeatAnswer.equalsIgnoreCase("yes")) {

                    System.out.println("Ok! Sad to see you go!");
                    exitMainLoop = true;

                }
            } else if (mainAnswer == 2) {

                System.out.println("Ok then! Let's start!");

                insertDataMenu();

                System.out.println("Do you want to exit program? (yes / no)");
                repeatAnswer = sc2.nextLine();

                if (repeatAnswer.equalsIgnoreCase("yes")) {
                    exitMainLoop = true;
                }

            } else if (mainAnswer == 3) {

                System.out.println("Ok! Sad to see you go!");
                exitMainLoop = true;

            } else {
                System.out.println("Sorry! Invalid answer! Try again!");
            }

        }

    }

    public static void printListsMenu() {
        int menuAnswer;
        boolean retry = true;

        while (retry == true) {

            System.out.println("Type a number between 1 - 9 to get: \n"
                    + "1.   A list of all the students with their details. \n"
                    + "2.   A list of all the trainers and their details. \n"
                    + "3.   A list of all the assignments and their details. \n"
                    + "4.   A list of all the courses and their details. \n"
                    + "5.   A list of all the students per course and their details. \n"
                    + "6.   A list of all the trainers per course and their details. \n"
                    + "7.   A list of all the assignments per course and their details. \n"
                    + "8.   A list of all the assignments per student per course and their details. \n"
                    + "9.   A list of students that belong to more than one course.\n");

            menuAnswer = sc.nextInt();

            switch (menuAnswer) {
                case 1:
                    studentdao.printStudentList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                case 2:
                    trainerdao.printTrainerList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                case 3:
                    assignmentdao.printAssignmentList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                case 4:
                    coursedao.printCourseList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                case 5:
                    studentdao.printStudentPerCourseList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                case 6:
                    trainerdao.printTrainerPerCourseList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                case 7:
                    assignmentdao.printAssignmentPerCourseList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                case 8:
                    assignmentdao.printAssignmentPerStudentPerCourseList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                case 9:
                    studentdao.printDoubleStudentList();
                    retry = utils.askUserToPrintAnotherList();
                    break;
                default:
                    System.out.println("Sorry! Invalid number!");
                    break;
            }
        }

    }

    public static void insertDataMenu() {
        int menuAnswer;
        boolean retry = true;
        boolean duplicateEntries;
        while (retry == true) {

            System.out.println("Type a number between 1 - 7 to: \n"
                    + "1.   Insert a new Course. \n"
                    + "2.   Insert a new Trainer. \n"
                    + "3.   Insert a new Assignment. \n"
                    + "4.   Insert a new Student. \n"
                    + "5.   Assign a Student to a Course. \n"
                    + "6.   Assign a Trainer to a Course. \n"
                    + "7.   Assign an Assignment to a Student per Course. \n");

            menuAnswer = sc.nextInt();

            switch (menuAnswer) {
                case 1:
                    CourseDto c = coursedao.createCourse();

                    coursedao.insertCourse(c);

                    retry = utils.askUserToInsertMoreData();
                    break;
                case 2:
                    Trainer tr = trainerdao.createTrainer();

                    trainerdao.insertTrainer(tr);

                    retry = utils.askUserToInsertMoreData();
                    break;
                case 3:
                    Assignment as = assignmentdao.createAssignment();

                    assignmentdao.insertAssignment(as);

                    retry = utils.askUserToInsertMoreData();
                    break;
                case 4:
                    Student st = studentdao.createStudent();

                    studentdao.insertStudent(st);

                    retry = utils.askUserToInsertMoreData();
                    break;
                case 5:
                    StudentPerCourse spc = studentdao.askUserToAssignStudent();
                    duplicateEntries = studentdao.checkForDuplicateEntries(spc);

                    if (duplicateEntries == false) {
                        studentdao.assignStudentToCourse(spc);
                    } else {
                        System.out.println("Sorry! This Student is already assigned to this Course! Try another!");
                    }
                    retry = utils.askUserToInsertMoreData();
                    break;
                case 6:
                    TrainerPerCourse tpc = trainerdao.askUserToAssignTrainer();
                    duplicateEntries = trainerdao.checkForDuplicateEntries(tpc);

                    if (duplicateEntries == false) {
                        trainerdao.assignTrainerToCourse(tpc);
                    } else {
                        System.out.println("Sorry! This Trainer is already assigned to this Course! Try another!");
                    }

                    retry = utils.askUserToInsertMoreData();
                    break;
                case 7:
                    AssignmentPerStudentPerCourse apspc = assignmentdao.askUserToAssignAssignment();
                    duplicateEntries = assignmentdao.checkForDuplicateEntries(apspc);

                    if (duplicateEntries == false) {
                        assignmentdao.assignAssignmentToStudentPerCourse(apspc);
                    } else {
                        System.out.println("Sorry! This Assignment is already assigned to this Student Per Course Course! Try another!");
                    }

                    retry = utils.askUserToInsertMoreData();
                    break;
                default:
                    System.out.println("Sorry! Invalid number!");
                    break;
            }
        }

    }
}
