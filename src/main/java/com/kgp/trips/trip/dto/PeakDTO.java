package com.kgp.trips.trip.dto;

import com.kgp.trips.trip.entity.Peak;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeakDTO {

    Integer id;
    String name;
    String description;
    Integer height;

    MountainRangeDTO mountainRange;
    Set<TripDTO> trips;

    public static PeakDTO createOnlyBasicFields(Peak peak) {
        return PeakDTO.builder()
                .id(peak.getId())
                .name(peak.getName())
                .description(peak.getDescription())
                .height(peak.getHeight())
                .build();
    }
}
