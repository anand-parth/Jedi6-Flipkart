package com.flipkart.restController;

import com.flipkart.bean.user.Professor;
import com.flipkart.business.user.ProfessorInterface;
import com.flipkart.business.user.ProfessorOperation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/professor")
public class ProfessorRestAPI {
    private static final ProfessorInterface professorOperation = new ProfessorOperation();

    @POST
    @Path("/addProfessor")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProfessorToSystem(Professor professor) throws ValidationException	{
        try
        {
            Professor professorResult=professorOperation.addProfessorToSystem(professor);
            return Response.status(201).entity("Professor with" + professor.getProfessorId() + " added in the system: ").build();
        }
        catch(Exception e)
        {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getProfessorId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProfessorId(String userId) throws ValidationException	{
        try
        {
            String professorId=professorOperation.getProfessorIdFromDatabase(userId);
            return Response.status(201).entity("Professor with" + professorId).build();
        }
        catch(Exception e)
        {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/viewCourses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCourses(
            @NotNull
            @QueryParam("professorId") String professorId) throws ValidationException	{

        List<String> courses= new ArrayList<String>();
        try
        {
            courses=professorOperation.viewCoursesByProfessor(professorId);
        }
        catch(Exception ex)
        {
            return null;
        }
        return courses;
    }

    @GET
    @Path("/viewEnrolledStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> viewEnrolledStudents(
            @NotNull
            @QueryParam("professorId") String professorId) throws ValidationException	{

        List<String> students=new ArrayList<String>();
        try
        {
            students=professorOperation.getEnrolledStudentsByCourse(professorId);
        }
        catch(Exception ex)
        {
            return null;
        }
        return students;
    }


    @POST
    @Path("/addGrades")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGrade(
            @NotNull
            @QueryParam("studentId") String studentId,
            @NotNull
            @QueryParam("courseId") String courseId,
            @NotNull
            @QueryParam("grade") String grade) throws ValidationException  	{
        try
        {
            professorOperation.insertGradeInDatabase(studentId, courseId, grade);
            return Response.status(200).entity( "Grade updated for student: " + studentId).build();
        }
        catch(Exception ex)
        {
            return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
        }

    }

    @POST
    @Path("/assignProfessor")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignProfessor(
            @NotNull
            @QueryParam("professorId") String professorId,
            @NotNull
            @QueryParam("courseId") String courseId) throws ValidationException  	{
        try
        {
            professorOperation.addProfessorOnCourse(professorId, courseId);
            return Response.status(200).entity( "Professor with profId: " + professorId + " assigned to courseId: " + courseId).build();
        }
        catch(Exception ex)
        {
            return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
        }

    }

    @GET
    @Path("/generateProfessorId")
    @Produces(MediaType.APPLICATION_JSON)
    public String generateProfessorId() throws ValidationException  	{
        try
        {
            String generatedProfessorId= professorOperation.generateProfessorId();
            return generatedProfessorId;
        }
        catch(Exception ex)
        {
            return null;
        }

    }

}