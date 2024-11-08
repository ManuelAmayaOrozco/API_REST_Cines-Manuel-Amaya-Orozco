package com.es.diecines.Service;

import com.es.diecines.DTO.PeliculaDTO;
import com.es.diecines.Model.Pelicula;
import com.es.diecines.Model.Sesion;
import com.es.diecines.Repository.PeliculaRepository;
import com.es.diecines.Repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final SesionRepository sesionRepository;

    public PeliculaService(PeliculaRepository peliculaRepository, SesionRepository sesionRepository) {
        this.peliculaRepository = peliculaRepository;
        this.sesionRepository = sesionRepository;
    }

    public PeliculaDTO create(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = mapToPelicula(peliculaDTO);

        pelicula = peliculaRepository.save(pelicula);

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

        if (p == null) {
            return null;
        } else {
            return mapToDTO(p);
        }

    }

    public List<PeliculaDTO> getAll() {

        List<PeliculaDTO> listaDeDTOs = new ArrayList<>();

        List<Pelicula> listaPel = peliculaRepository.findAll();

        for (Pelicula p: listaPel) {

            listaDeDTOs.add(mapToDTO(p));

        }

        return listaDeDTOs;
    }

    public List<PeliculaDTO> getByMinRating(String minRating) {

        // Parsear el minRating a Double
        Double minRatingD = 0.0;
        try {
            minRatingD = Double.parseDouble(minRating);
        } catch (NumberFormatException e) {
            System.out.println("Error al parsear el id");
            return null;
        }

        List<PeliculaDTO> listaDeDTOs = new ArrayList<>();

        List<Pelicula> listaPel = peliculaRepository.findAll();

        for (Pelicula p: listaPel) {

            if (p.getRating() > minRatingD) {

                listaDeDTOs.add(mapToDTO(p));

            }

        }

        return listaDeDTOs;
    }

    public PeliculaDTO update(String id, PeliculaDTO peliculaDTO) {

        // Parsear el id a Long
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.out.println("Error al parsear el id");
            return null;
        }

        // Compruebo que la pelicula existe en la BDD
        Pelicula p = peliculaRepository.findById(idL).orElse(null);

        if (p == null) {
            return null;
        } else {
            Pelicula newP = mapToPelicula(peliculaDTO);

            newP.setId(p.getId());

            peliculaRepository.save(newP);

            return mapToDTO(newP);
        }
    }

    public PeliculaDTO delete(String id) {

        // Parsear el id a Long
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.out.println("Error al parsear el id");
            return null;
        }

        // Compruebo que la pelicula existe en la BDD
        Pelicula p = peliculaRepository.findById(idL).orElse(null);

        if (p == null) {
            return null;
        } else {
            List<Sesion> sesiones = sesionRepository.findAll();

            for (Sesion s: sesiones) {

                if (s.getPelicula().getId() == idL) {

                    sesionRepository.delete(s);

                }

            }

            PeliculaDTO peliculaDTO = mapToDTO(p);

            peliculaRepository.delete(p);

            return peliculaDTO;
        }
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
        return pelicula;
    }

}
