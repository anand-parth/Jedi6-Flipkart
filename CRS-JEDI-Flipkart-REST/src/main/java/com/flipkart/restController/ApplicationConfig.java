package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;



public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // All the web services to be registered Here
        register(StudentRestAPI.class);
        register(UserRestAPI.class);
        register(ProfessorRestAPI.class);
        register(AdminRestAPI.class);
        register(CustomerController.class);
        register(HelloController.class);

    }

}
