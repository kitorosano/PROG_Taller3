package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.DTOs.PlataformaDTO;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Clases.Plataforma;
import main.java.taller1.Logica.Mappers.PlataformaMapper;

import java.util.Map;

@Path("/plataformas")
public class PlataformaController {

    Fabrica fabrica = Fabrica.getInstance();

    //obtener todas las plataformas
    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String,Plataforma> plataformas = fabrica.getIPlataforma().obtenerPlataformas();

            if (plataformas != null) {
                Map<String, PlataformaDTO> plataformasDTO = PlataformaMapper.toDTOMap(plataformas);
                return Response.ok(new Gson().toJson(plataformasDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //obtener plataforma por nombre
    @GET
    @Path("/findByNombre")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNombre(@QueryParam("nombre") String nombre) {
        try {
            Plataforma plataforma = fabrica.getIPlataforma().obtenerPlataforma(nombre).orElse(null);

            if (plataforma != null) {
                PlataformaDTO plataformaDTO = PlataformaMapper.toDTO(plataforma);
                return Response.ok(new Gson().toJson(plataformaDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Ingresar plataforma
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PlataformaDTO plataforma) {
        try {
            fabrica.getIPlataforma().altaPlataforma(plataforma);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
}
