package com.flipkart.restController;

import com.flipkart.bean.user.User;
import com.flipkart.business.user.StudentInterface;
import com.flipkart.business.user.StudentOperation;
import com.flipkart.business.user.UserInterface;
import com.flipkart.business.user.UserOperation;
import com.flipkart.constant.UserRole;
import javafx.util.Pair;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/user")
public class UserRestAPI {
    private static final StudentInterface studentOperation = new StudentOperation();
    public static final UserInterface userOperation = new UserOperation();

    @GET
    @Path("/login/{userId}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verifyCredentials(
            @NotNull
            @PathParam("userId") String userId,
            @NotNull
            @PathParam("password") String password) throws ValidationException {

        Pair<Boolean, UserRole> resultPair= userOperation.verifyCredentials(userId, password);
        if(resultPair.getKey())
        {
            UserRole role= resultPair.getValue();
            switch(role)
            {

                case STUDENT:
                    String studentId=studentOperation.getStudentIdFromDatabase(userId);
                    return Response.status(200).entity("Login successful! Student "+userId+" has been approved by the administration!" ).build();

                case ADMIN:
                    return Response.status(200).entity("Login as Admin: " + userId + " successful! " ).build();

                case PROFESSOR:
                    return Response.status(200).entity("Login as Professor: " + userId + " successful! " ).build();

                default:
                    return Response.status(200).entity("Login with userId: " + userId + " unsuccessful! " ).build();
            }
        }
        else
        {
            return Response.status(500).entity("Invalid credentials!").build();
        }

    }

    @GET
    @Path("/getAllUserId")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getRole() throws ValidationException{
        return userOperation.getAllUserIdList();
    }

    @GET
    @Path("/getUserDetails")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getUserDetails(
            @NotNull
            @QueryParam("userId") String userId) throws ValidationException{
        return userOperation.printUserDetails(userId);
    }

    @PUT
    @Path("/updatePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(
            @NotNull
            @QueryParam("userId") String userId,
            @NotNull
            @QueryParam("password") String newPassword) throws ValidationException {
        try{
        userOperation.updatePassword(userId, newPassword);
        return Response.status(201).entity("Password updated successfully! ").build();
        } catch(Exception e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }


    @POST
    @Path("/studentRegistration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserToSystem(@Valid User user)
    {
        try
        {
            userOperation.addUserToSystem(user);
            return Response.status(201).entity("User: " + user +  "added to the system successfully").build();
        }
        catch(Exception ex)
        {
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }
    }


}