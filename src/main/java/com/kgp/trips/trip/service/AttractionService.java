package com.kgp.trips.trip.service;

import com.kgp.trips.peak.entity.DeprecatedAttraction;
import com.kgp.trips.peak.entity.InfrastructureType;
import com.kgp.trips.peak.service.DeprecatedAttractionService;
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
import java.util.stream.Collectors;

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
            AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(attraction);
            if(attraction.getRegion() != null) {
                attractionDTO.setRegion(RegionDTO.createOnlyBasicFields(attraction.getRegion()));
            }
            attractionDTOSet.add(attractionDTO);
        }
        return attractionDTOSet;
    }

    public AttractionDTO getAttractionDTO(Integer id) {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if(optionalAttraction.isPresent()) {
            Attraction attraction = optionalAttraction.get();
            AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(attraction);
            if(attraction.getRegion() != null) {
                attractionDTO.setRegion(RegionDTO.createOnlyBasicFields(attraction.getRegion()));
            }
            return attractionDTO;
        } else {
            return null;
        }
    }


    public Attraction createAttraction(AttractionDTO attractionDTO) {
        Attraction attraction = createAttractionFromDTO(attractionDTO);
        return attractionRepository.save(attraction);
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
        Attraction attraction = createAttractionFromDTO(attractionDTO);
        return attractionRepository.save(attraction);
    }

    private Attraction createAttractionFromDTO(AttractionDTO attractionDTO) {
        Attraction attraction = Attraction.builder()
                .id(attractionDTO.getId())
                .name(attractionDTO.getName())
                .description(attractionDTO.getDescription())
                .build();
        if(attractionDTO.getRegion() != null) {
            Region region = new Region();
            region.setId(attractionDTO.getRegion().getId());
        }
        return attraction;
    }
}
