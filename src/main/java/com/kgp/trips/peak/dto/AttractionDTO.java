package com.kgp.trips.peak.dto;

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
}
