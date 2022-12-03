package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.*;
import main.java.taller1.Logica.DTOs.*;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Mappers.*;

import java.util.Map;

@Path("/paquetes")
public class PaqueteController {

    Fabrica fabrica = Fabrica.getInstance();

    //obtener todos los paquetes
    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, Paquete> paquetes = fabrica.getIPaquete().obtenerPaquetes();

            if (paquetes != null) {
                Map<String, PaqueteDTO> paquetesDTOMap = PaqueteMapper.toDTOMap(paquetes);
                return Response.ok(new Gson().toJson(paquetesDTOMap)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Ingresar paquete nuevo
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PaqueteDTO paqueteDTO) {
        try {
            fabrica.getIPaquete().altaPaquete(paqueteDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener paquete por nombre
    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("nombre") String nombre) {
        try {
            Paquete paquete = fabrica.getIPaquete().obtenerPaquete(nombre).orElse(null);

            if (paquete != null) {
                PaqueteDTO paqueteDTO = PaqueteMapper.toDTO(paquete);
                return Response.ok(new Gson().toJson(paqueteDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener paquetes de un espectaculo
    @GET
    @Path("/findByEspectaculoAndPlataforma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEspectaculoAndPlataforma(@QueryParam("nombreEspectaculo") String nombreEspectaculo, @QueryParam("nombrePlataforma") String nombrePlataforma) {
        try {
            Map<String, Paquete> paquetes = fabrica.getIPaquete().obtenerPaquetesDeEspectaculo(nombreEspectaculo, nombrePlataforma);

            if (paquetes != null) {
                Map<String, PaqueteDTO> paquetesDTO  = PaqueteMapper.toDTOMap(paquetes);
                return Response.ok(new Gson().toJson(paquetesDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }


    //Obtener paquetes de un espectador
    @GET
    @Path("/findByNombreEspectador")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNombreEspectador(@QueryParam("nombreEspectador") String nombreEspectador) {
        try {
            Map<String, Paquete> paquetes = fabrica.getIPaquete().obtenerPaquetesPorEspectador(nombreEspectador);

            if (paquetes != null) {
                Map<String, PaqueteDTO> PaquetesDTOMap = PaqueteMapper.toDTOMap(paquetes);
                return Response.ok(new Gson().toJson(PaquetesDTOMap)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }


    //Alta de espectaculo a paquete
    @POST
    @Path("/createEspectaculoAPaquete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEspectaculoAPaquete(AltaEspectaculoAPaqueteDTO altaEspectaculoAPaqueteDTO) {
        try {
            fabrica.getIPaquete().altaEspectaculoAPaquete(altaEspectaculoAPaqueteDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Alta de espectador a paquete
    @POST
    @Path("/createEspectadorAPaquete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEspectadorAPaquete(AltaEspectadorAPaqueteDTO altaEspectadorAPaqueteDTO) {
        try {
            fabrica.getIPaquete().altaEspectadorAPaquete(altaEspectadorAPaqueteDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }



}
