package com.kgp.trips.peak.api;

import com.kgp.trips.peak.dto.InfrastructureTypeDTO;
import com.kgp.trips.peak.entity.InfrastructureType;
import com.kgp.trips.peak.service.InfrastructureTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RequestMapping("/infrastructureType")
@RestController
public class InfrastructureTypeApi {

    @Autowired
    InfrastructureTypeService infrastructureTypeService;

    @GetMapping("")
    public ResponseEntity<Set<InfrastructureTypeDTO>> getAllInfrastructureType() {
        Set<InfrastructureTypeDTO> allInfrastructureTypeDTO = infrastructureTypeService.getAllInfrastructureTypeDTO();
        return ResponseEntity.of(Optional.of(allInfrastructureTypeDTO));
    }

    @PostMapping("")
    public InfrastructureType createInfrastructureType(@RequestBody InfrastructureTypeDTO infrastructureTypeDTO) {
        return infrastructureTypeService.createInfrastructureType(infrastructureTypeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInfrastructureType(@PathVariable String id) {
        try {
            infrastructureTypeService.deleteInfrastructureType(id);
        } catch (InfrastructureTypeService.InfrastructureTypeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ResponseEntity.EMPTY);
    }
}
