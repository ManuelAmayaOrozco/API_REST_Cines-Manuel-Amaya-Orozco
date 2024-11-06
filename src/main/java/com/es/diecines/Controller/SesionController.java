package com.es.diecines.Controller;

import com.es.diecines.DTO.SesionDTO;
import com.es.diecines.Service.SesionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sesiones")
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @PostMapping
    public SesionDTO createSesion(
            @RequestBody SesionDTO sesionDTO
    ) {
        return sesionService.create(sesionDTO);
    }

    // Implementar otros endpoints...
}
