package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.*;
import main.java.taller1.Logica.DTOs.AltaCategoriaAEspectaculoDTO;
import main.java.taller1.Logica.DTOs.CategoriaDTO;
import main.java.taller1.Logica.DTOs.EspectaculoDTO;
import main.java.taller1.Logica.DTOs.EspectaculoNuevoEstadoDTO;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Mappers.CategoriaMapper;
import main.java.taller1.Logica.Mappers.EspectaculoMapper;

import java.util.Map;
import java.util.Optional;

@Path("/espectaculos")
public class CategoriaController {

    Fabrica fabrica = Fabrica.getInstance();

    //obtener todas las categorias
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, Categoria> categorias = fabrica.getInstance().getICategoria().obtenerCategorias();

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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CategoriaDTO categoriaDTO) {
        try {
            fabrica.getInstance().getICategoria().altaCategoria(categoriaDTO);
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
            Optional<Categoria> categoria = fabrica.getICategoria().obtenerCategoria(nombre);

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

    @GET
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEspectaculosByNombreCategoria(@PathParam("nombre") String nombre) {
        try {
            Map<String, Espectaculo> espectaculos = fabrica.getICategoria().obtenerEspectaculosDeCategoria(nombre)

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

    @GET
    @Path("/{nombreEspectaculo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEspectaculo(@PathParam("nombreEspectaculo") String nombreEspectaculo) {
        try {
            Map<String, Categoria> categorias = fabrica.getICategoria().obtenerCategoriasDeEspectaculo(nombreEspectaculo);

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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AltaCategoriaAEspectaculoDTO altaCategoriaAEspectaculoDTO) {
        try {
            fabrica.getInstance().getICategoria().altaCategoriaAEspectaculo(altaCategoriaAEspectaculoDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }


}
