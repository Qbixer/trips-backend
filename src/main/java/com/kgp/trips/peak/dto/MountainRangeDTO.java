package com.kgp.trips.peak.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class MountainRangeDTO {

    Integer id;
    String name;
    String description;

    Set<PeakDTO> peaks;

    RegionDTO region;
}
