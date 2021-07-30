package com.flipkart.controller;

import com.flipkart.bean.user.User;
import com.flipkart.business.user.StudentInterface;
import com.flipkart.business.user.StudentOperation;
import com.flipkart.business.user.UserInterface;
import com.flipkart.business.user.UserOperation;
import com.flipkart.constant.CustomEntry;
import com.flipkart.constant.UserRole;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;


/**
 * @author JEDI-6-Flipkart-G1
 * Class to call User functions using REST requests
 */
@Path("/user")
public class UserRestAPI {
    private static final StudentInterface studentOperation = new StudentOperation();
    public static final UserInterface userOperation = new UserOperation();

    /**
     * GET Method to verify login credentials
     *
     * @param userId
     * @param password
     * @return Response object for REST API.
     */
    @GET
    @Path("/login/{userId}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verifyCredentials(
            @NotNull
            @PathParam("userId") String userId,
            @NotNull
            @PathParam("password") String password) {
        try {
            CustomEntry<Boolean, UserRole> resultPair = userOperation.verifyCredentials(userId, password);
            if (resultPair.getKey()) {
                UserRole role = resultPair.getValue();
                switch (role) {

                    case STUDENT:
                        String studentId = studentOperation.getStudentIdFromDatabase(userId);
                        return Response.status(200).entity("Login successful! Student " + userId + " has been approved by the administration!").build();

                    case ADMIN:
                        return Response.status(200).entity("Login as Admin: " + userId + " successful! ").build();

                    case PROFESSOR:
                        return Response.status(200).entity("Login as Professor: " + userId + " successful! ").build();

                    default:
                        return Response.status(200).entity("Login with userId: " + userId + " unsuccessful! ").build();
                }
            } else {
                return Response.status(500).entity("Invalid credentials!").build();
            }
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * GET method to get user Roles.
     *
     * @return List of roles.
     */
    @GET
    @Path("/getAllUserId")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getRole() {
        try {
            return userOperation.getAllUserIdList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * GET method to get User Details.
     *
     * @param userId
     * @return Map from user id to user details.
     */
    @GET
    @Path("/getUserDetails")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getUserDetails(
            @NotNull
            @QueryParam("userId") String userId) {
        try {
            return userOperation.printUserDetails(userId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * PUT Method to update user password
     *
     * @param userId
     * @param newPassword
     * @return Response object for REST API.
     */
    @PUT
    @Path("/updatePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(
            @NotNull
            @QueryParam("userId") String userId,
            @NotNull
            @QueryParam("password") String newPassword) {
        try {
            userOperation.updatePassword(userId, newPassword);
            return Response.status(201).entity("Password updated successfully! ").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


    /**
     * POST Method for adding user to the system.
     *
     * @param user
     * @return Response object for REST API.
     */
    @POST
    @Path("/studentRegistration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserToSystem(@Valid User user) {
        try {
            userOperation.addUserToSystem(user);
            return Response.status(201).entity("User: " + user + "added to the system successfully").build();
        } catch (Exception ex) {
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }
    }

}