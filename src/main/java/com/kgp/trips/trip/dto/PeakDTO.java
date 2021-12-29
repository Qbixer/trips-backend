package com.kgp.trips.trip.dto;

import com.kgp.trips.trip.entity.Peak;
import com.kgp.trips.trip.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    Boolean isKGP;

    MountainRangeDTO mountainRange;
    Set<TripDTO> trips;

    public static PeakDTO createOnlyBasicFields(Peak peak) {
        return PeakDTO.builder()
                .id(peak.getId())
                .name(peak.getName())
                .description(peak.getDescription())
                .height(peak.getHeight())
                .isKGP(peak.getIsKGP())
                .build();
    }

    public static PeakDTO createPeakDTOFromPeak(Peak peak) {
        PeakDTO peakDTO = PeakDTO.createOnlyBasicFields(peak);

        if(peak.getMountainRange() != null) {
            peakDTO.setMountainRange(MountainRangeDTO.createOnlyBasicFields(peak.getMountainRange()));
        }

        Set<TripDTO> tripDTOSet = new HashSet<>();
        if(peak.getTrips() != null) {
            for(Trip trip : peak.getTrips()) {
                TripDTO tripDTO = TripDTO.createOnlyBasicFields(trip);
                tripDTOSet.add(tripDTO);
            }
        }
        peakDTO.setTrips(tripDTOSet);
        return peakDTO;
    }
}
