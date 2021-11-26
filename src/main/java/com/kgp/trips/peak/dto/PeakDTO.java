package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.Peak;
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

    public static PeakDTO createOnlyBasicFields(Peak peak) {
        return PeakDTO.builder()
                .id(peak.getId())
                .name(peak.getName())
                .description(peak.getDescription())
                .height(peak.getHeight())
                .stamp(peak.getStamp())
                .build();
    }
}
