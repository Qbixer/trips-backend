package com.kgp.trips.peak.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
public class Attraction {

    @Id
    @GeneratedValue(generator = "attraction_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="attraction_infrastructure",
            joinColumns={@JoinColumn(name="attraction_id")},
            inverseJoinColumns={@JoinColumn(name="infrastructure_id")})
    Set<InfrastructureType> infrastructures;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    Region region;
}
