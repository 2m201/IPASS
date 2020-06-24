package org.example.webservices;

import org.example.domein.Account;
import org.example.domein.Data;
import org.example.domein.Material;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Path("materials")
public class MaterialResource {

    @RolesAllowed({"user"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaterials(@Context SecurityContext user){
        HashMap<String, Integer> list = new HashMap<>();
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());

        for (Map.Entry<Material, Integer> entry : u1.getSavedMaterials().entrySet()) {
            list.put(entry.getKey().getName(), entry.getValue());
        }

        return Response.ok(list).build();
    }

    @RolesAllowed({"user"})
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMaterials(@Context SecurityContext user, @FormParam("softwood") int softwood, @FormParam("ironnugget") int ironnugget, @FormParam("treebranch") int treebranch, @FormParam("wood") int wood, @FormParam("clay") int clay, @FormParam("hardwood") int hardwood, @FormParam("goldnugget") int goldnugget, @FormParam("stone") int stone, @FormParam("starfragment") int starfragment, @FormParam("clumpofweeds") int clumpofweeds){
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());
        System.out.println("Softwood: " + softwood);
        //TODO check if every input is empty
        try {
            HashMap<Material, Integer> materials = new HashMap<>();

            if (!(softwood == -1)) {materials.put(Material.getMaterialByName("Soft wood"), softwood); }
            if (!(ironnugget == -1)){ materials.put(Material.getMaterialByName("Iron nugget"), ironnugget);}
            if (!(treebranch == -1)) {materials.put(Material.getMaterialByName("Tree branch"), treebranch); }
            if (!(wood == -1)) {materials.put(Material.getMaterialByName("Wood"), wood);}
            if (!(clay == -1)) {materials.put(Material.getMaterialByName("Clay"), clay);}

            if (!(hardwood == -1)) {materials.put(Material.getMaterialByName("Hard wood"), hardwood);}
            if (!(goldnugget == -1)) {materials.put(Material.getMaterialByName("Gold nugget"), goldnugget);}
            if (!(stone == -1)) {materials.put(Material.getMaterialByName("Stone"), stone);}
            if (!(starfragment == -1)) {materials.put(Material.getMaterialByName("Star fragment"), starfragment); }
            if (!(clumpofweeds == -1)) {materials.put(Material.getMaterialByName("Clump of weeds"), clumpofweeds);}

            u1.addSavedMaterials(materials);
            return Response.ok(u1.getSavedMaterials()).build();

        }catch (Exception e ) {
            return Response.status(409).entity(new AbstractMap.SimpleEntry<>("error", e.getMessage())).build();
        }

    }

}
