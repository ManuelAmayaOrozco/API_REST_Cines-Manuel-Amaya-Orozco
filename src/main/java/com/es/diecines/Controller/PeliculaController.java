package com.es.diecines.Controller;

import com.es.diecines.DTO.PeliculaDTO;
import com.es.diecines.Service.PeliculaService;
import org.springframework.web.bind.annotation.*;

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

    // Implementar otros endpoints...
}