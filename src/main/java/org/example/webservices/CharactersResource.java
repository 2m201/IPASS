package org.example.webservices;

import org.example.domein.Account;
import org.example.domein.Character;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;
import java.util.List;

@Path("characters")
public class CharactersResource {

    @GET
    @Path("{searchCharacter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharacterByName(@PathParam("searchCharacter") String character){

        List<Character> c1 = Character.searchCharacter(character);

        if(!(c1.isEmpty())){
                return Response.ok(c1).build();
        }
        return Response.status(404).entity(new AbstractMap.SimpleEntry<>("error", "Character doesn't exist")).build();
    }

    @POST
    @Path("/save/{list}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCharacterToList(@Context SecurityContext user, @PathParam("list") String type, @FormParam("characterName") String name) {
        Account u1 = Account.getAccountByName(user.getUserPrincipal().getName());
        Character c1 = Character.getCharacterByName(name);

        if (type.equals("current")) {
            if (u1.getCurrentCharacters().size() == 10) {
                return Response.status(409).entity(new AbstractMap.SimpleEntry<String, String>("error", "List has reached max capacity")).build();
            } else{
                u1.addCurrentCharacter(c1);
                return Response.ok(u1).build();
            }
        } else if (type.equals("favourite")) {
            u1.addFavouriteCharacter(c1);
            return Response.ok(u1).build();
        }

        return Response.status(400).entity(new AbstractMap.SimpleEntry<String, String>("error", "Something went wrong")).build();


    }


}