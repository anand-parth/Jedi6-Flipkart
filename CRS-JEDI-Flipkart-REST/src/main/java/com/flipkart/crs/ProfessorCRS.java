package com.flipkart.crs;

import com.flipkart.bean.user.Professor;
import com.flipkart.business.user.ProfessorInterface;
import com.flipkart.business.user.ProfessorOperation;
import com.flipkart.constant.Designation;
import com.flipkart.constant.UserRole;
import com.flipkart.exception.ProfessorNotAddedException;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.List;

import static com.flipkart.constant.ScanIO.cin;

/**
 *
 *  Class that displays Professor Dashboard
 *
 */
public class ProfessorCRS {

    public static final ProfessorInterface professorOperation = new ProfessorOperation();
    private static String professorId;
    private static final Logger LOG = Logger.getLogger(ProfessorCRS.class);

    /**
     * Method to display Professor dashboard
     * @param userId
     */
    public static void dashboard(String userId) {
        professorId = professorOperation.getProfessorIdFromDatabase(userId);
        String formatter = "\n\t";
        String dashBoardMessage = "--------------Professor Dashboard------------------" + formatter + "1. Teach Course"
                + formatter + "2. View Courses" + formatter + "3. Check Enrolled Students" + formatter + "4. Add Grade" + formatter + "5. Log out\n" +
                "-----------------------------------------------";
        while (true) {
            System.out.println(dashBoardMessage);
            Integer input = cin.nextInt();
            switch (input) {
                case 1:
                    teachCourse();
                    break;
                case 2:
                    viewCourses();
                    break;
                case 3:
                    viewEnrolledStudents();
                    break;
                case 4:
                    addGrade();
                    break;
                case 5:
                    return;
            }
        }
    }

    /**
     * Method to view Enrolled students in a course.
     * @return pair of course Names and the corresponding enrolled students.
     */
    private static Pair<String, List<String>> viewEnrolledStudents() {
        List<String> coursesList = viewCourses();
        System.out.print("Enter the course option for which you want see enrolled students: ");
        Integer courseChoice = cin.nextInt();
        List<String> enrolledStudentIdList = professorOperation.getEnrolledStudentsByCourse(coursesList.get(courseChoice));
        Integer i=0;
        for (String studentId : enrolledStudentIdList) {
            System.out.println("Student " + (++i));
            StudentCRS.studentOperation.displayStudent(studentId);
        }
        return new Pair<>(coursesList.get(courseChoice), enrolledStudentIdList);
    }

    private static void displayStudent(String studentId) {

    }

    /**
     * Method to mention course to be taught
     */
    private static void teachCourse() {
        StudentCRS.studentOperation.viewAllCourses();
        System.out.print("Enter courseId to teach course: ");
        String courseId = cin.next();
        try {
            professorOperation.addProfessorOnCourse(professorId, courseId);
        } catch (ProfessorNotAddedException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * Method to view all courses for the semester.
     * @return List of courses.
     */
    private static List<String> viewCourses() {
        List<String> coursesList = professorOperation.viewCoursesByProfessor(professorId);
        displayCourses(coursesList);
        return coursesList;
    }

    /**
     * Method to display Courses.
     * @param coursesList
     */
    private static void displayCourses(List<String> coursesList) {
        // We can fetch course details here
        int cnt = 0;
        for(String courseId: coursesList){
            System.out.print(++cnt + ". " +courseId+" ");
        }
    }

    /**
     * Method to add Grades for a course.
     */
    private static void addGrade() {
        Pair<String, List<String>> viewEnrolledStudents = viewEnrolledStudents();
        String courseId = viewEnrolledStudents.getKey();
        List<String> enrolledStudentIdList = viewEnrolledStudents.getValue();
        for (String studentId : enrolledStudentIdList) {
            StudentCRS.studentOperation.displayStudent(studentId);
            gradeStudent(studentId, courseId);
        }
    }

    /**
     * Method to grade student using the student ID and course ID.
     * @param studentId
     * @param courseId
     */
    private static void gradeStudent(String studentId, String courseId) {
        System.out.println("Enter Grade for " + courseId + ": ");
        String grade = cin.next();
        professorOperation.insertGradeInDatabase(studentId, courseId, grade);
        LOG.info("Student " + studentId + " for " + courseId + " graded successful");
    }

    public static Professor getProfessorInstance(String professorId, String department, Designation designation,
                                                 String userId, String name, UserRole role, String password, Long phoneNumber, String emailAddress) {
        return new Professor(professorId, department, designation, userId, name, role, password, phoneNumber, emailAddress);
    }
}
