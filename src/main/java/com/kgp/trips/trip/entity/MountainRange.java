package com.kgp.trips.trip.entity;

import lombok.*;

import javax.persistence.*;
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
}
