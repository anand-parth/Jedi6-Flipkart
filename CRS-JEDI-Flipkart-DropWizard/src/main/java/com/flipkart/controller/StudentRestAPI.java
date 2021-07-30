package com.flipkart.controller;

import com.flipkart.bean.user.Student;
import com.flipkart.business.user.StudentInterface;
import com.flipkart.business.user.StudentOperation;
import com.flipkart.constant.UserRole;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


/**
 * @author JEDI-6-Flipkart-G1
 * Class to call Student functions using REST requests
 */

@Path("/student")
public class StudentRestAPI {

    public static final StudentInterface studentOperation = new StudentOperation();
    private static final Logger LOG = Logger.getLogger(StudentRestAPI.class);

    /**
     * Method to view Grades.
     *
     * @param studentId
     * @return
     */
    @GET
    @Path("/viewGrades")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Map<String, String> viewGrades(
            @NotNull
            @QueryParam("studentId") String studentId) {
        try {
            return studentOperation.viewGrades(studentId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to register courses for a student.
     *
     * @param studentId
     * @param choice_1
     * @param choice_2
     * @param choice_3
     * @param choice_4
     * @param choice_5
     * @param choice_6
     * @return
     */
    @POST
    @Path("/courseRegistration")
    @Consumes(APPLICATION_JSON)
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
            @QueryParam("choice_6") String choice_6) {
        try {
            List<String> courseChoices = new ArrayList<>();
            courseChoices.add(choice_1);
            courseChoices.add(choice_2);
            courseChoices.add(choice_3);
            courseChoices.add(choice_4);
            courseChoices.add(choice_5);
            courseChoices.add(choice_6);
            StringBuilder sb = new StringBuilder();
            Integer i = 1;
            for (String choice : courseChoices) {
                sb.append("Choice ").append(i).append(": ").append(choice).append("\n");
                ++i;
            }
            studentOperation.courseRegistration(studentId, courseChoices);
            return Response.status(201).entity("Choices Added!!\n" + sb).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Method to view registered courses for the student.
     *
     * @param studentId
     * @return
     */
    @GET
    @Path("/viewRegisteredCourses")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public List<String> viewRegisteredCourses(
            @NotNull
            @QueryParam("studentId") String studentId) {
        try {
            return studentOperation.viewRegisteredCourses(studentId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to view all offered courses in the semester.
     *
     * @return
     */
    @GET
    @Path("/viewAllCourses")
    @Produces(APPLICATION_JSON)
    public Map<String, HashMap<String, String>> viewAllCourses() {
        try {
            return studentOperation.viewAllCourses();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method for student Signup to the system.
     *
     * @param userId
     * @param studentId
     * @param department
     * @param semester
     * @return
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
    ) {
        try {
            Map<String, String> userDetails = UserRestAPI.userOperation.printUserDetails(userId);
            Student student = new Student(userDetails.get("userId"), userDetails.get("userName"), UserRole.STUDENT, "", department,
                    studentId, Integer.valueOf(semester), Long.valueOf(userDetails.get("phoneNumber")), userDetails.get("emailAddress"));
            studentOperation.addStudentToSystem(student);
            return Response.status(201).entity("Student with studentId: " + userId + " has been added").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
