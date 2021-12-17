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
@Table(name = "mountain_range")
public class DeprecatedMountainRange {

    @Id
    @GeneratedValue(generator = "mountain_range_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deprecatedMountainRange", cascade = {CascadeType.ALL})
    Set<DeprecatedPeak> deprecatedPeaks;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    DeprecatedRegion deprecatedRegion;

}
