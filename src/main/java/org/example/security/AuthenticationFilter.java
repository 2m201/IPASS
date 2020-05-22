package org.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.example.domein.Account;
import org.example.webservices.AuthenticationResource;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter { //wordt gedaan voor ALLE requests -> zie het als trechter, met een filter erin
    @Override
    public void filter(ContainerRequestContext requestCtx) {
        String scheme = requestCtx.getUriInfo().getRequestUri().getScheme();

        MySecurityContext msc = new MySecurityContext(null, scheme); //lege gebruiker gemaakt
        String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) { //deel van authorisatie header
            String token = authHeader.substring("Bearer".length()).trim();

            try {
                JwtParser parser = Jwts.parser().setSigningKey(AuthenticationResource.key); //sleutel decoderen
                Claims claims = parser.parseClaimsJws(token).getBody(); //
                String user = claims.getSubject();
                msc = new MySecurityContext(Account.getAccountByName(user), scheme); // lege gebruiker wordt overgeschreven
            } catch (JwtException | IllegalArgumentException e) {
                System.out.println("Invalid JWT, processing as guest!");
            }
        }
        requestCtx.setSecurityContext(msc); //informatie van gebruiker + schema opgeslagen in java object (wie deze persoon is -> rol, etc)
    }
}
