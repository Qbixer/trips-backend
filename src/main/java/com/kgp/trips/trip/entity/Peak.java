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
}
