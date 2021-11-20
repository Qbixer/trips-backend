package com.kgp.trips.peak.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Region {

    @Id
    @GeneratedValue(generator = "region_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    Set<Attraction> attractions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    Set<MountainRange> mountainRanges;
}
