package com.flipkart.crs;

import com.flipkart.bean.academics.Course;
import com.flipkart.bean.user.Professor;
import com.flipkart.bean.user.User;
import com.flipkart.business.user.AdminInterface;
import com.flipkart.business.user.AdminOperation;
import com.flipkart.constant.Designation;
import com.flipkart.constant.UserRole;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import static com.flipkart.constant.Designation.*;
import static com.flipkart.constant.ScanIO.cin;

/**
 *
 *  Class that displays Admin Dashboard
 *
 */
public class AdminCRS {

    private static final AdminInterface adminOperation = new AdminOperation();
    private static final Logger LOG = Logger.getLogger(AdminCRS.class);

    /**
     * Method to create Admin menu
     * @param userId
     */
    public static void dashboard(String userId) {
        String formatter = "\n\t";
        String dashBoardMessage = "--------------Admin Dashboard------------------" + formatter + "1. Add Professor"
                + formatter + "2. Approve Student" + formatter + "3. Add Course" + formatter + "4. Edit Course" +
                formatter + "5. Generate Grade Card" + formatter + "6. List Users" + formatter + "7. Delete User" + formatter + "8. Log out\n" +
                "-----------------------------------------------";
        while (true) {
            System.out.println(dashBoardMessage);
            Integer input = cin.nextInt();
            switch (input) {
                case 1:
                    addProfessor();
                    break;
                case 2:
                    approveStudent();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    editCourse();
                    break;
                case 5:
                    generateGradeCard();
                    break;
                case 6:
                    listUsers();
                    break;
                case 7:
                    deleteUser();
                    break;
                case 8:
                    return;
            }
        }
    }

    /**
     * Method to add Course.
     */
    private static void addCourse() {
        System.out.print("Enter courseId: ");
        String courseId = cin.next();
        System.out.print("Enter courseName: ");
        String courseName = cin.next();
        Course course = new Course(courseId, courseName);
        try {
            adminOperation.addCourse(course);
        }
        catch (CourseFoundException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * Method to edit Course details.
     */
    private static void editCourse() {
        StudentCRS.studentOperation.viewAllCourses();
        System.out.print("Enter courseId: ");
        String courseId = cin.next();
        Map<String, String> courseDetails = adminOperation.fetchCourseDetails(courseId);
        System.out.print("Enter new courseName(enter NA to not change): ");
        String courseName = cin.next();
        System.out.print("Enter new courseFaculty(enter NA to not change): ");
        String courseFaculty = cin.next();
        adminOperation.updateCourseDetails(courseId, !courseName.equalsIgnoreCase("na") ? courseName : courseDetails.get("courseName"),
                !courseFaculty.equalsIgnoreCase("na") ? courseFaculty : courseDetails.get("courseFaculty"));
        LOG.info("Course details for " + courseId + " updated successfully!!");
    }

    /**
     * Method to approve Student.
     */
    private static void approveStudent() {
        System.out.println("--------------Approve Student----------------------");
        List<String> pendingApprovals = adminOperation.viewPendingApprovals();
        if (pendingApprovals.isEmpty()) {
            System.out.println("Congrats! No pending approvals");
            return;
        }
        Integer i=0;
        for (String studentId : pendingApprovals) {
            System.out.println("Student " + (++i));
            StudentCRS.studentOperation.displayStudent(studentId);
            System.out.print("\n\n");
        }
        while (true) {
            System.out.print("Enter Student IDs to approve (to quit enter EXIT): ");
            String studentId = cin.next();
            if (studentId.equalsIgnoreCase("exit")) break;
            adminOperation.approveStudent(studentId);
            LOG.info("Student with studentId " + studentId + " approved successfully!!");
        }
    }

    /**
     * Method to generate Grades
     */
    private static void generateGradeCard() {
        System.out.println("--------------Generate Grade Card----------------------");
        System.out.println("Enter studentId: ");
        String studentId = cin.next();
        adminOperation.generateGradeCard(studentId);
    }

    /**
     * Method to add professor to system.
     */
    private static void addProfessor() {
        System.out.println("--------------Add Professor----------------------");
        User user = UserCRS.userSignUp(UserRole.PROFESSOR);
        System.out.print("\n\n\n");
        System.out.println("---------------Academic Information-------------");
        String professorId = ProfessorCRS.professorOperation.generateProfessorId();
        System.out.println("ProfessorId: " + professorId);
        System.out.print("Department: ");
        String department = cin.next();
        System.out.println("Specify the designation according to the following: ");
        System.out.println(LECTURER.toInt() + ". " + LECTURER.toString() + " " + ASSISTANT_PROFESSOR.toInt() + ". " + ASSISTANT_PROFESSOR.toString() +
                " " + ASSOCIATE_PROFESSOR.toInt() + ". " + ASSOCIATE_PROFESSOR.toString() + " " + PROFESSOR.toInt() + ". " + PROFESSOR.toString() +
                " " + HOD.toInt() + ". " + HOD.toString());
        System.out.print("Designation: ");
        Integer designation = cin.nextInt();
        Professor professor = ProfessorCRS.professorOperation.addProfessorToSystem(ProfessorCRS.getProfessorInstance(professorId, department,
                Designation.getDesignation(designation), user.getloginId(), user.getName(), user.getRole(), user.getPassword(),
                user.getPhoneNumber(), user.getEmailAddress()));
        LOG.info("Professor with professorId "+professorId+ " successfully added!!");
    }

    /**
     * Method to delete a user.
     */
    private static void deleteUser() {
        System.out.println("--------------Delete User----------------------");
        System.out.print("Enter User ID: ");
        String deleteUserId = cin.next();
        adminOperation.deleteUser(deleteUserId);
    }


    /**
     * Method to list all users in the system.
     */
    private static void listUsers() {
        List<String> userRecords = UserCRS.userOperation.getAllUserIdList();
        for (String userId : userRecords) {
            System.out.println("-----------------------------------------------");
            UserCRS.userOperation.printUserDetails(userId);
            System.out.println("-----------------------------------------------");
        }
    }
}