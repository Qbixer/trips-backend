package com.kgp.trips.peak.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Deprecated
@Table(name = "peak")
public class DeprecatedPeak {

    @Id
    @GeneratedValue(generator = "peak_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @Column
    Float height;

    @Column
    Boolean stamp;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="peak_route",
            joinColumns={@JoinColumn(name="peak_id")},
            inverseJoinColumns={@JoinColumn(name="route_id")})
    Set<Route> routes;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mountain_range_id")
    DeprecatedMountainRange deprecatedMountainRange;
}
