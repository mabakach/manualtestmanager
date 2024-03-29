package ch.mabaka.manualtestmanager.rest;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @GET
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    
    public String hello() {
        return "hello";
    }
}