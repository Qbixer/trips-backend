package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.AttractionDTO;
import com.kgp.trips.peak.entity.DeprecatedAttraction;
import com.kgp.trips.peak.entity.InfrastructureType;
import com.kgp.trips.peak.repository.DeprecatedAttractionRepository;
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
public class DeprecatedAttractionService {

    public class AttractionNotFoundException extends Exception {

    }

    @Autowired
    DeprecatedAttractionRepository deprecatedAttractionRepository;

    @Autowired
    InfrastructureTypeRepository infrastructureTypeRepository;

    public Set<AttractionDTO> getAllAttractionDTO() {
        List<DeprecatedAttraction> all = deprecatedAttractionRepository.findAll();
        Set<AttractionDTO> attractionDTOSet = new HashSet<>();

        for(DeprecatedAttraction deprecatedAttraction : all) {
            AttractionDTO attractionDTO = AttractionDTO.createAllFields(deprecatedAttraction);
            attractionDTOSet.add(attractionDTO);
        }
        return attractionDTOSet;
    }

    public AttractionDTO getAttractionDTO(Integer id) {
        Optional<DeprecatedAttraction> optionalAttraction = deprecatedAttractionRepository.findById(id);
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
        return deprecatedAttractionRepository.save(deprecatedAttraction);
    }

    public void deleteAttraction(Integer id) throws AttractionNotFoundException {
        Optional<DeprecatedAttraction> optionalAttraction = deprecatedAttractionRepository.findById(id);
        if(optionalAttraction.isEmpty()) {
            throw new AttractionNotFoundException();
        }
        DeprecatedAttraction deprecatedAttraction = optionalAttraction.get();
        deprecatedAttractionRepository.delete(deprecatedAttraction);
    }

    public DeprecatedAttraction updateAttraction(AttractionDTO attractionDTO) throws AttractionNotFoundException {
        Optional<DeprecatedAttraction> optionalAttraction = deprecatedAttractionRepository.findById(attractionDTO.getId());
        if(optionalAttraction.isEmpty()) {
            throw new AttractionNotFoundException();
        }
        DeprecatedAttraction deprecatedAttraction = DeprecatedAttraction.createFromDTO(attractionDTO);
        List<InfrastructureType> infrastructureTypes = infrastructureTypeRepository.findAllById(deprecatedAttraction.getInfrastructures().stream().map(InfrastructureType::getName).collect(Collectors.toSet()));
        deprecatedAttraction.setInfrastructures(new HashSet<>(infrastructureTypes));
        return deprecatedAttractionRepository.save(deprecatedAttraction);
    }
}
