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
@Table(name = "region")
public class DeprecatedRegion {

    @Id
    @GeneratedValue(generator = "region_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deprecatedRegion")
    Set<DeprecatedAttraction> deprecatedAttractions;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deprecatedRegion")
    Set<DeprecatedMountainRange> deprecatedMountainRanges;
}
