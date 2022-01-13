package com.kgp.trips.trip.entity;

import com.kgp.trips.trip.dto.AttractionDTO;
import com.kgp.trips.trip.dto.MountainRangeDTO;
import com.kgp.trips.trip.dto.RegionDTO;
import com.kgp.trips.trip.dto.TripDTO;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(schema = "trip")
public class Region {

    @Id
    @GeneratedValue(generator = "trip.region_id_seq")
    Integer id;

    @Column(unique = true)
    String name;

    @Column
    String description;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    Set<Attraction> attractions;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    Set<MountainRange> mountainRanges;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    Set<Trip> trips;

    public static Region createRegionFromRegionDTO(RegionDTO regionDTO) {
        Region region = Region.builder()
                .id(regionDTO.getId())
                .name(regionDTO.getName())
                .description(regionDTO.getDescription())
                .build();

        if(regionDTO.getAttractions() != null) {
            Set<Attraction> attractions = new HashSet<>();
            for(AttractionDTO attractionDTO : regionDTO.getAttractions()) {
                Attraction attraction = new Attraction();
                attraction.setId(attractionDTO.getId());
                attractions.add(attraction);
            }
            region.setAttractions(attractions);
        }

        if(regionDTO.getTrips() != null) {
            Set<Trip> trips = new HashSet<>();
            for(TripDTO tripDTO : regionDTO.getTrips()) {
                Trip trip = new Trip();
                trip.setId(tripDTO.getId());
                trips.add(trip);
            }
            region.setTrips(trips);
        }

        if(regionDTO.getMountainRanges() != null) {
            Set<MountainRange> mountainRanges = new HashSet<>();
            for(MountainRangeDTO mountainRangeDTO : regionDTO.getMountainRanges()) {
                MountainRange mountainRange = new MountainRange();
                mountainRange.setId(mountainRangeDTO.getId());
                mountainRanges.add(mountainRange);
            }
            region.setMountainRanges(mountainRanges);
        }

        return region;
    }
}
