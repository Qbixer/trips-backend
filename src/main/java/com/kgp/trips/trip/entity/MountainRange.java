package com.kgp.trips.trip.entity;

import com.kgp.trips.trip.dto.MountainRangeDTO;
import com.kgp.trips.trip.dto.PeakDTO;
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
public class MountainRange {

    @Id
    @GeneratedValue(generator = "mountain_range_id_seq")
    Integer id;

    @Column(unique = true)
    String name;

    @Column
    String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    Region region;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mountainRange")
    Set<Peak> peaks;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "trip",
            name="trip_mountain_range",
            joinColumns={@JoinColumn(name="mountain_range_id")},
            inverseJoinColumns={@JoinColumn(name="trip_id")})
    Set<Trip> trips;

    public static MountainRange createMountainRangeFromMountainRangeDTO(MountainRangeDTO mountainRangeDTO) {
        MountainRange mountainRange = MountainRange.builder()
                .id(mountainRangeDTO.getId())
                .name(mountainRangeDTO.getName())
                .description(mountainRangeDTO.getDescription())
                .build();

        if(mountainRangeDTO.getRegion() != null) {
            Region region = new Region();
            region.setId(mountainRangeDTO.getRegion().getId());
            mountainRange.setRegion(region);
        }

        if(mountainRange.getPeaks() != null) {
            Set<Peak> peaks = new HashSet<>();
            for(PeakDTO peakDTO : mountainRangeDTO.getPeaks()) {
                Peak peak = new Peak();
                peak.setId(peakDTO.getId());
                peaks.add(peak);
            }
            mountainRange.setPeaks(peaks);
        }

        if(mountainRangeDTO.getTrips() != null) {
            Set<Trip> trips = new HashSet<>();
            for(TripDTO tripDTO : mountainRangeDTO.getTrips()) {
                Trip trip = new Trip();
                trip.setId(tripDTO.getId());
                trips.add(trip);
            }
            mountainRange.setTrips(trips);
        }

        return mountainRange;
    }
}
