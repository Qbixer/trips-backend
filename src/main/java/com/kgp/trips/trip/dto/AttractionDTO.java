package com.kgp.trips.trip.dto;

import com.kgp.trips.trip.entity.Attraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDTO {

    Integer id;
    String name;
    String description;

    RegionDTO region;

    public static AttractionDTO createOnlyBasicFields(Attraction attraction) {
        return AttractionDTO.builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .description(attraction.getDescription())
                .build();
    }
}
