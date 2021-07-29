package com.flipkart.restController;

import com.flipkart.bean.payment.*;
import com.flipkart.bean.user.Student;
import com.flipkart.bean.user.User;
import com.flipkart.business.user.StudentInterface;
import com.flipkart.business.user.StudentOperation;
//import com.flipkart.constant.PaymentMode;
import com.flipkart.constant.PaymentMode;
import com.flipkart.constant.UserRole;
import com.flipkart.crs.StudentCRS;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.ProfessorNotAddedException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import static com.flipkart.constant.PaymentMode.CASH;
//import static com.flipkart.constant.PaymentMode.CHEQUE;
import static com.flipkart.constant.PaymentMode.*;
import static com.flipkart.constant.ScanIO.cin;

/**
 *
 *  Class that displays Student Dashboard
 *
 */
@Path("/student")
public class StudentRestAPI {

    public static final StudentInterface studentOperation = new StudentOperation();
    private static final Logger LOG = Logger.getLogger(StudentCRS.class);


    /**
     * Method to view Grades.
     */
    @GET
    @Path("/viewGrades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Map<String,String> viewGrades(
            @NotNull
            @QueryParam("studentId") String studentId) {
                return studentOperation.viewGrades(studentId);
    }

    /**
     * Method to register courses for a student.
     * json file
     * {
     *     "studentId" : "S01",
     *     "choice_1" : "abc",
     *     "choice_2" : "abc",
     *     "choice_3" : "abc",
     *     "choice_4" : "abc",
     *     "choice_5" : "abc",
     *     "choice_6" : "abc"
     * }
     */
    @POST
    @Path("/courseRegistration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response courseRegistration(
            @NotNull
            @QueryParam("studentId") String studentId,
            @NotNull
            @QueryParam("choice_1") String choice_1,
            @NotNull
            @QueryParam("choice_2") String choice_2,
            @NotNull
            @QueryParam("choice_3") String choice_3,
            @NotNull
            @QueryParam("choice_4") String choice_4,
            @NotNull
            @QueryParam("choice_5") String choice_5,
            @NotNull
            @QueryParam("choice_6") String choice_6) throws ValidationException {
        Map<String, HashMap<String, String>> allCourses = viewAllCourses();
        List<String> courseChoices = new ArrayList<>();
        courseChoices.add(choice_1);
        courseChoices.add(choice_2);
        courseChoices.add(choice_3);
        courseChoices.add(choice_4);
        courseChoices.add(choice_5);
        courseChoices.add(choice_6);
        studentOperation.courseRegistration(studentId, courseChoices);
        return Response.status(201).entity("Choices Added!!").build();
    }

    /**
     * Method to get choice of courses.
     * @return courses.
     */
    private static String getCourseChoice(){
        return cin.next();
    }

    /**
     * Method to view registered courses for the student.
     */
    @GET
    @Path("/viewRegisteredCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> viewRegisteredCourses(
            @NotNull
            @QueryParam("studentId") String studentId) {
                return studentOperation.viewRegisteredCourses(studentId);
    }

    /**
     * Method to view all offered courses in the semester.
     */
    @GET
    @Path("/viewAllCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, HashMap<String, String>> viewAllCourses() {
        return studentOperation.viewAllCourses();
    }

    /**
     * Method for student Signup to the system.
     * json file
     * {
     *     "StudentId" : "S01",
     *     "Department" : "CSE",
     *     "Semester" : 6
     * }
     */
    @POST
    @Path("/studentSignUp")
    @Consumes("application/json")
    public Response studentSignUp(
            @NotNull
            @QueryParam("userId") String userId,
            @NotNull
            @QueryParam("studentId") String studentId,
            @NotNull
            @QueryParam("department") String department,
            @NotNull
            @QueryParam("semester") String semester
    ) throws ValidationException{
        Map<String, String> userDetails = UserRestAPI.userOperation.printUserDetails(userId);
        Student student = new Student(userDetails.get("userId"), userDetails.get("userName"), UserRole.STUDENT, "", department,
                studentId, Integer.valueOf(semester), Long.valueOf(userDetails.get("phoneNumber")), userDetails.get("emailAddress"));
        StudentDaoOperation.addStudentToSystem(student);
        return Response.status(201).entity("Student with studentId: " + userId + " has been added").build();
    }
}
