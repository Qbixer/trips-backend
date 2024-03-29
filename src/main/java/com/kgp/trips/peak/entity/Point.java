package com.kgp.trips.peak.entity;

import com.kgp.trips.peak.enums.Color;
import com.kgp.trips.peak.enums.PointType;
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
public class Point {

    @Id
    @GeneratedValue(generator = "point_id_seq")
    Integer id;

    @Column
    String name;

    @Column(length = 2000)
    String description;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass= Color.class)
    @CollectionTable(name="point_color")
    @Column(name = "color")
    Set<Color> colors;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass= PointType.class)
    @CollectionTable(name="point_point_type")
    @Column(name = "point_type")
    Set<PointType> pointTypes;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="point_infrastructure",
            joinColumns={@JoinColumn(name="point_id")},
            inverseJoinColumns={@JoinColumn(name="infrastructure_id")})
    Set<InfrastructureType> infrastructures;
}
