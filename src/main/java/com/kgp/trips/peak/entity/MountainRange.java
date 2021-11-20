package com.kgp.trips.peak.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class MountainRange {

    @Id
    @GeneratedValue(generator = "mountain_range_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mountainRange")
    Set<Peak> peaks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    Region region;

}
