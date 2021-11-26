package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.AttractionDTO;
import com.kgp.trips.peak.dto.InfrastructureTypeDTO;
import com.kgp.trips.peak.dto.MountainRangeDTO;
import com.kgp.trips.peak.dto.RegionDTO;
import com.kgp.trips.peak.entity.Attraction;
import com.kgp.trips.peak.entity.InfrastructureType;
import com.kgp.trips.peak.entity.MountainRange;
import com.kgp.trips.peak.entity.Region;
import com.kgp.trips.peak.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public Set<RegionDTO> getAllRegionDTO() {
        List<Region> all = regionRepository.findAll();
        Set<RegionDTO> regionDTOSet = new HashSet<>();

        for(Region region: all) {
            RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(region);
            regionDTO.setMountainRanges(null);
            regionDTO.setAttractions(null);
            regionDTOSet.add(regionDTO);
        }
        return regionDTOSet;
    }

    public RegionDTO getRegionDTO(Integer id) {
        Optional<Region> optionalRegionDTO = regionRepository.findById(id);
        if(optionalRegionDTO.isPresent()) {
            Region region = optionalRegionDTO.get();
            RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(region);
            Set<MountainRangeDTO> mountainRangeDTOSet = new HashSet<>();

            for(MountainRange mountainRange: region.getMountainRanges()) {
                MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(mountainRange);
                mountainRangeDTOSet.add(mountainRangeDTO);
            }
            regionDTO.setMountainRanges(mountainRangeDTOSet);

            Set<AttractionDTO> attractionDTOSet = new HashSet<>();
            for(Attraction attraction: region.getAttractions()) {
                AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(attraction);
                Set<InfrastructureTypeDTO> infrastructureTypeDTOSet = new HashSet<>();
                for(InfrastructureType infrastructureType: attraction.getInfrastructures()) {
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
