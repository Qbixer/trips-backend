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
public class Region {

    @Id
    @GeneratedValue(generator = "region_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    Set<Attraction> attractions;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    Set<MountainRange> mountainRanges;
}
