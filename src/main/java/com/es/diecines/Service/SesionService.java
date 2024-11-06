package com.es.diecines.Service;

import com.es.diecines.DTO.SesionDTO;
import com.es.diecines.Model.Sesion;
import com.es.diecines.Repository.PeliculaRepository;
import com.es.diecines.Repository.SesionRepository;
import org.springframework.stereotype.Service;

@Service
public class SesionService {

    private final SesionRepository sesionRepository;
    private final PeliculaRepository peliculaRepository;

    public SesionService(SesionRepository sesionRepository, PeliculaRepository peliculaRepository) {
        this.sesionRepository = sesionRepository;
        this.peliculaRepository = peliculaRepository;
    }

    public SesionDTO create(SesionDTO sesionDTO) {
        Sesion sesion = mapToSesion(sesionDTO);

        sesion = sesionRepository.save(sesion);

        return mapToDTO(sesion);
    }

    private SesionDTO mapToDTO(Sesion sesion) {
        SesionDTO sesionDTO = new SesionDTO();
        sesionDTO.setId(sesion.getId());
        sesionDTO.setMovieId(sesion.getPelicula().getId());
        sesionDTO.setRoomId(sesion.getRoomId());
        sesionDTO.setDate(sesion.getDate());
        return sesionDTO;
    }

    private Sesion mapToSesion(SesionDTO sesionDTO) {
        Sesion sesion = new Sesion();
        sesion.setPelicula(peliculaRepository.getReferenceById(sesionDTO.getMovieId()));
        sesion.setRoomId(sesionDTO.getRoomId());
        sesion.setDate(sesionDTO.getDate());
        return sesion;

    }

}
