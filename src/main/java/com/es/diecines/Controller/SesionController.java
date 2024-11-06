package com.es.diecines.Controller;

import com.es.diecines.DTO.PeliculaDTO;
import com.es.diecines.DTO.SesionDTO;
import com.es.diecines.Service.SesionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sesiones")
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @PostMapping
    public SesionDTO create(
            @RequestBody SesionDTO sesionDTO
    ) {
        return sesionService.create(sesionDTO);
    }

    @GetMapping
    public List<SesionDTO> getAll() {

        return sesionService.getAll();

    }

    @PutMapping("/{id}")
    public SesionDTO update(
            @RequestBody SesionDTO sesionDTO,
            @PathVariable String id
    ) {

        if (id == null || id.isEmpty()) return null;

        SesionDTO s = sesionService.update(id, sesionDTO);

        if (s == null) {
            return null;
        } else {
            return s;
        }

    }

}
