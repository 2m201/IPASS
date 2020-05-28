package org.example.webservices;

import org.example.domein.Character;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("characters")
public class CharactersResource {

    @GET
    @Path("{searchCharacter}")
    @Produces("application/json")
    public Response getCharacterByName(@PathParam("searchCharacter") String character){
        List<Character> c1 = Character.searchCharacter(character);
        if(character == null){
            return Response.status(404).entity(new AbstractMap.SimpleEntry<>("error", "Character doesn't exist")).build();
        }
        if(!(c1.isEmpty())){
                return Response.ok(c1).build();
        }
        return Response.status(409).entity(new AbstractMap.SimpleEntry<>("error", "Character doesn't exist")).build();
    }

}
