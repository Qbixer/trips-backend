package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.Route;
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

    public static RouteDTO createOnlyBasicFields(Route route) {
        return RouteDTO.builder()
                .id(route.getId())
                .name(route.getName())
                .description(route.getDescription())
                .build();
    }
}
