package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.DeprecatedPeak;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class PeakDTO {

    Integer id;
    String name;
    String description;
    Float height;
    Boolean stamp;

    Set<RouteDTO> routes;

    MountainRangeDTO mountainRange;

    public static PeakDTO createOnlyBasicFields(DeprecatedPeak deprecatedPeak) {
        return PeakDTO.builder()
                .id(deprecatedPeak.getId())
                .name(deprecatedPeak.getName())
                .description(deprecatedPeak.getDescription())
                .height(deprecatedPeak.getHeight())
                .stamp(deprecatedPeak.getStamp())
                .build();
    }
}
