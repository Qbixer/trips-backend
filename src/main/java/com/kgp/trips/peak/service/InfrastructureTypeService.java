package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.InfrastructureTypeDTO;
import com.kgp.trips.peak.entity.InfrastructureType;
import com.kgp.trips.peak.repository.InfrastructureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InfrastructureTypeService {

    public class InfrastructureTypeNotFoundException extends Exception {

    }

    @Autowired
    InfrastructureTypeRepository infrastructureTypeRepository;

    public Set<InfrastructureTypeDTO> getAllInfrastructureTypeDTO() {
        List<InfrastructureType> all = infrastructureTypeRepository.findAll();
        Set<InfrastructureTypeDTO> infrastructureTypeDTOSet = new HashSet<>();

        for(InfrastructureType infrastructureType: all) {
            InfrastructureTypeDTO infrastructureTypeDTO = InfrastructureTypeDTO.createOnlyBasicFields(infrastructureType);
            infrastructureTypeDTOSet.add(infrastructureTypeDTO);
        }
        return infrastructureTypeDTOSet;
    }

    public InfrastructureType createInfrastructureType(InfrastructureTypeDTO infrastructureTypeDTO) {
        return infrastructureTypeRepository.save(InfrastructureType.createFromDTO(infrastructureTypeDTO));
    }

    public void deleteInfrastructureType(String id) throws InfrastructureTypeNotFoundException {
        Optional<InfrastructureType> optionalInfrastructureType = infrastructureTypeRepository.findById(id);
        if(optionalInfrastructureType.isEmpty()) {
            throw new InfrastructureTypeNotFoundException();
        }
        InfrastructureType infrastructureType = optionalInfrastructureType.get();
        infrastructureTypeRepository.delete(infrastructureType);
    }
}
