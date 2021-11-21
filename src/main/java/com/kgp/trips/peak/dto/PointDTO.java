package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.enums.Color;
import com.kgp.trips.peak.enums.PointType;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PointDTO {

    Integer id;
    String name;
    String description;

    Set<Color> colors;

    Set<PointType> pointTypes;

    Set<InfrastructureTypeDTO> infrastructures;
}
