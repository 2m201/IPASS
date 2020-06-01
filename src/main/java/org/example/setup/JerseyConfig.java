package org.example.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        packages("org.example.webservices", "org.example.security");
        register(RolesAllowedDynamicFeature.class);
    }
}
