package com.flipkart;

import com.flipkart.controller.AdminRestAPI;
import com.flipkart.controller.ProfessorRestAPI;
import com.flipkart.controller.StudentRestAPI;
import com.flipkart.controller.UserRestAPI;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application<Configuration> {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        JerseyEnvironment jerseyEnvironment = e.jersey();
        BasicConfigurator.configure();
        LOG.info("Registering REST resources");
        //  Register all services inside jersey container like this
        jerseyEnvironment.register(new AdminRestAPI());
        jerseyEnvironment.register(new UserRestAPI());
        jerseyEnvironment.register(new ProfessorRestAPI());
        jerseyEnvironment.register(new StudentRestAPI());
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
