package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.*;
import main.java.taller1.Logica.DTOs.FuncionDTO;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Mappers.FuncionMapper;

import java.util.Map;

@Path("/funciones")
public class FuncionController {
    Fabrica fabrica = Fabrica.getInstance();

    //obtener todos las funciones
    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, Funcion> funciones = fabrica.getIFuncion().obtenerFunciones();
            if (funciones != null) {
                Map<String, FuncionDTO> funcionesDTO = FuncionMapper.toDTOMap(funciones);
                return Response.ok(new Gson().toJson(funcionesDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Alta de nueva funcion
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(FuncionDTO funcion) {
        try {
            fabrica.getIFuncion().altaFuncion(funcion);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }


    //Obtener funcion dado plataforma-espectaculo-funcion
    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("nombrePlataforma") String nombrePlataforma, @QueryParam("nombreEspectaculo") String nombreEspectaculo, @QueryParam("nombreFuncion") String nombreFuncion) {
        try {
            Funcion funcion = fabrica.getIFuncion().obtenerFuncion(nombrePlataforma, nombreEspectaculo, nombreFuncion).orElse(null);
            if (funcion != null) {
                FuncionDTO funcionDTO = FuncionMapper.toDTO(funcion);
                return Response.ok(new Gson().toJson(funcionDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener funciones de una plataforma
    @GET
    @Path("/findByPlataforma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma(@QueryParam("nombrePlataforma") String nombrePlataforma) {
        try {
            Map<String, Funcion> funciones = fabrica.getIFuncion().obtenerFuncionesDePlataforma(nombrePlataforma);

            if (funciones != null) {
                Map<String, FuncionDTO> funcionesDTO = FuncionMapper.toDTOMap(funciones);
                return Response.ok(new Gson().toJson(funcionesDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener funciones de un espectaculo
    @GET
    @Path("/findByEspectaculoAndPlataforma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEspectaculoAndPlataforma(@QueryParam("nombrePlataforma") String nombrePlataforma, @QueryParam("nombreEspectaculo") String nombreEspectaculo) {
        try {
            Map<String, Funcion> funciones = fabrica.getIFuncion().obtenerFuncionesDeEspectaculo(nombrePlataforma, nombreEspectaculo);

            if (funciones != null) {
                Map<String, FuncionDTO> funcionesDTO  = FuncionMapper.toDTOMap(funciones);
                return Response.ok(new Gson().toJson(funcionesDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener funciones de un artista
    @GET
    @Path("/findByArtista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtista(@QueryParam("nombrePlataforma") String nombrePlataforma, @QueryParam("nombreEspectaculo") String nombreEspectaculo, @QueryParam("nombreArtista") String nombreArtista) {
        try {
            Map<String, Funcion> funciones = fabrica.getIFuncion().obtenerFuncionesDeArtista(nombrePlataforma, nombreEspectaculo, nombreArtista);

            if (funciones != null) {
                Map<String, FuncionDTO> funcionesDTO = FuncionMapper.toDTOMap(funciones);
                return Response.ok(new Gson().toJson(funcionesDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

}
