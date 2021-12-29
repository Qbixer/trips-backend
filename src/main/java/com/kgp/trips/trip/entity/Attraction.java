package com.kgp.trips.trip.entity;

import com.kgp.trips.trip.dto.AttractionDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(schema = "trip")
public class Attraction {

    @Id
    @GeneratedValue(generator = "attraction_id_seq")
    Integer id;

    @Column(unique = true)
    String name;

    @Column
    String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    Region region;

    public static Attraction createAttractionFromAttractionDTO(AttractionDTO attractionDTO) {
        Attraction attraction = Attraction.builder()
                .id(attractionDTO.getId())
                .name(attractionDTO.getName())
                .description(attractionDTO.getDescription())
                .build();
        if(attractionDTO.getRegion() != null) {
            Region region = new Region();
            region.setId(attractionDTO.getRegion().getId());
            attraction.setRegion(region);
        }
        return attraction;
    }
}
