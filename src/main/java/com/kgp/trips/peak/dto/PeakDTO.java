package com.kgp.trips.peak.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PeakDTO {

    Integer id;
    String name;
    String description;
    Float height;
    Boolean stamp;

    Set<RouteDTO> routes;

    MountainRangeDTO mountainRange;
}
