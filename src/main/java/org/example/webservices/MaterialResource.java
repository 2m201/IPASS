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
import java.util.HashMap;

@Path("materials")
public class MaterialResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaterials(@Context SecurityContext user){
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());
        HashMap<Material, Integer> list = u1.getSavedMaterials();

        return Response.ok(list).build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMaterials(@Context SecurityContext user, @FormParam("softwood") int softwood, @FormParam("ironnugget") int ironnugget, @FormParam("treebranch") int treebranch, @FormParam("wood") int wood, @FormParam("clay") int clay, @FormParam("hardwood") int hardwood, @FormParam("goldnugget") int goldnugget, @FormParam("stone") int stone, @FormParam("starfragment") int starfragment, @FormParam("clumpofweeds") int clumpofweeds){
        Account u1 = Data.getData().getAccountByName(user.getUserPrincipal().getName());

         HashMap<Material, Integer> materials = new HashMap<>();
         materials.put(Material.getMaterialByName("Soft wood"), softwood);
         materials.put(Material.getMaterialByName("Iron nugget"), ironnugget);
         materials.put(Material.getMaterialByName("Tree branch"), treebranch);
         materials.put(Material.getMaterialByName("Wood"), wood);
         materials.put(Material.getMaterialByName("Gold nugget"), goldnugget);
         materials.put(Material.getMaterialByName("Stone"), stone);
         materials.put(Material.getMaterialByName("Star fragment"), starfragment);
         materials.put(Material.getMaterialByName("Clump of weeds"), clumpofweeds);

         u1.addSavedMaterials(materials);
        return Response.ok(u1).build();

    }

}
