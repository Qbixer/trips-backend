package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.Region;
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

    public static RegionDTO createOnlyBasicFields(Region region) {
        return RegionDTO.builder()
                .id(region.getId())
                .name(region.getName())
                .description(region.getDescription())
                .build();
    }
}
