package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.Point;
import com.kgp.trips.peak.enums.Color;
import com.kgp.trips.peak.enums.PointType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class PointDTO {

    Integer id;
    String name;
    String description;

    Set<Color> colors;

    Set<PointType> pointTypes;

    Set<InfrastructureTypeDTO> infrastructures;

    public static PointDTO createOnlyBasicFields(Point point) {
        return PointDTO.builder()
                .id(point.getId())
                .name(point.getName())
                .description(point.getDescription())
                .colors(point.getColors())
                .pointTypes(point.getPointTypes())
                .build();
    }
}
