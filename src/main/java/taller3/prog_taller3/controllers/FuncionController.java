package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.*;
import main.java.taller1.Logica.DTOs.EspectaculoDTO;
import main.java.taller1.Logica.DTOs.FuncionDTO;
import main.java.taller1.Logica.DTOs.PlataformaDTO;
import main.java.taller1.Logica.DTOs.UsuarioDTO;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Mappers.EspectaculoMapper;
import main.java.taller1.Logica.Mappers.FuncionMapper;
import main.java.taller1.Logica.Mappers.UsuarioMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/funciones")
public class FuncionController {
    Fabrica fabrica = Fabrica.getInstance();

    //obtener todos las funciones
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, Funcion> funciones = fabrica.getInstance().getIFuncion().obtenerFunciones();
            if (funciones != null) {
                return Response.ok(new Gson().toJson(funciones)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Alta de nueva funcion
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(FuncionDTO funcion) {
        try {
            fabrica.getInstance().getIFuncion().altaFuncion(funcion);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }


    //Obtener funcion dado plataforma-espectaculo-funcion
    @GET
    @Path("/{nombrePlataforma}&{nombreEspectaculo}&{nombreFuncion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma_Espectaculo(@PathParam("nombrePlataforma") String nombrePlataforma,
                                                 @PathParam("nombreEspectaculo") String nombreEspectaculo,
                                                 @PathParam("nombreFuncion") String nombreFuncion) {
        try {
            Optional<Funcion> OPTfuncion = fabrica.getInstance().getIFuncion().obtenerFuncion(nombrePlataforma, nombreEspectaculo, nombreFuncion);
            Funcion funcion = OPTfuncion.get();

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
    @Path("/{nombrePlataforma}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNombre(@PathParam("nombrePlataforma") String nombrePlataforma) {
        try {
            Map<String, Funcion> funciones = fabrica.getInstance().getIFuncion().obtenerFuncionesDePlataforma(nombrePlataforma);

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
    @Path("/{nombrePlataforma}&{nombreEspectaculo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma_Espectaculo(@PathParam("nombrePlataforma") String nombrePlataforma,
                                                         @PathParam("nombreEspectaculo") String nombreEspectaculo){
        try {
            Map<String, Funcion> funciones = fabrica.getInstance().getIFuncion().obtenerFuncionesDeEspectaculo(nombrePlataforma, nombreEspectaculo);

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

    //Obtener artistas invitados a una funcion
    @GET
    @Path("/{nombrePlataforma}&{nombreEspectaculo}&{nombreFuncion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findArtistasByPlataforma_Espectaculo(@PathParam("nombrePlataforma") String nombrePlataforma,
                                                @PathParam("nombreEspectaculo") String nombreEspectaculo,
                                                @PathParam("nombreFuncion") String nombreFuncion) {
        try {
            Map<String, Artista> artistas = fabrica.getInstance().getIFuncion().obtenerArtistasInvitadosAFuncion(nombrePlataforma, nombreEspectaculo, nombreFuncion);
            Map<String, Usuario> users = new HashMap<String, Usuario>();
            for (Artista a : artistas.values()){
                users.put(a.getNickname(), (Usuario) a);
            }

            if (users != null) {
                Map<String, UsuarioDTO> usuariosDTO  = UsuarioMapper.toDTOMap(users);
                return Response.ok(new Gson().toJson(usuariosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener funciones de un artista
    @GET
    @Path("/{nombrePlataforma}&{nombreEspectaculo}&{nombreArtista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtista(@PathParam("nombrePlataforma") String nombrePlataforma,
                                  @PathParam("nombreEspectaculo") String nombreEspectaculo,
                                  @PathParam("nombreArtista") String nombreArtista) {
        try {
            Map<String, Funcion> funciones = fabrica.getInstance().getIFuncion().obtenerFuncionesDeArtista(nombrePlataforma, nombreEspectaculo, nombreArtista);

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
