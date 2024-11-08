package com.es.diecines.Controller;

import com.es.diecines.DTO.PeliculaDTO;
import com.es.diecines.Error.BaseDeDatosException;
import com.es.diecines.Error.ErrorGenerico;
import com.es.diecines.Service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @PostMapping
    public PeliculaDTO create(
            @RequestBody PeliculaDTO peliculaDTO
    ) {
        return peliculaService.create(peliculaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable String id
    ) {
        try {
            // 1 Comprobar que el id no viene vacío
            if (id == null || id.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            // 2 Si no viene vacio, llamo al Service
            PeliculaDTO p = peliculaService.getById(id);

            // 3 Compruebo la validez de p para devolver una respuesta
            if(p == null) {
                ResponseEntity<ErrorGenerico> respuesta =
                        new ResponseEntity<>(
                                new ErrorGenerico("Pelicula no encontrada", "localhost:8080/peliculas/{id}"),
                                HttpStatus.NOT_FOUND);
                return respuesta;
            } else {
                ResponseEntity<PeliculaDTO> respuesta = new ResponseEntity<PeliculaDTO>(
                        p, HttpStatus.OK
                );
                return respuesta;
            }
        } catch (NumberFormatException e) {
            ErrorGenerico error = new ErrorGenerico(
                    e.getMessage(),
                    "localhost:8080/peliculas/"+id
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (BaseDeDatosException e) {
            ErrorGenerico error = new ErrorGenerico(
                    e.getMessage(),
                    "localhost:8080/peliculas/"+id
            );
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<PeliculaDTO> p = peliculaService.getAll();

        if(p == null) {
            ResponseEntity<ErrorGenerico> respuesta =
                    new ResponseEntity<>(
                            new ErrorGenerico("No hay películas", "localhost:8080/peliculas/"),
                            HttpStatus.NOT_FOUND);
            return respuesta;
        } else {
            ResponseEntity<List<PeliculaDTO>> respuesta = new ResponseEntity<List<PeliculaDTO>>(
                    p, HttpStatus.OK
            );
            return respuesta;
        }

    }

    @GetMapping("/rating/{minRating}")
    public ResponseEntity<?> getByMinRating(
            @PathVariable String minRating
    ) {
        try {

            if (minRating == null || minRating.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            List<PeliculaDTO> p = peliculaService.getByMinRating(minRating);

            if(p == null) {
                ResponseEntity<ErrorGenerico> respuesta =
                        new ResponseEntity<>(
                                new ErrorGenerico("Películas no encontradas", "localhost:8080/peliculas/rating/{minRating}"),
                                HttpStatus.NOT_FOUND);
                return respuesta;
            } else {
                ResponseEntity<List<PeliculaDTO>> respuesta = new ResponseEntity<List<PeliculaDTO>>(
                        p, HttpStatus.OK
                );
                return respuesta;
            }

        } catch (NumberFormatException e) {
            ErrorGenerico error = new ErrorGenerico(
                    e.getMessage(),
                    "localhost:8080/peliculas/rating/"+minRating
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (BaseDeDatosException e) {
            ErrorGenerico error = new ErrorGenerico(
                    e.getMessage(),
                    "localhost:8080/peliculas/rating/"+minRating
            );
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public PeliculaDTO update(
            @RequestBody PeliculaDTO peliculaDTO,
            @PathVariable String id
    ) {

        if (id == null || id.isEmpty()) return null;

        PeliculaDTO p = peliculaService.update(id, peliculaDTO);

        if (p == null) {
            return null;
        } else {
            return p;
        }

    }

    @DeleteMapping("/{id}")
    public PeliculaDTO delete(
            @PathVariable String id
    ) {

        if (id == null || id.isEmpty()) return null;

        PeliculaDTO p = peliculaService.delete(id);

        if (p == null) {
            return null;
        } else {
            return p;
        }

    }
}