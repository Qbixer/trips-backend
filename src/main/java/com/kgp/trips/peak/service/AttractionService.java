package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.AttractionDTO;
import com.kgp.trips.peak.entity.DeprecatedAttraction;
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
@Deprecated
public class AttractionService {

    public class AttractionNotFoundException extends Exception {

    }

    @Autowired
    AttractionRepository attractionRepository;

    @Autowired
    InfrastructureTypeRepository infrastructureTypeRepository;

    public Set<AttractionDTO> getAllAttractionDTO() {
        List<DeprecatedAttraction> all = attractionRepository.findAll();
        Set<AttractionDTO> attractionDTOSet = new HashSet<>();

        for(DeprecatedAttraction deprecatedAttraction : all) {
            AttractionDTO attractionDTO = AttractionDTO.createAllFields(deprecatedAttraction);
            attractionDTOSet.add(attractionDTO);
        }
        return attractionDTOSet;
    }

    public AttractionDTO getAttractionDTO(Integer id) {
        Optional<DeprecatedAttraction> optionalAttraction = attractionRepository.findById(id);
        if(optionalAttraction.isPresent()) {
            DeprecatedAttraction deprecatedAttraction = optionalAttraction.get();
            AttractionDTO attractionDTO = AttractionDTO.createAllFields(deprecatedAttraction);
            return attractionDTO;
        } else {
            return null;
        }
    }


    public DeprecatedAttraction createAttraction(AttractionDTO attractionDTO) {
        DeprecatedAttraction deprecatedAttraction = DeprecatedAttraction.createFromDTO(attractionDTO);
        List<InfrastructureType> infrastructureTypes = infrastructureTypeRepository.findAllById(deprecatedAttraction.getInfrastructures().stream().map(InfrastructureType::getName).collect(Collectors.toSet()));
        deprecatedAttraction.setInfrastructures(new HashSet<>(infrastructureTypes));
        return attractionRepository.save(deprecatedAttraction);
    }

    public void deleteAttraction(Integer id) throws AttractionNotFoundException {
        Optional<DeprecatedAttraction> optionalAttraction = attractionRepository.findById(id);
        if(optionalAttraction.isEmpty()) {
            throw new AttractionNotFoundException();
        }
        DeprecatedAttraction deprecatedAttraction = optionalAttraction.get();
        attractionRepository.delete(deprecatedAttraction);
    }

    public DeprecatedAttraction updateAttraction(AttractionDTO attractionDTO) throws AttractionNotFoundException {
        Optional<DeprecatedAttraction> optionalAttraction = attractionRepository.findById(attractionDTO.getId());
        if(optionalAttraction.isEmpty()) {
            throw new AttractionNotFoundException();
        }
        DeprecatedAttraction deprecatedAttraction = DeprecatedAttraction.createFromDTO(attractionDTO);
        List<InfrastructureType> infrastructureTypes = infrastructureTypeRepository.findAllById(deprecatedAttraction.getInfrastructures().stream().map(InfrastructureType::getName).collect(Collectors.toSet()));
        deprecatedAttraction.setInfrastructures(new HashSet<>(infrastructureTypes));
        return attractionRepository.save(deprecatedAttraction);
    }
}
