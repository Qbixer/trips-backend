package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.AttractionDTO;
import com.kgp.trips.peak.dto.InfrastructureTypeDTO;
import com.kgp.trips.peak.dto.MountainRangeDTO;
import com.kgp.trips.peak.dto.RegionDTO;
import com.kgp.trips.peak.entity.DeprecatedAttraction;
import com.kgp.trips.peak.entity.DeprecatedRegion;
import com.kgp.trips.peak.entity.InfrastructureType;
import com.kgp.trips.peak.entity.DeprecatedMountainRange;
import com.kgp.trips.peak.repository.DeprecatedRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Deprecated
public class RegionService {

    @Autowired
    DeprecatedRegionRepository deprecatedRegionRepository;

    public Set<RegionDTO> getAllRegionDTO() {
        List<DeprecatedRegion> all = deprecatedRegionRepository.findAll();
        Set<RegionDTO> regionDTOSet = new HashSet<>();

        for(DeprecatedRegion deprecatedRegion : all) {
            RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(deprecatedRegion);
            regionDTO.setMountainRanges(null);
            regionDTO.setAttractions(null);
            regionDTOSet.add(regionDTO);
        }
        return regionDTOSet;
    }

    public RegionDTO getRegionDTO(Integer id) {
        Optional<DeprecatedRegion> optionalRegionDTO = deprecatedRegionRepository.findById(id);
        if(optionalRegionDTO.isPresent()) {
            DeprecatedRegion deprecatedRegion = optionalRegionDTO.get();
            RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(deprecatedRegion);
            Set<MountainRangeDTO> mountainRangeDTOSet = new HashSet<>();

            for(DeprecatedMountainRange deprecatedMountainRange : deprecatedRegion.getDeprecatedMountainRanges()) {
                MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(deprecatedMountainRange);
                mountainRangeDTOSet.add(mountainRangeDTO);
            }
            regionDTO.setMountainRanges(mountainRangeDTOSet);

            Set<AttractionDTO> attractionDTOSet = new HashSet<>();
            for(DeprecatedAttraction deprecatedAttraction : deprecatedRegion.getDeprecatedAttractions()) {
                AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(deprecatedAttraction);
                Set<InfrastructureTypeDTO> infrastructureTypeDTOSet = new HashSet<>();
                for(InfrastructureType infrastructureType: deprecatedAttraction.getInfrastructures()) {
                    InfrastructureTypeDTO infrastructureTypeDTO = InfrastructureTypeDTO.createOnlyBasicFields(infrastructureType);
                    infrastructureTypeDTOSet.add(infrastructureTypeDTO);
                }
                attractionDTO.setInfrastructures(infrastructureTypeDTOSet);
                attractionDTOSet.add(attractionDTO);
            }
            regionDTO.setAttractions(attractionDTOSet);
            return regionDTO;
        } else {
            return null;
        }

    }

}
