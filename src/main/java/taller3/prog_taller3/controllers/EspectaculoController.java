package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.E_EstadoEspectaculo;
import main.java.taller1.Logica.Clases.Plataforma;
import main.java.taller1.Logica.Clases.Usuario;
import main.java.taller1.Logica.DTOs.AltaEspectaculoDTO;
import main.java.taller1.Logica.DTOs.EspectaculoDTO;
import main.java.taller1.Logica.DTOs.EspectaculoNuevoEstadoDTO;
import main.java.taller1.Logica.Fabrica;
import main.java.taller1.Logica.Clases.Espectaculo;
import main.java.taller1.Logica.Mappers.EspectaculoMapper;

import java.util.Map;
import java.util.Optional;

@Path("/espectaculos")
public class EspectaculoController {
    Fabrica fabrica = Fabrica.getInstance();

    //obtener todos los espectaculos
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, Espectaculo> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculos();

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

    //Ingresar espectaculo
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AltaEspectaculoDTO espectaculo) {
        try {
            fabrica.getInstance().getIEspectaculo().altaEspectaculo(espectaculo);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    @Path("/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEstado(@PathParam("estado") E_EstadoEspectaculo estado) {
        try {
            Map<String, Espectaculo> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorEstado(estado);

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
    @Path("/{nicknameArtista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtista(@PathParam("nicknameArtista") String nicknameArtista) {
        try {
            Map<String, Espectaculo> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorArtista(nicknameArtista);

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
    @Path("/{nombrePlataforma}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma(@PathParam("nombrePlataforma") String nombrePlataforma) {
        try {
            Map<String, Espectaculo> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorPlataforma(nombrePlataforma);

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
    @Path("/{nombrePlataforma}&{nombreEspectaculo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma_Espectaculo(@PathParam("nombrePlataforma") String nombrePlataforma,
                                            @PathParam("nombreEspectaculo") String nombreEspectaculo){
        try {
            Optional<Espectaculo> OPTespectaculo = fabrica.getInstance().getIEspectaculo().obtenerEspectaculo(nombrePlataforma, nombreEspectaculo);
            Espectaculo espectaculo = OPTespectaculo.get();

            if (espectaculo != null) {
                EspectaculoDTO espectaculoDTO = EspectaculoMapper.toDTO(espectaculo);
                return Response.ok(new Gson().toJson(espectaculoDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    @Path("/{nombrePlataforma}&{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma_Estado(@PathParam("nombrePlataforma") String nombrePlataforma,
                                            @PathParam("estado") E_EstadoEspectaculo estado){
        try {
            Map<String, Espectaculo> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorPlataformaYEstado(nombrePlataforma, estado);

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
    @Path("/{nombreArtista}&{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtista_Estado(@PathParam("nombreArtista") String nombreArtista,
                                         @PathParam("estado") E_EstadoEspectaculo estado){
        try {
            Map<String, Espectaculo> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorArtistaYEstado(nombreArtista, estado);

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

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(EspectaculoNuevoEstadoDTO nuevoDTO) {
        try {
            fabrica.getInstance().getIEspectaculo().cambiarEstadoEspectaculo(nuevoDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }


}
