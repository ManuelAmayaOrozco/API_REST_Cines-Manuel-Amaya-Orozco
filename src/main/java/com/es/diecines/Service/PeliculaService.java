package com.es.diecines.Service;

import com.es.diecines.DTO.PeliculaDTO;
import com.es.diecines.Model.Pelicula;
import com.es.diecines.Repository.PeliculaRepository;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public PeliculaDTO create(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = mapToPelicula(peliculaDTO);

        return mapToDTO(pelicula);
    }

    public PeliculaDTO getById(String id) {

        // Parsear el id a Long
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.out.println("Error al parsear el id");
            return null;
        }

        Pelicula p = peliculaRepository.findById(idL).orElse(null);

        return mapToDTO(p);
    }

    private PeliculaDTO mapToDTO(Pelicula pelicula) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(pelicula.getId());
        peliculaDTO.setTitle(pelicula.getTitle());
        peliculaDTO.setDirector(pelicula.getDirector());
        peliculaDTO.setTime(pelicula.getTime());
        peliculaDTO.setTrailer(pelicula.getTrailer());
        peliculaDTO.setPosterImage(pelicula.getPosterImage());
        peliculaDTO.setScreenshot(pelicula.getScreenshot());
        peliculaDTO.setSynopsis(pelicula.getSynopsis());
        peliculaDTO.setRating(pelicula.getRating());
        return peliculaDTO;
    }

    private Pelicula mapToPelicula(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitle(peliculaDTO.getTitle());
        pelicula.setDirector(peliculaDTO.getDirector());
        pelicula.setTime(peliculaDTO.getTime());
        pelicula.setTrailer(peliculaDTO.getTrailer());
        pelicula.setPosterImage(peliculaDTO.getPosterImage());
        pelicula.setScreenshot(peliculaDTO.getScreenshot());
        pelicula.setSynopsis(peliculaDTO.getSynopsis());
        pelicula.setRating(peliculaDTO.getRating());
        pelicula = peliculaRepository.save(pelicula);
        return pelicula;
    }

}
