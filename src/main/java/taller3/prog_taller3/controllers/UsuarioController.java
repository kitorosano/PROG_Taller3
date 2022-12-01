package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.Artista;
import main.java.taller1.Logica.Clases.Usuario;
import main.java.taller1.Logica.DTOs.EspectaculoFavoritoDTO;
import main.java.taller1.Logica.DTOs.UsuarioDTO;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Mappers.UsuarioMapper;

import java.util.HashMap;
import java.util.Map;

@Path("/usuarios")
public class UsuarioController {
  
  Fabrica fabrica = Fabrica.getInstance();
  
  
  //alta de usuario
  @POST
  @Path("/create")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(UsuarioDTO user) {
    try {
      fabrica.getIUsuario().altaUsuario(user);
      return Response.status(Response.Status.CREATED).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
  
  //obtener todos los usuarios
  @GET
  @Path("/findAll")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    System.out.println("findAll");
    try {
      Map<String, Usuario> users = fabrica.getIUsuario().obtenerUsuarios();
  
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
  
  //obtener usuario por nickname
  @GET
  @Path("/findByNickname")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findByNickname(@QueryParam("nickname") String nickname) {
    System.out.println("findByNickname");
    try {
      Usuario user = fabrica.getIUsuario().obtenerUsuarioPorNickname(nickname).orElse(null);
  
      if (user != null) {
        UsuarioDTO userDTO = UsuarioMapper.toDTO(user);
        return Response.ok(new Gson().toJson(userDTO)).build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
  
  //obtener usuario por correo
  @GET
  @Path("/findByCorreo")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findByCorreo(@QueryParam("correo") String correo) {
    System.out.println("findByCorreo");
    try {
      Usuario user = fabrica.getIUsuario().obtenerUsuarioPorCorreo(correo).orElse(null);
  
      if (user != null) {
        UsuarioDTO userDTO = UsuarioMapper.toDTO(user);
        return Response.ok(new Gson().toJson(userDTO)).build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
  
  //Obtener espectadores de un paquete
  @GET
  @Path("/findByPaquete")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findByPaquete(@QueryParam("nombrePaquete") String nombrePaquete) {
    System.out.println("findByPaquete");
    try {
      Map<String, Usuario> espectadores = fabrica.getIPaquete().obtenerEspectadoresDePaquete(nombrePaquete);
  
      if (espectadores != null) {
        Map<String, UsuarioDTO> espectadoresDTO = UsuarioMapper.toDTOMap(espectadores);
        return Response.ok(new Gson().toJson(espectadoresDTO)).build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
  
  //Obtener artistas invitados a una funcion
  @GET
  @Path("/findArtistasInvitadosAFuncion")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findArtistasInvitadosAFuncion(@QueryParam("nombrePlataforma") String nombrePlataforma, @QueryParam("nombreEspectaculo") String nombreEspectaculo, @QueryParam("nombreFuncion") String nombreFuncion) {
    System.out.println("findArtistasInvitadosAFuncion");
    try {
      Map<String, Artista> artistas = fabrica.getIFuncion().obtenerArtistasInvitadosAFuncion(nombrePlataforma, nombreEspectaculo, nombreFuncion);
      Map<String, Usuario> users = new HashMap<>();
      for (Artista a : artistas.values()) {
        users.put(a.getNickname(), a);
      }
  
      if (users != null) {
        Map<String, UsuarioDTO> usuariosDTO = UsuarioMapper.toDTOMap(users);
        return Response.ok(new Gson().toJson(usuariosDTO)).build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
  
  //modificar usuario
  @PUT
  @Path("/updateByNickname")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateByNickname(UsuarioDTO user) {
    try {
      fabrica.getIUsuario().modificarUsuario(user);
      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
  
  // Marcar espectaculo favorito
  @POST
  @Path("/createEspectaculoFavorito")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createEspectaculoFavorito(EspectaculoFavoritoDTO espectaculoFavorito) {
    try {
      fabrica.getIUsuario().marcarFavorito(espectaculoFavorito);
      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }

  // desmarcar favorito
  @DELETE
  @Path("/deleteEspectaculoFavorito")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deleteEspectaculoFavorito(EspectaculoFavoritoDTO espectaculoFavorito) {
    try {
      fabrica.getIUsuario().desmarcarFavorito(espectaculoFavorito);
      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
}