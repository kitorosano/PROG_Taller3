package taller3.prog_taller3.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import main.java.taller1.Logica.Clases.E_EstadoEspectaculo;
import main.java.taller1.Logica.DTOs.AltaEspectaculoDTO;
import main.java.taller1.Logica.DTOs.EspectaculoDTO;
import main.java.taller1.Logica.DTOs.EspectaculoNuevoEstadoDTO;
import main.java.taller1.Logica.Fabrica;

import java.util.Map;
import java.util.Optional;

public class EspectaculoController {
    Fabrica fabrica = Fabrica.getInstance();

    //obtener todos los espectaculos
    @GET
    @Path("/espectaculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, EspectaculoDTO> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculos();

            if (espectaculos != null) {
                return Response.ok(new Gson().toJson(espectaculos)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Ingresar espectaculo
    @POST
    @Path("/espectaculos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AltaEspectaculoDTO espectaculo) {
        try {
            fabrica.getInstance().getIEspectaculo().altaEspectaculo(espectaculo);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
    
    //Obtener un espectaculo especifico
    @GET
    @Path("/espectaculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("nombreEspectaculo") String nombreEspectaculo, @QueryParam("nombrePlataforma") String nombrePlataforma) {
        try {
            EspectaculoDTO espectaculo = fabrica.getInstance().getIEspectaculo().obtenerEspectaculo(nombrePlataforma, nombreEspectaculo).orElse(null);
            
            if (espectaculo != null) {
                return Response.ok(new Gson().toJson(espectaculo)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos por estado
    @GET
    @Path("/espectaculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEstado(@QueryParam("estado") E_EstadoEspectaculo estado) {
        try {
            Map<String, EspectaculoDTO> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorEstado(estado);

            if (espectaculos != null) {
                return Response.ok(new Gson().toJson(espectaculos)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de un artista
    @GET
    @Path("/espectaculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtista(@QueryParam("artistaOrganizador") String artistaOrganizador) {
        try {
            Map<String, EspectaculoDTO> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorArtista(artistaOrganizador);

            if (espectaculos != null) {
                return Response.ok(new Gson().toJson(espectaculos)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de una plataforma
    @GET
    @Path("/espectaculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma(@QueryParam("nombrePlataforma") String nombrePlataforma) {
        try {
            Map<String, EspectaculoDTO> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorPlataforma(nombrePlataforma);

            if (espectaculos != null) {
                return Response.ok(new Gson().toJson(espectaculos)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de una plataforma y estado
    @GET
    @Path("/espectaculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataformaAndEstado(@QueryParam("nombrePlataforma") String nombrePlataforma, @QueryParam("estado") E_EstadoEspectaculo estado) {
        try {
            Map<String, EspectaculoDTO> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorPlataformaYEstado(nombrePlataforma, estado);

            if (espectaculos != null) {
                return Response.ok(new Gson().toJson(espectaculos)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de un artista y estado
    @GET
    @Path("/{nombreArtista}&{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtistaAndEstado(@QueryParam("nombreArtista") String nombreArtista, @QueryParam("estado") E_EstadoEspectaculo estado) {
        try {
            Map<String, EspectaculoDTO> espectaculos = fabrica.getInstance().getIEspectaculo().obtenerEspectaculosPorArtistaYEstado(nombreArtista, estado);

            if (espectaculos != null) {
                return Response.ok(new Gson().toJson(espectaculos)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
    
    //Obtener espectaculos de una categoria
    @GET
    @Path("/espectaculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNombreCategoria(@QueryParam("nombreCategoria") String nombreCategoria) {
        try {
            Map<String, EspectaculoDTO> espectaculos = fabrica.getIEspectaculo().obtenerEspectaculosPorCategoria(nombreCategoria);
            
            if (espectaculos != null) {
                return Response.ok(new Gson().toJson(espectaculos)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
    
    //Obtener espectaculos de un paquete
    @GET
    @Path("/espectaculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPaquete(@QueryParam("nombrePaquete") String nombrePaquete) {
        try {
            Map<String,EspectaculoDTO> espectaculos = fabrica.getIEspectaculo().obtenerEspectaculosPorPaquete(nombrePaquete);
            
            if (espectaculos != null) {
                return Response.ok(new Gson().toJson(espectaculos)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
    
    //Cambiar estado de espectaculo (Considerar si es viable dejar @DELETE)
    @DELETE
    @Path("/espectaculos")
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
