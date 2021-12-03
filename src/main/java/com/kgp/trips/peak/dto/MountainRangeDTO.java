package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.MountainRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MountainRangeDTO {

    Integer id;
    String name;
    String description;

    Set<PeakDTO> peaks;

    RegionDTO region;

    public static MountainRangeDTO createOnlyBasicFields(MountainRange mountainRange) {
        return MountainRangeDTO.builder()
                .id(mountainRange.getId())
                .name(mountainRange.getName())
                .description(mountainRange.getDescription())
                .build();
    }
}
