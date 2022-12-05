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

@Path("/espectaculos")
public class EspectaculoController {
    Fabrica fabrica = Fabrica.getInstance();

    //obtener todos los espectaculos
    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            Map<String, EspectaculoDTO> espectaculosDTO = fabrica.getIEspectaculo().obtenerEspectaculos();

            if (espectaculosDTO != null) {
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
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AltaEspectaculoDTO espectaculo) {
        try {
            fabrica.getIEspectaculo().altaEspectaculo(espectaculo);
            return Response.status(Response.Status.OK.getStatusCode()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener un espectaculo especifico
    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("nombreEspectaculo") String nombreEspectaculo, @QueryParam("nombrePlataforma") String nombrePlataforma) {
        try {
            EspectaculoDTO espectaculoDTO = fabrica.getIEspectaculo().obtenerEspectaculo(nombrePlataforma, nombreEspectaculo).orElse(null);
            return Response.ok(new Gson().toJson(espectaculoDTO)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos por estado
    @GET
    @Path("/findByEstado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEstado(@QueryParam("estado") E_EstadoEspectaculo estado) {
        try {
            Map<String, EspectaculoDTO> espectaculosDTO = fabrica.getIEspectaculo().obtenerEspectaculosPorEstado(estado);

            if (espectaculosDTO != null) {
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de un artista
    @GET
    @Path("/findByArtista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtista(@QueryParam("artistaOrganizador") String artistaOrganizador) {
        try {
            Map<String, EspectaculoDTO> espectaculosDTO = fabrica.getIEspectaculo().obtenerEspectaculosPorArtista(artistaOrganizador);

            if (espectaculosDTO != null) {
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de una plataforma
    @GET
    @Path("/findByPlataforma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataforma(@QueryParam("nombrePlataforma") String nombrePlataforma) {
        try {
            Map<String, EspectaculoDTO> espectaculosDTO = fabrica.getIEspectaculo().obtenerEspectaculosPorPlataforma(nombrePlataforma);

            if (espectaculosDTO != null) {
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de una plataforma y estado
    @GET
    @Path("/findByPlataformaAndEstado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPlataformaAndEstado(@QueryParam("nombrePlataforma") String nombrePlataforma, @QueryParam("estado") E_EstadoEspectaculo estado) {
        try {
            Map<String, EspectaculoDTO> espectaculosDTO = fabrica.getIEspectaculo().obtenerEspectaculosPorPlataformaYEstado(nombrePlataforma, estado);

            if (espectaculosDTO != null) {
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de un artista y estado
    @GET
    @Path("/findByArtistaAndEstado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtistaAndEstado(@QueryParam("nombreArtista") String nombreArtista, @QueryParam("estado") E_EstadoEspectaculo estado) {
        try {
            Map<String, EspectaculoDTO> espectaculosDTO = fabrica.getIEspectaculo().obtenerEspectaculosPorArtistaYEstado(nombreArtista, estado);

            if (espectaculosDTO != null) {
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de una categoria
    @GET
    @Path("/findByCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCategoria(@QueryParam("nombreCategoria") String nombreCategoria) {
        try {
            Map<String, EspectaculoDTO> espectaculosDTO = fabrica.getIEspectaculo().obtenerEspectaculosPorCategoria(nombreCategoria);

            if (espectaculosDTO != null) {
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Obtener espectaculos de un paquete
    @GET
    @Path("/findByPaquete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPaquete(@QueryParam("nombrePaquete") String nombrePaquete) {
        try {
            Map<String,EspectaculoDTO> espectaculosDTO = fabrica.getIEspectaculo().obtenerEspectaculosPorPaquete(nombrePaquete);

            if (espectaculosDTO != null) {
                return Response.ok(new Gson().toJson(espectaculosDTO)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    //Cambiar estado de espectaculo
    @PUT
    @Path("/updateEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEstado(EspectaculoNuevoEstadoDTO nuevoDTO) {
        try {
            fabrica.getIEspectaculo().cambiarEstadoEspectaculo(nuevoDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }
}
