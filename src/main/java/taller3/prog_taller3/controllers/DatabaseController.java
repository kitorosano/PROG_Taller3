package taller3.prog_taller3.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Fabrica;

import java.io.FileInputStream;

@Path("/database")
public class DatabaseController {

    Fabrica fabrica = Fabrica.getInstance();
    
    @POST
    @Path("/createImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createImage(FileInputStream imagen) {
        try {
            fabrica.getIDatabase().guardarImagen(imagen);
            return Response.status(Response.Status.OK.getStatusCode()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

}
