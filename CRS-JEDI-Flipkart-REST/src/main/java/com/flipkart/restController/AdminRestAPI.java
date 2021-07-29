package com.flipkart.restController;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipkart.bean.academics.Course;
import com.flipkart.bean.user.*;
import com.flipkart.business.*;
import com.flipkart.business.user.ProfessorInterface;
import com.flipkart.business.user.ProfessorOperation;
import com.flipkart.exception.*;
import com.flipkart.business.user.AdminInterface;
import com.flipkart.business.user.AdminOperation;



@Path("/admin")
public class AdminRestAPI {
    private static final AdminInterface adminOperation = new AdminOperation();
    private static final ProfessorInterface professorOperation = new ProfessorOperation();

    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@Valid Course course) throws CourseFoundException {
        try {
            adminOperation.addCourse(course);
            return Response.status(201).entity("Course : " + course.getCourseId() + " code added to catalog successfully").build();
        } catch (CourseFoundException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/editCourse/{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCourse(
            @NotNull
            @QueryParam("courseName") String courseName,
            @QueryParam("courseFaculty") String courseFaculty,
            @PathParam("courseId") String courseId) throws ValidationException {
        adminOperation.updateCourseDetails(courseId, courseName, courseFaculty);
        return Response.status(201).entity("Details for course with courseId: " + courseId + " have been updated").build();
    }


    @GET
    @Path("/viewPendingApprovals")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> viewPendingApprovals() {
        return adminOperation.viewPendingApprovals();
    }

    @PUT
    @Path("/approveStudent/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(
            @NotNull
            @PathParam("studentId") String studentId) throws ValidationException {
        List<String> studentList = adminOperation.viewPendingApprovals();
        adminOperation.approveStudent(studentId);
        return Response.status(201).entity("Student with studentId: " + studentId + " is approved").build();
    }


    @PUT
    @Path("/generateGradeCard/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateGradeCard(
            @NotNull
            @PathParam("studentId") String studentId) throws ValidationException {
        adminOperation.generateGradeCard(studentId);
        return Response.status(201).entity("Student with studentId: " + studentId + " has his grade-card generated").build();
    }


    @POST
    @Path("/addProfessor")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(@Valid Professor professor) throws ValidationException {
        professorOperation.addProfessorToSystem(professor);
        return Response.status(201).entity("Professor with professorId: " + professor.getProfessorId() + " has been added").build();
    }


    @DELETE
    @Path("/deleteUser/{deleteUserId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(
            @NotNull
            @PathParam("deleteUserId") String deleteUserId) throws ValidationException {
        adminOperation.deleteUser(deleteUserId);
        return Response.status(201).entity("User with UserId: " + deleteUserId + " has been removed from system").build();
    }


    @GET
    @Path("/listUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> listUsers() {
        return UserRestAPI.userOperation.getAllUserIdList();
    }
}