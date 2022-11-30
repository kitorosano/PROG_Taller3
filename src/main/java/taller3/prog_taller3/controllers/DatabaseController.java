//package taller3.prog_taller3.controllers;
//
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import main.java.taller1.Logica.Fabrica;
//
//import java.io.FileInputStream;
//import java.util.Map;
//import java.util.Optional;
//
//public class DatabaseController {
//
//    Fabrica fabrica = Fabrica.getInstance();
//
//    //Guardar imagen (Considerar cambiar a PUT-DELETE)
//    @POST
//    @Path("/database")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response create(FileInputStream imagen) {
//        try {
//            fabrica.getInstance().getIDatabase().guardarImagen(imagen);
//            return Response.status(Response.Status.CREATED).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
//        }
//    }
//
//}
