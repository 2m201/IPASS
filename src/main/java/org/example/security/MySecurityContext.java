package org.example.security;

import org.example.domein.Account;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext { //object bestaat heel kort, tot de request gedaan is
    private Account user;
    private String scheme;

    public MySecurityContext(Account user, String scheme) {
        this.user = user;
        this.scheme = scheme;
    }

    @Override
    public Principal getUserPrincipal() {return this.user;} //principal bevat informatie over de gebruiker, vooral de naam

    @Override
    public boolean isUserInRole(String s) {
        if(user.getRole() != null) {
            System.out.println(s + "equals" + user.getRole());
            return s.equals(user.getRole());
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}