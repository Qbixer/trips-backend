package com.kgp.trips.trip.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
}
