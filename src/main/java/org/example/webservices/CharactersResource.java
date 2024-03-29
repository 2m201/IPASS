package org.example.webservices;

import org.example.domein.Account;
import org.example.domein.Character;
import org.example.domein.Data;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;
import java.util.List;

@Path("characters")
public class CharactersResource {

//    @RolesAllowed({"user"})
    @GET
    @Path("{searchCharacter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharacterByName(@PathParam("searchCharacter") String character){
        String characteristic = character.substring(0,1).toUpperCase() + character.substring(1);

        List<Character> c1 = Character.searchCharacter(characteristic);

        if(!(c1.isEmpty())){
                return Response.ok(c1).build();
        }
        return Response.status(404).entity(new AbstractMap.SimpleEntry<>("error", "Character does not exist")).build();
    }

    @RolesAllowed({"admin"})
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCharacter(@FormParam("characterName") String name, @FormParam("characterURL") String URL, @FormParam("gender") String gender,
                                    @FormParam("characterSpecies") String species, @FormParam("characterPersonality") String personality, @FormParam("characterBirthday") String birthday,
                                    @FormParam("characterCatchphrase") String catchphrase, @FormParam("characterDescription") String description) {

        if (name.isEmpty() || URL.isEmpty() || gender.isEmpty() || species.isEmpty() || birthday.isEmpty() || catchphrase.isEmpty() || description.isEmpty()) {
            return Response.status(400).entity(new AbstractMap.SimpleEntry<>("error", "Please fill in every field.")).build();
        }

        if (Data.getData().getAllCharacters().stream().noneMatch(cList -> cList.getName().equals(name))) {
            String gen;

            if (gender.equals("female")) {
                gen = "Female";
            } else  {
                gen="Male";
            }
            Character c = new Character(name, gen, personality, species, birthday, catchphrase, description);
            c.setPicture(URL);
            return Response.ok(c).build();
        }
        return Response.status(409).entity(new AbstractMap.SimpleEntry<>("error", "The character you are trying to create already exists.")).build();
    }

    @RolesAllowed({"admin"})
    @PATCH
    @Path("{charName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeCharacter(@PathParam("charName") String changeName, @FormParam("characterName") String name, @FormParam("characterURL") String URL, @FormParam("gender") String gender,
                                    @FormParam("characterSpecies") String species, @FormParam("characterPersonality") String personality, @FormParam("characterBirthday") String birthday,
                                    @FormParam("characterCatchphrase") String catchphrase, @FormParam("characterDescription") String description)  {

        Character character1 = Data.getData().getCharacterByName(changeName);

        try{
            if ((character1 == null)) {throw new Exception("The character you are trying to modify does not exist.");}

            if (!(name.equals(""))) {
                if (Data.getData().getAllCharacters().stream().noneMatch(cList -> cList.getName().equals(name))) {
                    character1.setName(name);
                } else {throw new Exception("A character with that name already exists.");}
            }

            try {
                if (!(URL.equals(""))) {
                    character1.setPicture(URL);
                }
                if (!(gender.equals(""))) {
                    character1.setGender(gender);
                }
                if (!(personality.equals(""))) {
                    character1.setPersonality(personality);
                }
                if (!(species.equals(""))) {
                    character1.setSpecies(species);
                }
                if (!(birthday.equals(""))) {
                    character1.setBirthday(birthday);
                }
                if (!(catchphrase.equals(""))) {
                    character1.setCatchphrase(catchphrase);
                }
                if (!(description.equals(""))) {
                    character1.setDescription(description);
                }
                return Response.ok("The character has been modified.").build();
            } catch (Exception e) {
                return Response.ok("The character has been modified.").build();
            }

        } catch (Exception e) {
            return Response.status(409).entity(new AbstractMap.SimpleEntry<>("error", e.getMessage())).build();
        }
    }

    @RolesAllowed({"admin"})
    @DELETE
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCharacter(@PathParam("name") String name) {

        try {

        Character.deleteCharacter(name);

            return Response.ok("The character has been deleted").build();

    }catch(Exception e) {
            return Response.status(404).entity(new AbstractMap.SimpleEntry<>("error", e.getMessage())).build();

        }

    }

    @RolesAllowed({"user"})
    @PATCH
    @Path("save/{list}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCharacterToList(@Context SecurityContext user, @PathParam("list") String type, @FormParam("characterName") String name) {
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());
        Character c1 = Data.getData().getCharacterByName(name);

        try {
            u1.addCharacter(type, c1);
            return Response.ok(u1).build();
        }catch (Exception e ) {
            return Response.status(409).entity(new AbstractMap.SimpleEntry<>("error", e.getMessage())).build();

        }

    }

//    @RolesAllowed({"user"})
    @GET
    @Path("current")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentCharacter(@Context SecurityContext user) {
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());


        return Response.ok(u1.getCurrentCharacters()).build();
    }

    @RolesAllowed({"user"})
    @GET
    @Path("favourite")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavouriteCharacter(@Context SecurityContext user) {
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());

        return Response.ok(u1.getFavouriteCharacters()).build();

    }

    @RolesAllowed({"user"})
    @DELETE
    @Path("transfer/{character}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferCharacter(@Context SecurityContext user, @PathParam("character") String name) {
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());
        try {
            Character character1 = Data.getData().getCharacterByName(name);
            u1.addCharacter("current", character1);

            u1.deleteCharacter("favourite", name);

            return Response.ok(u1).build();

        } catch (Exception e) {
            return Response.status(409).entity(new AbstractMap.SimpleEntry<>("error", e.getMessage())).build();
        }
    }

    @RolesAllowed({"user"})
    @DELETE
    @Path("{type}/{character}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCharacter(@Context SecurityContext user, @PathParam("type") String list, @PathParam("character") String name) {
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());

        try {
            u1.deleteCharacter(list, name);
            return Response.ok(u1).build();
        }catch (Exception e ) {
            return Response.status(409).entity(new AbstractMap.SimpleEntry<>("error", e.getMessage())).build();
        }
    }

}

