package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.Usuario;
import main.java.taller1.Logica.DTOs.UsuarioDTO;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Mappers.UsuarioMapper;

import java.util.Map;

@Path("/users")
public class UserController {
  
  Fabrica fabrica = Fabrica.getInstance();

  //obtener todos los usuarios
  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    try {
      Map<String,Usuario> users = fabrica.getInstance().getIUsuario().obtenerUsuarios();

      if (users != null) {
        Map<String,UsuarioDTO> usersDTO= UsuarioMapper.toDTOMap(users);
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
  @Path("/{nickname}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("nickname") String nickname) {
    try {
      Usuario user = fabrica.getInstance().getIUsuario().obtenerUsuarioPorNickname(nickname).orElse(null);

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

  //alta de usuario
  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(UsuarioDTO user) {
    try {
      fabrica.getInstance().getIUsuario().altaUsuario(user);
      return Response.status(Response.Status.CREATED).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }

  //modificar usuario
  @PUT
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateById(UsuarioDTO user) {
    try {
      fabrica.getInstance().getIUsuario().modificarUsuario(user);
      return Response.status(Response.Status.NO_CONTENT).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }

  //obtener usuario por correo
  @GET
  @Path("/{correo}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findByMail(@PathParam("correo") String correo) {
    try {
      Usuario user = fabrica.getInstance().getIUsuario().obtenerUsuarioPorCorreo(correo).orElse(null);

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




  
//  @DELETE
//  @Path("/{id}")
//  public Response deleteById(@PathParam("id") String id) {
//    try {
//      userService.deleteById(id);
//      return Response.status(Response.Status.NO_CONTENT).build();
//    } catch (Exception e) {
//      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
//    }
//  }
}