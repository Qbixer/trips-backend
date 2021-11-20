package com.kgp.trips.peak.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Route {

    @Id
    @GeneratedValue(generator = "route_id_seq")
    Long id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="route_point",
            joinColumns={@JoinColumn(name="route_id")},
            inverseJoinColumns={@JoinColumn(name="point_id")})
    Set<Point> points;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="peak_route",
            joinColumns={@JoinColumn(name="route_id")},
            inverseJoinColumns={@JoinColumn(name="peak_id")})
    Set<Peak> peaks;
}