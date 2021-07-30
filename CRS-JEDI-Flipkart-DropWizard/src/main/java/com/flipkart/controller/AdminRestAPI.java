package com.flipkart.controller;

import com.flipkart.bean.academics.Course;
import com.flipkart.bean.user.Professor;
import com.flipkart.business.user.*;
import com.flipkart.constant.Designation;
import com.flipkart.constant.UserRole;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to call admin functions using REST requests
 */


@Path("/admin")
public class AdminRestAPI {
    private static final AdminInterface adminOperation = new AdminOperation();
    private static final ProfessorInterface professorOperation = new ProfessorOperation();
    private static final UserInterface userOperation = new UserOperation();

    /**
     * @param courseName: String courseName
     * @param courseId    : String courseId
     *                    REST Service to add new course to calatog
     * @return
     */

    @POST
    @Path("/addCourse")
    @Produces(APPLICATION_JSON)
    public Response addCourse(
            @NotNull
            @QueryParam("courseId") String courseId,
            @NotNull
            @QueryParam("courseName") String courseName) {
        try {
            Course course = new Course(courseId, courseName);
            adminOperation.addCourse(course);
            return Response.status(201).entity("Course : " + courseId + " code added to catalog successfully").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


    /**
     * @param courseName:    String courseName
     * @param courseFaculty: String courseFaculty
     * @param courseId:      String courseID
     *                       REST Service to edit course details in calatog
     * @return
     */

    @PUT
    @Path("/editCourse/{courseId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response editCourse(
            @NotNull
            @QueryParam("courseName") String courseName,
            @QueryParam("courseFaculty") String courseFaculty,
            @PathParam("courseId") String courseId) {
        try {
            adminOperation.updateCourseDetails(courseId, courseName, courseFaculty);
            return Response.status(201).entity("Details for course with courseId: " + courseId + " have been updated").build();
        } catch (Exception e) {
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }
    }


    /**
     * REST Service to view list of students with pending approvals
     *
     * @return List of students not approved
     */

    @GET
    @Path("/viewPendingApprovals")
    @Produces(APPLICATION_JSON)
    public Response viewPendingApprovals() {
        try {
            List<String> approvals = adminOperation.viewPendingApprovals();
            return Response.status(201).entity("Unapproved students: \n" + approvals).build();
        } catch (Exception e) {
            return Response.status(500).entity("Something went wrong! Please try again").build();
        }
    }


    /**
     * @param studentId: String studentID to approve
     *                   REST Service to approve a student by admin
     * @return
     */

    @PUT
    @Path("/approveStudent/{studentId}")
    @Produces(APPLICATION_JSON)
    public Response approveStudent(
            @NotNull
            @PathParam("studentId") String studentId) {
        try {
            adminOperation.approveStudent(studentId);
            return Response.status(201).entity("Student with studentId: " + studentId + " is approved").build();
        } catch (Exception e) {
            return Response.status(500).entity("Something went wrong! Please try again").build();
        }
    }


    /**
     * @param studentId: String studentID for whom to generate grade-card
     *                   REST Service to generate grade-card of given studentID
     * @return
     */

    @PUT
    @Path("/generateGradeCard/{studentId}")
    @Produces(APPLICATION_JSON)
    public Response generateGradeCard(
            @NotNull
            @PathParam("studentId") String studentId) {
        try {
            adminOperation.generateGradeCard(studentId);
            return Response.status(201).entity("Student with studentId: " + studentId + " has his grade-card generated").build();
        } catch (Exception e) {
            return Response.status(500).entity("Something went wrong! Please try again").build();
        }
    }


    /**
     * @param professor: Professor object to be added to database
     *                   REST Service to add new professor to database
     * @return
     */

    @POST
    @Path("/addProfessor")
    @Produces(APPLICATION_JSON)
    public Response addProfessorToSystem(
            @NotNull
            @QueryParam("userId") String userId,
            @NotNull
            @QueryParam("professorId") String professorId,
            @NotNull
            @QueryParam("department") String department,
            @NotNull
            @QueryParam("designation") String designation) {
        try {
            Map<String, String> userDetails = UserRestAPI.userOperation.printUserDetails(userId);
            Professor professor = new Professor(professorId, department, Designation.getDesignation(Integer.valueOf(designation)),
                    userId, userDetails.get("userName"), UserRole.PROFESSOR, "", Long.valueOf(userDetails.get("phoneNumber")),
                    userDetails.get("emailAddress"));
            professorOperation.addProfessorToSystem(professor);
            return Response.status(201).entity("Professor with professorId " + professorId + " has been added").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


    /**
     * @param deleteUserId: String UserID to be delete
     *                      REST Service to delete user from database
     * @return
     */

    @DELETE
    @Path("/deleteUser/{deleteUserId}")
    @Produces(APPLICATION_JSON)
    public Response deleteUser(
            @NotNull
            @PathParam("deleteUserId") String deleteUserId) {
        try {
            adminOperation.deleteUser(deleteUserId);
            return Response.status(201).entity("User with UserId: " + deleteUserId + " has been removed from system").build();

        } catch (Exception e) {
            return Response.status(500).entity("Something went wrong! Please try again").build();
        }
    }

    /**
     * REST Service to list all users in database
     *
     * @return List of users in database
     */

    @GET
    @Path("/listUsers")
    @Produces(APPLICATION_JSON)
    public Response listUsers() {
        try {
            List<String> users = userOperation.getAllUserIdList();
            StringBuilder sb = new StringBuilder();
            Integer i = 1;
            for (String userId : users) {
                Map<String, String> userDetails = userOperation.printUserDetails(userId);
                userDetails.put("userRole", UserRole.getRole(Integer.valueOf(userDetails.get("userRole"))).toString());
                sb.append("User ").append(i).append(": {");
                userDetails.forEach((key, value) -> sb.append(key + " : " + value + ", "));
                sb.replace(sb.length() - 2, sb.length(), "}\n");
                ++i;
            }
            return Response.status(201).entity("Users in the system: " + sb).build();
        } catch (Exception e) {
            return Response.status(500).entity("Something went wrong! Please try again").build();
        }
    }
}