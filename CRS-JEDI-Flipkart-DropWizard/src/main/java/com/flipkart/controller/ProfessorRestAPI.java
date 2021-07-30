package com.flipkart.controller;

import com.flipkart.bean.user.Professor;
import com.flipkart.business.user.ProfessorInterface;
import com.flipkart.business.user.ProfessorOperation;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to call Professor functions using REST requests
 */

@Path("/professor")
public class ProfessorRestAPI {
    private static final ProfessorInterface professorOperation = new ProfessorOperation();

    /**
     * POST Method to add Professor to the system
     *
     * @param professor
     * @return Response object for REST API
     */
    @POST
    @Path("/addProfessor")
    @Consumes(APPLICATION_JSON)
    public Response addProfessorToSystem(Professor professor) {
        try {
            Professor professorResult = professorOperation.addProfessorToSystem(professor);
            return Response.status(201).entity("Professor with" + professor.getProfessorId() + " added in the system: ").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * GET Method to get ProfessorId from system
     *
     * @param userId
     * @return Response object for REST API
     */
    @GET
    @Path("/getProfessorId")
    @Consumes(APPLICATION_JSON)
    public Response getProfessorId(String userId) {
        try {
            String professorId = professorOperation.getProfessorIdFromDatabase(userId);
            return Response.status(201).entity("Professor with" + professorId).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * GET Method to get List of courses taught by Prof for the semester.
     *
     * @param professorId
     * @return List of courses
     */
    @GET
    @Path("/viewCourses")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public List<String> getCourses(
            @NotNull
            @QueryParam("professorId") String professorId) {

        List<String> courses = new ArrayList<String>();
        try {
            courses = professorOperation.viewCoursesByProfessor(professorId);
        } catch (Exception ex) {
            return null;
        }
        return courses;
    }

    /**
     * GET Method to view Enrolled Students for a course
     *
     * @param professorId
     * @return List of Strings with students.
     */
    @GET
    @Path("/viewEnrolledStudent")
    @Produces(APPLICATION_JSON)
    public List<String> viewEnrolledStudents(
            @NotNull
            @QueryParam("professorId") String professorId) {

        List<String> students = new ArrayList<String>();
        try {
            students = professorOperation.getEnrolledStudentsByCourse(professorId);
        } catch (Exception ex) {
            return null;
        }
        return students;
    }

    /**
     * POST Method to add grades for a course.
     *
     * @param studentId
     * @param courseId
     * @param grade
     * @return Response object for REST API.
     */
    @POST
    @Path("/addGrades")
    @Consumes(APPLICATION_JSON)
    public Response addGrade(
            @NotNull
            @QueryParam("studentId") String studentId,
            @NotNull
            @QueryParam("courseId") String courseId,
            @NotNull
            @QueryParam("grade") String grade) {
        try {
            professorOperation.insertGradeInDatabase(studentId, courseId, grade);
            return Response.status(200).entity("Grade updated for student: " + studentId).build();
        } catch (Exception ex) {
            return Response.status(500).entity("Something went wrong, Please Try Again ! ").build();
        }

    }

    /**
     * POST Method to assign Professor to a course.
     *
     * @param professorId
     * @param courseId
     * @return Response object for REST API
     */
    @POST
    @Path("/assignProfessor")
    @Consumes(APPLICATION_JSON)
    public Response assignProfessor(
            @NotNull
            @QueryParam("professorId") String professorId,
            @NotNull
            @QueryParam("courseId") String courseId) {
        try {
            professorOperation.addProfessorOnCourse(professorId, courseId);
            return Response.status(200).entity("Professor with profId: " + professorId + " assigned to courseId: " + courseId).build();
        } catch (Exception ex) {
            return Response.status(500).entity("Something went wrong, Please Try Again ! ").build();
        }

    }

    /**
     * GET Method to generate unique Professor ID.
     *
     * @return String denoting unique Professor ID.
     */
    @GET
    @Path("/generateProfessorId")
    @Produces(APPLICATION_JSON)
    public String generateProfessorId() {
        try {
            String generatedProfessorId = professorOperation.generateProfessorId();
            return generatedProfessorId;
        } catch (Exception ex) {
            return null;
        }

    }

}