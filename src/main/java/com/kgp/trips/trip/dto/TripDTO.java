package com.kgp.trips.trip.dto;

import com.kgp.trips.trip.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    Integer id;
    String name;
    String description;
    LocalDateTime date;
    String mapaTurystycznaLink;

    RegionDTO region;
    Set<MountainRangeDTO> mountainRanges;
    Set<PeakDTO> peaks;

    //TODO photos
    public static TripDTO createOnlyBasicFields(Trip trip) {
        return TripDTO.builder()
                .id(trip.getId())
                .name(trip.getName())
                .description(trip.getDescription())
                .date(trip.getDate())
                .mapaTurystycznaLink(trip.getMapaTurystycznaLink())
                .build();
    }
}
