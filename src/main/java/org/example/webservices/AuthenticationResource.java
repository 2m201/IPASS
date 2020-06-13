package org.example.webservices;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.example.domein.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;

@Path("/authentication")
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();

    private String createToken(String username, String role) throws JwtException {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUserByPassword(@FormParam("username") String username, @FormParam("password") String password) {
        if (username.isEmpty() || password.isEmpty() ){
            return Response.status(400).entity(new AbstractMap.SimpleEntry<String, String>("error", "Voer alles in")).build();
        }

        try {
            if (Account.getAccountByName(username) == null){
                return Response.status(404).entity(new AbstractMap.SimpleEntry<String, String>("error", "Account doesn't exist")).build();
            }

            String role = Account.validateLogin(username, password);
            if (role==null){
                return Response.status(409).entity(new AbstractMap.SimpleEntry<String, String>("error", "Account doesn't exist")).build();
            }
            String token = createToken(username, role);
            System.out.println(username + role);

            AbstractMap.SimpleEntry<String, String> JWT = new AbstractMap.SimpleEntry<>("JWT", token);
            return Response.ok(JWT).build();
        } catch (JwtException | IllegalArgumentException e) {
            return Response.status((Response.StatusType) Response.status(Response.Status.UNAUTHORIZED)).build();
        }
    }
}
