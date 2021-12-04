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
public class MountainRange {

    @Id
    @GeneratedValue(generator = "mountain_range_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mountainRange", cascade = {CascadeType.ALL})
    Set<Peak> peaks;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    Region region;

}
