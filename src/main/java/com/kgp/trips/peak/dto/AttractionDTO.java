package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.Attraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDTO {

    Integer id;
    String name;
    String description;

    Set<InfrastructureTypeDTO> infrastructures;

    RegionDTO region;

    public static AttractionDTO createOnlyBasicFields(Attraction attraction) {
        return AttractionDTO.builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .description(attraction.getDescription())
                .build();
    }

    public static AttractionDTO createAllFields(Attraction attraction) {
        AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(attraction);
        attractionDTO.setInfrastructures(attraction.getInfrastructures().stream().map(InfrastructureTypeDTO::createOnlyBasicFields).collect(Collectors.toSet()));
        attractionDTO.setRegion(RegionDTO.createOnlyBasicFields(attraction.getRegion()));
        return attractionDTO;
    }
}
