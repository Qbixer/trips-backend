package com.kgp.trips.trip.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgp.trips.trip.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    Integer id;
    String name;
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime date;
    String mapaTurystycznaLink;

    RegionDTO region;
    Set<MountainRangeDTO> mountainRanges;
    Set<PeakDTO> peaks;
    Set<PhotoDTO> photos;

    public static TripDTO createOnlyBasicFields(Trip trip) {
        return TripDTO.builder()
                .id(trip.getId())
                .name(trip.getName())
                .description(trip.getDescription())
                .date(trip.getDate())
                .mapaTurystycznaLink(trip.getMapaTurystycznaLink())
                .build();
    }

    public static TripDTO createTripDTOFromTrip(Trip trip) {
        TripDTO tripDTO = TripDTO.createOnlyBasicFields(trip);

        if(trip.getRegion() != null) {
            tripDTO.setRegion(RegionDTO.createOnlyBasicFields(trip.getRegion()));
        }

        Set<MountainRangeDTO> mountainRangeDTOSet = new HashSet<>();
        if(trip.getMountainRanges() != null) {
            for(MountainRange mountainRange : trip.getMountainRanges()) {
                MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(mountainRange);
                mountainRangeDTOSet.add(mountainRangeDTO);
            }
        }
        tripDTO.setMountainRanges(mountainRangeDTOSet);

        Set<PeakDTO> peakDTOSet = new HashSet<>();
        if(trip.getPeaks() != null) {
            for (Peak peak : trip.getPeaks()) {
                PeakDTO peakDTO = PeakDTO.createOnlyBasicFields(peak);
                peakDTOSet.add(peakDTO);
            }
        }
        tripDTO.setPeaks(peakDTOSet);

        return tripDTO;
    }
}
