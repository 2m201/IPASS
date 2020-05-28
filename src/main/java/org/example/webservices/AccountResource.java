package org.example.webservices;

import org.example.domein.Material;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("account")
public class AccountResource {

   @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveMaterials(@FormParam ("softwood") String materiallist){
       return Response.ok(materiallist).build();
   }
}
