package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.AttractionDTO;
import com.kgp.trips.peak.entity.Attraction;
import com.kgp.trips.peak.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AttractionService {

    public class AttractionNotFoundException extends Exception {

    }

    @Autowired
    AttractionRepository attractionRepository;

    public Set<AttractionDTO> getAllAttractionDTO() {
        List<Attraction> all = attractionRepository.findAll();
        Set<AttractionDTO> attractionDTOSet = new HashSet<>();

        for(Attraction attraction: all) {
            AttractionDTO attractionDTO = AttractionDTO.createAllFields(attraction);
            attractionDTOSet.add(attractionDTO);
        }
        return attractionDTOSet;
    }


    public Attraction createAttraction(AttractionDTO attractionDTO) {
        return attractionRepository.save(Attraction.createFromDTO(attractionDTO));
    }

    public void deleteAttraction(Integer id) throws AttractionNotFoundException {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if(optionalAttraction.isEmpty()) {
            throw new AttractionNotFoundException();
        }
        Attraction attraction = optionalAttraction.get();
        attractionRepository.delete(attraction);
    }

    public Attraction updateAttraction(AttractionDTO attractionDTO) throws AttractionNotFoundException {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(attractionDTO.getId());
        if(optionalAttraction.isEmpty()) {
            throw new AttractionNotFoundException();
        }
        return attractionRepository.save(Attraction.createFromDTO(attractionDTO));
    }
}
