package com.kgp.trips.trip.service;

import com.kgp.trips.trip.dto.AttractionDTO;
import com.kgp.trips.trip.dto.RegionDTO;
import com.kgp.trips.trip.entity.Attraction;
import com.kgp.trips.trip.entity.Region;
import com.kgp.trips.trip.repository.AttractionRepository;
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

        for(Attraction attraction : all) {
            AttractionDTO attractionDTO = createAttractionDTOFromAttraction(attraction);
            attractionDTOSet.add(attractionDTO);
        }
        return attractionDTOSet;
    }

    public AttractionDTO getAttractionDTO(Integer id) {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if(optionalAttraction.isPresent()) {
            Attraction attraction = optionalAttraction.get();
            return createAttractionDTOFromAttraction(attraction);
        } else {
            return null;
        }
    }


    public AttractionDTO createAttraction(AttractionDTO attractionDTO) {
        Attraction attraction = createAttractionFromAttractionDTO(attractionDTO);
        Attraction save = attractionRepository.save(attraction);
        return createAttractionDTOFromAttraction(save);
    }

    public void deleteAttraction(Integer id) throws AttractionNotFoundException {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if(optionalAttraction.isEmpty()) {
            throw new AttractionNotFoundException();
        }
        Attraction attraction = optionalAttraction.get();
        attractionRepository.delete(attraction);
    }

    public AttractionDTO updateAttraction(AttractionDTO attractionDTO) throws AttractionNotFoundException {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(attractionDTO.getId());
        if(optionalAttraction.isEmpty()) {
            throw new AttractionNotFoundException();
        }
        Attraction attraction = createAttractionFromAttractionDTO(attractionDTO);
        return createAttractionDTOFromAttraction(attractionRepository.save(attraction));
    }

    private AttractionDTO createAttractionDTOFromAttraction(Attraction attraction) {
        AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(attraction);
        if(attraction.getRegion() != null) {
            attractionDTO.setRegion(RegionDTO.createOnlyBasicFields(attraction.getRegion()));
        }
        return attractionDTO;
    }

    private Attraction createAttractionFromAttractionDTO(AttractionDTO attractionDTO) {
        Attraction attraction = Attraction.builder()
                .id(attractionDTO.getId())
                .name(attractionDTO.getName())
                .description(attractionDTO.getDescription())
                .build();
        if(attractionDTO.getRegion() != null) {
            Region region = new Region();
            region.setId(attractionDTO.getRegion().getId());
            attraction.setRegion(region);
        }
        return attraction;
    }
}
