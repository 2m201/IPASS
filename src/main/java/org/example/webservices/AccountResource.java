package org.example.webservices;

import org.example.domein.Account;
import org.example.domein.Data;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;

@Path("accounts")
public class AccountResource {

    @RolesAllowed("user")
    @PATCH
    @Path("/account")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(@Context SecurityContext user, @FormParam("oldPassword") String oldP, @FormParam("newPassword") String newP)  {
        try {
            String naam = user.getUserPrincipal().getName();
            Account account = Data.getData().getAccountByName(user.getUserPrincipal().getName());
            System.out.println("name " + account.getName());

            account.setPassword(oldP, newP);

            return Response.ok("The password has been changed").build();
        }
        catch(Exception e ){
            return Response.status(409).entity(new AbstractMap.SimpleEntry<>("error", e.getMessage())).build();
            }
        }
    }


