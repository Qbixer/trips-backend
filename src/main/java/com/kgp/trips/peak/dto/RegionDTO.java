package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.DeprecatedRegion;
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
public class RegionDTO {

    Integer id;
    String name;
    String description;

    Set<AttractionDTO> attractions;

    Set<MountainRangeDTO> mountainRanges;

    public static RegionDTO createOnlyBasicFields(DeprecatedRegion deprecatedRegion) {
        return RegionDTO.builder()
                .id(deprecatedRegion.getId())
                .name(deprecatedRegion.getName())
                .description(deprecatedRegion.getDescription())
                .build();
    }
}
