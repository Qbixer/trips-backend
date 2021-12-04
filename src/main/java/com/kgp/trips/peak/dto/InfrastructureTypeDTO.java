package com.kgp.trips.peak.dto;

import com.kgp.trips.peak.entity.InfrastructureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfrastructureTypeDTO {

    String name;

    public static InfrastructureTypeDTO createOnlyBasicFields(InfrastructureType infrastructureType) {
        return InfrastructureTypeDTO.builder()
                .name(infrastructureType.getName())
                .build();
    }

    public static InfrastructureTypeDTO createAllFields(InfrastructureType infrastructureType) {
        InfrastructureTypeDTO infrastructureTypeDTO = createOnlyBasicFields(infrastructureType);
        return infrastructureTypeDTO;
    }
}
