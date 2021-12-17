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
public class Region {

    @Id
    @GeneratedValue(generator = "region_id_seq")
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
}
