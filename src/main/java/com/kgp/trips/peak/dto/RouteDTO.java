package com.kgp.trips.peak.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RouteDTO {

    Integer id;
    String name;
    String description;

    Set<PointDTO> points;

    Set<PeakDTO> peaks;
}
