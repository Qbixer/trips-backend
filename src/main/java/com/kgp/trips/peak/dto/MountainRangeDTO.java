package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.DeprecatedMountainRange;
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
public class MountainRangeDTO {

    Integer id;
    String name;
    String description;

    Set<PeakDTO> peaks;

    RegionDTO region;

    public static MountainRangeDTO createOnlyBasicFields(DeprecatedMountainRange deprecatedMountainRange) {
        return MountainRangeDTO.builder()
                .id(deprecatedMountainRange.getId())
                .name(deprecatedMountainRange.getName())
                .description(deprecatedMountainRange.getDescription())
                .build();
    }
}
