package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.*;
import main.java.taller1.Logica.DTOs.AltaCategoriaAEspectaculoDTO;
import main.java.taller1.Logica.DTOs.CategoriaDTO;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Mappers.CategoriaMapper;

import java.util.Map;

@Path("/categorias")
public class CategoriaController {

    Fabrica fabrica = Fabrica.getInstance();

    //obtener todas las categorias
    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, Categoria> categorias = fabrica.getICategoria().obtenerCategorias();

            if (categorias != null) {
                Map<String, CategoriaDTO> categoriasDTO = CategoriaMapper.toDTOMap(categorias);
                return Response.ok(new Gson().toJson(categoriasDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Alta de una categoria nueva
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CategoriaDTO categoriaDTO) {
        try {
            fabrica.getICategoria().altaCategoria(categoriaDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener categoria por su nombre
    @GET
    @Path("/findByNombre")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNombre(@QueryParam("nombre") String nombre) {
        try {
            Categoria categoria = fabrica.getICategoria().obtenerCategoria(nombre).orElse(null);

            if (categoria != null) {
                CategoriaDTO categoriaDTO = CategoriaMapper.toDTO(categoria);
                return Response.ok(new Gson().toJson(categoriaDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener categorias de un espectaculo
    @GET
    @Path("/findByEspectaculoAndPlataforma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEspectaculoAndPlataforma(@QueryParam("nombreEspectaculo") String nombreEspectaculo, @QueryParam("nombrePlataforma") String nombrePlataforma) {
        try {
            Map<String, Categoria> categorias = fabrica.getICategoria().obtenerCategoriasDeEspectaculo(nombreEspectaculo, nombrePlataforma);

            if (categorias != null) {
                Map<String, CategoriaDTO> categoriasDTO = CategoriaMapper.toDTOMap(categorias);
                return Response.ok(new Gson().toJson(categoriasDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Alta de una categoria a un espectaculo
    @POST
    @Path("/createCategoriaAEspectaculo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategoriaAEspectaculo(AltaCategoriaAEspectaculoDTO altaCategoriaAEspectaculoDTO) {
        try {
            fabrica.getICategoria().altaCategoriaAEspectaculo(altaCategoriaAEspectaculoDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }


}
