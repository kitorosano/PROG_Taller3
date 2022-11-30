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

@Path("/espectadorRegistradoAFuncion")
public class EspectadorRegistradoFuncionController {

    Fabrica fabrica = Fabrica.getInstance();

    //registrar 1 espectador a funcion
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AltaEspectadorRegistradoAFuncionDTO altaEspectadorRegistradoAFuncionDTO) {
        try {
            fabrica.getIEspectadorRegistradoAFuncion().registrarEspectadorAFuncion(altaEspectadorRegistradoAFuncionDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //registrar coleccion de espectadores a funcion
    @POST
    @Path("/createMap")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMap(Map<String, AltaEspectadorRegistradoAFuncionDTO> espectadoresFunciones) {
        try {
            fabrica.getIEspectadorRegistradoAFuncion().registrarEspectadoresAFunciones(espectadoresFunciones);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //obtener funciones de un espectador
    @GET
    @Path("/findByNickname")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNickname(@QueryParam("nickname") String nickname) {
        try {
            Map<String, EspectadorRegistradoAFuncion> funciones = fabrica.getIEspectadorRegistradoAFuncion().obtenerFuncionesRegistradasDelEspectador(nickname);

            if (funciones != null) {
                Map<String, EspectadorRegistradoAFuncionDTO> funcionesDTO = EspectadorRegistradoAFuncionMapper.toDTOMap(funciones);
                return Response.ok(new Gson().toJson(funcionesDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //obtener espectadores de una funcion
    @GET
    @Path("/findByFuncion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByFuncion(@QueryParam("nombreFuncion") String nombreFuncion, @QueryParam("nombreEspectaculo") String nombreEspectaculo, @QueryParam("nombrePlataforma") String nombrePlataforma) {
        try {
            Map<String, Usuario> users = fabrica.getIEspectadorRegistradoAFuncion().obtenerEspectadoresRegistradosAFuncion(nombrePlataforma, nombreEspectaculo, nombreFuncion);

            if (users != null) {
                Map<String, UsuarioDTO> usersDTO = UsuarioMapper.toDTOMap(users);
                return Response.ok(new Gson().toJson(usersDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
}
