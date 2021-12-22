/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Utils {

    Scanner sc2 = new Scanner(System.in);
    //localhost = database IP
    // privateschool = mySQL Workbench Schema name

    private static final String mySQLUrl = "jdbc:mysql://localhost:3306/privateschool?"
            + "zeroDateTimeBehavior=CONVERT_TO_NULL"
            + "&useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC"
            + "&allowPublicKeyRetrieval=true"
            + "&useSSL=false";
    private static String username = "root";
    private static String password = "gnosis451622";

    public static Connection getConnection() throws SQLException {
        Connection con = null;

        con = DriverManager.getConnection(mySQLUrl, username, password);

        return con;

    }

    public Date convertLocalDateToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public Timestamp convertLocalDateTimeToDate(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    public boolean askUserToPrintAnotherList() {
        boolean retry = true;
        String retryAnswer;
        System.out.println("\nDo you want to print another list? (yes / no)");
        retryAnswer = sc2.nextLine();
        if (retryAnswer.equalsIgnoreCase("no")) {
            retry = false;
        }
        return retry;

    }

    public boolean askUserToInsertMoreData() {
        boolean retry = true;
        String retryAnswer;
        System.out.println("\nDo you want to insert more data? (yes / no)");
        retryAnswer = sc2.nextLine();
        if (retryAnswer.equalsIgnoreCase("no")) {
            retry = false;
        }
        return retry;

    }
}
