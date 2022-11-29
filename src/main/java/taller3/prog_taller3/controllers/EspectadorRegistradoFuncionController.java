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
import java.util.Optional;

@Path("/EspectadorAFuncion")
public class EspectadorRegistradoFuncionController {

    Fabrica fabrica = Fabrica.getInstance();

    //registrar 1 espectador a funcion
    @POST
    @Path("/espectador")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AltaEspectadorRegistradoAFuncionDTO altaEspectadorRegistradoAFuncionDTO) {
        try {
            fabrica.getInstance().getIEspectadorRegistradoAFuncion().registrarEspectadorAFuncion(altaEspectadorRegistradoAFuncionDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //registrar coleccion de espectadores a funcion
    @POST
    @Path("/espectadores")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, AltaEspectadorRegistradoAFuncionDTO> espectadoresFunciones) {
        try {
            fabrica.getInstance().getIEspectadorRegistradoAFuncion().registrarEspectadoresAFunciones(espectadoresFunciones);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //obtener funciones de un espectador
    @GET
    @Path("/{nicknameEspectador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNickname(@PathParam("nicknameEspectador") String nicknameEspectador) {
        try {
            Map<String, EspectadorRegistradoAFuncion> funciones = fabrica.getInstance().getIEspectadorRegistradoAFuncion().obtenerFuncionesRegistradasDelEspectador(nicknameEspectador);

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
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNombre(@PathParam("nombre") String nombre) {
        try {
            Map<String, Usuario> users = fabrica.getInstance().getIEspectadorRegistradoAFuncion().obtenerEspectadoresRegistradosAFuncion(nombre);

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
