package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.DeprecatedAttraction;
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
@Deprecated
public class AttractionDTO {

    Integer id;
    String name;
    String description;

    Set<InfrastructureTypeDTO> infrastructures;

    RegionDTO region;

    public static AttractionDTO createOnlyBasicFields(DeprecatedAttraction deprecatedAttraction) {
        return AttractionDTO.builder()
                .id(deprecatedAttraction.getId())
                .name(deprecatedAttraction.getName())
                .description(deprecatedAttraction.getDescription())
                .build();
    }

    public static AttractionDTO createAllFields(DeprecatedAttraction deprecatedAttraction) {
        AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(deprecatedAttraction);
        attractionDTO.setInfrastructures(deprecatedAttraction.getInfrastructures().stream().map(InfrastructureTypeDTO::createOnlyBasicFields).collect(Collectors.toSet()));
        attractionDTO.setRegion(deprecatedAttraction.getDeprecatedRegion()!= null ? RegionDTO.createOnlyBasicFields(deprecatedAttraction.getDeprecatedRegion()) : null);
        return attractionDTO;
    }
}
