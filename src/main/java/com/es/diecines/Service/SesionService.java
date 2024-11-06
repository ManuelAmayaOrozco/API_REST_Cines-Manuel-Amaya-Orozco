package com.es.diecines.Service;

import com.es.diecines.DTO.PeliculaDTO;
import com.es.diecines.DTO.SesionDTO;
import com.es.diecines.Model.Pelicula;
import com.es.diecines.Model.Sesion;
import com.es.diecines.Repository.PeliculaRepository;
import com.es.diecines.Repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SesionService {

    private final SesionRepository sesionRepository;
    private final PeliculaRepository peliculaRepository;

    public SesionService(SesionRepository sesionRepository, PeliculaRepository peliculaRepository) {
        this.sesionRepository = sesionRepository;
        this.peliculaRepository = peliculaRepository;
    }

    public List<SesionDTO> getAll() {

        List<SesionDTO> listaDeDTOs = new ArrayList<>();

        List<Sesion> listaSes = sesionRepository.findAll();

        for (Sesion s: listaSes) {

            listaDeDTOs.add(mapToDTO(s));

        }

        return listaDeDTOs;

    }

    public SesionDTO create(SesionDTO sesionDTO) {
        Sesion sesion = mapToSesion(sesionDTO);

        sesion = sesionRepository.save(sesion);

        return mapToDTO(sesion);
    }

    public SesionDTO update(String id, SesionDTO sesionDTO) {

        // Parsear el id a Long
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.out.println("Error al parsear el id");
            return null;
        }

        // Compruebo que la sesion existe en la BDD
        Sesion s = sesionRepository.findById(idL).orElse(null);

        if (s == null) {
            return null;
        } else {
            Sesion newS = mapToSesion(sesionDTO);

            newS.setId(s.getId());

            sesionRepository.save(newS);

            return mapToDTO(newS);
        }

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
