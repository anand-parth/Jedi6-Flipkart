package com.flipkart.crs;

import com.flipkart.bean.user.User;
import com.flipkart.business.user.UserInterface;
import com.flipkart.business.user.UserOperation;
import com.flipkart.constant.UserRole;
import javafx.util.Pair;

import static com.flipkart.constant.ScanIO.cin;

/**
 *
 *  Class that displays User Dashboard
 *
 */

public class UserCRS {

    public static final UserInterface userOperation = new UserOperation();

    /**
     * Method to sign up user corresponding to the User Role.
     * @param role
     * @return User object.
     */
    public static User userSignUp(UserRole role) {
        System.out.print("Name: ");
        String name = cin.next();
        System.out.print("UserID: ");
        String userId = cin.next();
        System.out.print("Password: ");
        String password = cin.next();
        System.out.print("Mobile Number: ");
        Long phoneNumber = cin.nextLong();
        System.out.print("Email Address: ");
        String emailAddress = cin.next();
        return userOperation.addUserToSystem(getUserInstance(userId, name, role, password, phoneNumber, emailAddress));
    }

    /**
     * Method to update password.
     */
    public static void forgotPassword() {
        System.out.println("------------------Update Password--------------------");
        System.out.print("UserID: ");
        String userId = cin.next();
        System.out.print("Email Address: ");
        String emailAddress = cin.next();
        Integer OTP = userOperation.sendOTPToMail(emailAddress);
        System.out.println(OTP);
        System.out.print("Enter OTP: ");
        if (verifyOTP(OTP)) {
            System.out.println("Enter new Password: ");
            String password = cin.next();
            userOperation.updatePassword(userId, password);
        }
    }

    /**
     * Method to verify OTP sent to registered email ID.
     * @param OTP
     * @return boolean denoting if OTP is verified.
     */
    private static boolean verifyOTP(Integer OTP) {
        Integer inputOTP = cin.nextInt();
        return inputOTP.equals(OTP);
    }

    /**
     * Method to login into the user dashboard.
     */
    public static void login() {
        String formatter = "\n\t";
        System.out.print("----- Login Menu -----");
        System.out.print(formatter + "UserID: ");
        String userId = cin.next();
        System.out.print("\tPassword: ");
        String password = cin.next();
        Pair<Boolean, UserRole> userCredentials = userOperation.verifyCredentials(userId, password);
        Boolean isLoggedIn = userCredentials.getKey();
        if (isLoggedIn) {
            System.out.println("Logged in successfully!!!!");
            System.out.println("Redirecting to the Dashboard..........");
            UserRole userRole = userCredentials.getValue();
            switch (userRole) {
                case STUDENT:
                    StudentCRS.dashboard(userId);
                    break;
                case ADMIN:
                    AdminCRS.dashboard(userId);
                    break;
                case PROFESSOR:
                    ProfessorCRS.dashboard(userId);
                    break;
                default:
                    throw new IllegalArgumentException("User Role " + userRole + " not recognized. " +
                            "Inconvenience is regretted ");
            }
        } else {
            System.out.println("Login Failed!!!");
        }
    }

    public static User getUserInstance(String userId, String name, UserRole role, String password, Long phoneNumber, String emailAddress) {
        return new User(userId, name, role, password, phoneNumber, emailAddress);
    }
}
