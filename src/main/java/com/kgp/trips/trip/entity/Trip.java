package com.kgp.trips.trip.entity;

import com.kgp.trips.trip.dto.MountainRangeDTO;
import com.kgp.trips.trip.dto.PeakDTO;
import com.kgp.trips.trip.dto.TripDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(schema = "trip")
public class Trip {

    @Id
    @GeneratedValue(generator = "trip_id_seq")
    Integer id;

    @Column(unique = true)
    String name;

    @Column
    String description;

    @Column
    LocalDateTime date;

    @Column
    String mapaTurystycznaLink;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    Region region;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "trip",
            name="trip_photo",
            joinColumns={@JoinColumn(name="trip_id")},
            inverseJoinColumns={@JoinColumn(name="photo_id")})
    Set<Photo> photos;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "trip",
            name="trip_peak",
            joinColumns={@JoinColumn(name="trip_id")},
            inverseJoinColumns={@JoinColumn(name="peak_id")})
    Set<Peak> peaks;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "trip",
            name="trip_mountain_range",
            joinColumns={@JoinColumn(name="trip_id")},
            inverseJoinColumns={@JoinColumn(name="mountain_range_id")})
    Set<MountainRange> mountainRanges;

    //TODO photo
    public static Trip createTripFromTripDTO(TripDTO tripDTO) {
        Trip trip = Trip.builder()
                .id(tripDTO.getId())
                .name(tripDTO.getName())
                .description(tripDTO.getDescription())
                .date(tripDTO.getDate())
                .mapaTurystycznaLink(tripDTO.getMapaTurystycznaLink())
                .build();

        if(tripDTO.getRegion() != null) {
            Region region = new Region();
            region.setId(tripDTO.getRegion().getId());
            trip.setRegion(region);
        }

        if(trip.getPeaks() != null) {
            Set<Peak> peaks = new HashSet<>();
            for(PeakDTO peakDTO : tripDTO.getPeaks()) {
                Peak peak = new Peak();
                peak.setId(peakDTO.getId());
                peaks.add(peak);
            }
            trip.setPeaks(peaks);
        }

        if(tripDTO.getMountainRanges() != null) {
            Set<MountainRange> mountainRanges = new HashSet<>();
            for(MountainRangeDTO mountainRangeDTO : tripDTO.getMountainRanges()) {
                MountainRange mountainRange = new MountainRange();
                mountainRange.setId(mountainRangeDTO.getId());
                mountainRanges.add(mountainRange);
            }
            trip.setMountainRanges(mountainRanges);
        }



        return trip;
    }
}
