package com.kgp.trips.trip.dto;

import com.kgp.trips.trip.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {

    Integer id;
    String name;
    String description;

    Set<AttractionDTO> attractions;
    Set<MountainRangeDTO> mountainRanges;
    Set<TripDTO> trips;

    public static RegionDTO createOnlyBasicFields(Region region) {
        return RegionDTO.builder()
                .id(region.getId())
                .name(region.getName())
                .description(region.getDescription())
                .build();
    }
}
