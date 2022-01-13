package com.kgp.trips.trip.dto;

import com.kgp.trips.trip.entity.MountainRange;
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
public class MountainRangeDTO {

    Integer id;
    String name;
    String description;

    RegionDTO region;
    Set<PeakDTO> peaks;
    Set<TripDTO> trips;
    PhotoDTO photo;

    public static MountainRangeDTO createOnlyBasicFields(MountainRange mountainRange) {
        return MountainRangeDTO.builder()
                .id(mountainRange.getId())
                .name(mountainRange.getName())
                .description(mountainRange.getDescription())
                .build();

    }

    public static MountainRangeDTO createMountainRangeDTOFromMountainRange(MountainRange mountainRange) {
        MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(mountainRange);

        if (mountainRange.getRegion() != null) {
            mountainRangeDTO.setRegion(RegionDTO.createOnlyBasicFields(mountainRange.getRegion()));
        }

        Set<PeakDTO> peakDTOSet = new HashSet<>();
        if (mountainRange.getPeaks() != null) {
            for (Peak peak : mountainRange.getPeaks()) {
                PeakDTO peakDTO = PeakDTO.createOnlyBasicFields(peak);
                peakDTOSet.add(peakDTO);
            }
        }
        mountainRangeDTO.setPeaks(peakDTOSet);

        Set<TripDTO> tripDTOSet = new HashSet<>();
        if (mountainRange.getTrips() != null) {
            for(Trip trip : mountainRange.getTrips()) {
                TripDTO tripDTO = TripDTO.createOnlyBasicFields(trip);
                tripDTOSet.add(tripDTO);
            }
        }
        mountainRangeDTO.setTrips(tripDTOSet);

        if (mountainRange.getPhoto() != null) {
            mountainRangeDTO.setPhoto(PhotoDTO.createPhotoDTOFromPhoto(mountainRange.getPhoto()));
        }

        return mountainRangeDTO;
    }
}
