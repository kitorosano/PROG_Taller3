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

import java.io.FileInputStream;
import java.util.Map;
import java.util.Optional;

@Path("/categorias")
public class DatabaseController {

    Fabrica fabrica = Fabrica.getInstance();

    //Guardar imagen (Considerar cambiar a PUT-DELETE)
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(FileInputStream imagen) {
        try {
            fabrica.getInstance().getIDatabase().guardarImagen(imagen);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

}
