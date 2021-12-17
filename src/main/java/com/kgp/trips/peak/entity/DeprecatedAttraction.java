package com.kgp.trips.peak.entity;

import com.kgp.trips.peak.dto.AttractionDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Deprecated
@Table(name = "attraction")
public class DeprecatedAttraction {

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
            joinColumns={@JoinColumn(name="attraction_id", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="infrastructure_id", referencedColumnName = "name")})
    Set<InfrastructureType> infrastructures;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    DeprecatedRegion deprecatedRegion;

    public static DeprecatedAttraction createFromDTO(AttractionDTO attractionDTO) {
        return DeprecatedAttraction.builder()
                .id(attractionDTO.getId())
                .name(attractionDTO.getName())
                .description(attractionDTO.getDescription())
                .infrastructures(attractionDTO.getInfrastructures() != null ? attractionDTO.getInfrastructures().stream().map(infrastructureTypeDTO -> InfrastructureType.builder().name(infrastructureTypeDTO.getName()).build()).collect(Collectors.toSet()) : null)
                .deprecatedRegion(attractionDTO.getRegion() != null ? DeprecatedRegion.builder().id(attractionDTO.getRegion().getId()).build() : null)
                .build();
    }
}
