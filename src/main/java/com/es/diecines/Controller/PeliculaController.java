package com.es.diecines.Controller;

import com.es.diecines.DTO.PeliculaDTO;
import com.es.diecines.Service.PeliculaService;
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
    public PeliculaDTO getById(
            @PathVariable String id
    ) {
        if (id == null || id.isEmpty()) return null;

        PeliculaDTO p = peliculaService.getById(id);

        if (p == null) {
            return null;
        } else {
            return p;
        }
    }

    @GetMapping
    public List<PeliculaDTO> getAll() {

        return peliculaService.getAll();

    }

    @GetMapping("/rating/{minRating}")
    public List<PeliculaDTO> getByMinRating(
            @PathVariable String minRating
    ) {
        if (minRating == null || minRating.isEmpty()) return null;

        List<PeliculaDTO> p = peliculaService.getByMinRating(minRating);

        if (p == null) {
            return null;
        } else {
            return p;
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