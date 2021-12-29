package com.kgp.trips.trip.dto;

import com.kgp.trips.trip.entity.*;
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

    public static RegionDTO createRegionDTOFromRegion(Region region) {
        RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(region);

        Set<AttractionDTO> attractionDTOSet = new HashSet<>();
        if(region.getAttractions() != null) {
            for (Attraction attraction : region.getAttractions()) {
                AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(attraction);
                attractionDTOSet.add(attractionDTO);
            }
        }
        regionDTO.setAttractions(attractionDTOSet);

        Set<TripDTO> tripDTOSet = new HashSet<>();
        if(region.getTrips() != null) {
            for(Trip trip : region.getTrips()) {
                TripDTO tripDTO = TripDTO.createOnlyBasicFields(trip);
                tripDTOSet.add(tripDTO);
            }
        }
        regionDTO.setTrips(tripDTOSet);

        Set<MountainRangeDTO> mountainRangeDTOSet = new HashSet<>();
        if(region.getMountainRanges() != null) {
            for(MountainRange mountainRange : region.getMountainRanges()) {
                MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(mountainRange);
                Set<PeakDTO> peakDTOSet = new HashSet<>();
                if(mountainRange.getPeaks() != null) {
                    for (Peak peak : mountainRange.getPeaks()) {
                        PeakDTO peakDTO = PeakDTO.createOnlyBasicFields(peak);
                        peakDTOSet.add(peakDTO);
                    }
                }
                mountainRangeDTO.setPeaks(peakDTOSet);
                mountainRangeDTOSet.add(mountainRangeDTO);
            }
        }
        regionDTO.setMountainRanges(mountainRangeDTOSet);
        return regionDTO;
    }
}
