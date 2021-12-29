package com.kgp.trips.trip.entity;

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
public class Peak {

    @Id
    @GeneratedValue(generator = "peak_id_seq")
    Integer id;

    @Column(unique = true)
    String name;

    @Column
    String description;

    @Column
    Integer height;

    @Column(name = "is_kgp")
    Boolean isKGP;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mountain_range_id")
    MountainRange mountainRange;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "trip",
            name="trip_peak",
            joinColumns={@JoinColumn(name="peak_id")},
            inverseJoinColumns={@JoinColumn(name="trip_id")})
    Set<Trip> trips;

    public static Peak createPeakFromPeakDTO(PeakDTO peakDTO) {
        Peak peak = Peak.builder()
                .id(peakDTO.getId())
                .name(peakDTO.getName())
                .description(peakDTO.getDescription())
                .height(peakDTO.getHeight())
                .isKGP(peakDTO.getIsKGP())
                .build();

        if(peakDTO.getMountainRange() != null) {
            MountainRange mountainRange = new MountainRange();
            mountainRange.setId(peakDTO.getMountainRange().getId());
            peak.setMountainRange(mountainRange);
        }

        if(peakDTO.getTrips() != null) {
            Set<Trip> trips = new HashSet<>();
            for(TripDTO tripDTO : peakDTO.getTrips()) {
                Trip trip = new Trip();
                trip.setId(tripDTO.getId());
                trips.add(trip);
            }
            peak.setTrips(trips);
        }

        return peak;
    }
}
