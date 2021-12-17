package com.kgp.trips.peak.entity;

import com.kgp.trips.peak.dto.InfrastructureTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class InfrastructureType {

    @Id
    String name;

    public static InfrastructureType createFromDTO(InfrastructureTypeDTO infrastructureTypeDTO) {
        return InfrastructureType.builder()
                .name(infrastructureTypeDTO.getName())
                .build();
    }
}
