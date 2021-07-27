package com.flipkart.crs;

import com.flipkart.bean.user.Student;
import com.flipkart.bean.user.User;
import org.apache.log4j.BasicConfigurator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.flipkart.constant.ScanIO.cin;

/**
 *
 *   Driver class for running CRS Applications.
 *
 */
public class CRSApplication {
    public static List<User> userRecords =  new ArrayList<User>();
    private static List<Student> studentRecords =  new ArrayList<Student>();
    public static HashMap<String, User> userHashMap = new HashMap<String, User>();

    public static void main(String[] args) {
        BasicConfigurator.configure();
        crsMenu();
    }

    /**
     * Method to print CRS Menu
     */
    public static void crsMenu(){
        String formatter = "\n\t";
        String welcomeMessage = "----- Welcome to Course Registration System -----" + formatter +
                "1. Login" + formatter + "2. Student Sign Up" + formatter + "3. Forgot Password?" + formatter +
                "4. Exit\n" + "-----------------------------------------------";
        while (true) {
            System.out.println(welcomeMessage);
            Integer input = cin.nextInt();
            makeDecision(input);
        }
    }

    /**
     * Method to choose options at the CRS Menu.
     * @param input
     */
    public static void makeDecision(Integer input){
        switch (input){
            case 1:
                UserCRS.login();
                break;
            case 2:
                StudentCRS.studentSignUp();
                break;
            case 3:
                UserCRS.forgotPassword();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Error, Make a valid choice!!!");
        }
    }
}

