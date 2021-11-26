package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.InfrastructureType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfrastructureTypeDTO {

    String name;

    public static InfrastructureTypeDTO createOnlyBasicFields(InfrastructureType infrastructureType) {
        return InfrastructureTypeDTO.builder()
                .name(infrastructureType.getName())
                .build();
    }
}
