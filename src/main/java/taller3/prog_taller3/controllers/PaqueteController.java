package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.*;
import main.java.taller1.Logica.DTOs.*;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Mappers.*;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

import java.util.Map;
import java.util.Optional;

@Path("/paquetes")
public class PaqueteController {

    Fabrica fabrica = Fabrica.getInstance();

    //obtener todas las categorias
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, Paquete> paquetes = fabrica.getInstance().getIPaquete().obtenerPaquetes();

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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PaqueteDTO paqueteDTO) {
        try {
            fabrica.getInstance().getIPaquete().altaPaquete(paqueteDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNombre(@PathParam("nombre") String nombre) {
        try {
            Optional<Paquete> OPTpaquete = fabrica.getIPaquete().obtenerPaquete(nombre);
            Paquete paquete = OPTpaquete.get();

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

    @GET
    @Path("/{nombreEspectaculo}&{nombrePlataforma}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma_Espectaculo(@PathParam("nombrePlataforma") String nombrePlataforma,
                                                 @PathParam("nombreEspectaculo") String nombreEspectaculo){
        try {
            Map<String, Paquete> paquetes = fabrica.getInstance().getIPaquete().obtenerPaquetesDeEspectaculo(nombreEspectaculo, nombrePlataforma);

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

    @GET
    @Path("/espectaculos/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEspectaculoByNombre(@PathParam("nombre") String nombre) {
        try {
            Map<String,Espectaculo> espectaculos = fabrica.getIPaquete().obtenerEspectaculosDePaquete(nombre);

            if (espectaculos != null) {
                Map<String, EspectaculoDTO> espectaculosDTO = EspectaculoMapper.toDTOMap(espectaculos);
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    /*      TODO   Map<String, EspectadorPaquete>     (FALTA VER QUE PASA CON EspectadorPaquete)
    @GET
    @Path("/espectador/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEspectadorByNombre(@PathParam("nombre") String nombre) {
        try {
            Map<String,EspectadorPaquete> espectadores = fabrica.getIPaquete().obtenerEspectadoresDePaquete(nombre);

            if (espectadores != null) {
                Map<String, EspectadorPaqueteDTO> espectadorPaquetesDTOMap = UsuarioMapper.toDTOMap(espectaculos);
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
    */

    /*       TODO Map<String, EspectadorPaquete>       (FALTA VER QUE PASA CON EspectadorPaquete)
    @GET
    @Path("/espectador/{nickname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEspectadorByNombre(@PathParam("nickname") String nickname) {
        try {
            Map<String,EspectadorPaquete> espectadores = fabrica.getIPaquete().obtenerPaquetesPorEspectador(nickname);

            if (espectadores != null) {
                Map<String, EspectadorPaqueteDTO> espectadorPaquetesDTOMap = UsuarioMapper.toDTOMap(espectaculos);
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
     */

    /*       TODO     void altaEspectaculoAPaquete     (FALTA VER QUE PASA CON EspectadorPaquete)
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PaqueteDTO paqueteDTO) {
        try {
            fabrica.getInstance().getIPaquete().altaPaquete(paqueteDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
    */

    /*       TODO     void altaEspectadorAPaquete      (FALTA VER QUE PASA CON EspectadorPaquete)
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PaqueteDTO paqueteDTO) {
        try {
            fabrica.getInstance().getIPaquete().altaPaquete(paqueteDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
    */



}
