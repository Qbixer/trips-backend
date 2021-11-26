package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.Attraction;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
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
}