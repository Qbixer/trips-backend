package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.AttractionDTO;
import com.kgp.trips.peak.entity.Attraction;
import com.kgp.trips.peak.entity.InfrastructureType;
import com.kgp.trips.peak.repository.AttractionRepository;
import com.kgp.trips.peak.repository.InfrastructureTypeRepository;
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

    @Autowired
    InfrastructureTypeRepository infrastructureTypeRepository;

    public Set<AttractionDTO> getAllAttractionDTO() {
        List<Attraction> all = attractionRepository.findAll();
        Set<AttractionDTO> attractionDTOSet = new HashSet<>();

        for(Attraction attraction: all) {
            AttractionDTO attractionDTO = AttractionDTO.createAllFields(attraction);
            attractionDTOSet.add(attractionDTO);
        }
        return attractionDTOSet;
    }

    public AttractionDTO getAttractionDTO(Integer id) {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if(optionalAttraction.isPresent()) {
            Attraction attraction = optionalAttraction.get();
            AttractionDTO attractionDTO = AttractionDTO.createAllFields(attraction);
            return attractionDTO;
        } else {
            return null;
        }
    }


    public Attraction createAttraction(AttractionDTO attractionDTO) {
        Attraction attraction = Attraction.createFromDTO(attractionDTO);
        List<InfrastructureType> infrastructureTypes = infrastructureTypeRepository.findAllById(attraction.getInfrastructures().stream().map(InfrastructureType::getName).collect(Collectors.toSet()));
        attraction.setInfrastructures(new HashSet<>(infrastructureTypes));
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
        Attraction attraction = Attraction.createFromDTO(attractionDTO);
        List<InfrastructureType> infrastructureTypes = infrastructureTypeRepository.findAllById(attraction.getInfrastructures().stream().map(InfrastructureType::getName).collect(Collectors.toSet()));
        attraction.setInfrastructures(new HashSet<>(infrastructureTypes));
        return attractionRepository.save(attraction);
    }
}
