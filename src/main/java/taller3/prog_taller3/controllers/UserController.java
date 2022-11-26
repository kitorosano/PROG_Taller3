package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.Usuario;
import main.java.taller1.Logica.Fabrica;

import java.util.Map;

@Path("/users")
public class UserController {
  
  Fabrica fabrica = Fabrica.getInstance();
  
  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    try {
      Map<String,Usuario> users = fabrica.getInstance().getIUsuario().obtenerUsuarios();
      if (users != null) {
        return Response.ok(new Gson().toJson(users)).build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
  
  @GET
  @Path("/{nickname}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("nickname") String nickname) {
    try {
      Usuario user = fabrica.getInstance().getIUsuario().obtenerUsuarioPorNickname(nickname).orElse(null);
      
      if (user != null) {
        return Response.ok(new Gson().toJson(user)).build();
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
  public Response create(Usuario user) {
    try {
      fabrica.getInstance().getIUsuario().altaUsuario(user);
      return Response.status(Response.Status.CREATED).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
    }
  }
  
  @PUT
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateById(Usuario user) {
    try {
      fabrica.getInstance().getIUsuario().modificarUsuario(user);
      return Response.status(Response.Status.NO_CONTENT).build();
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