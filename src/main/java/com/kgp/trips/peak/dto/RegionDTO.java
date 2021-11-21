package com.kgp.trips.peak.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RegionDTO {

    Integer id;
    String name;
    String description;

    Set<AttractionDTO> attractions;

    Set<MountainRangeDTO> mountainRanges;
}
