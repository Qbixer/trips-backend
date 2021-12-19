package com.kgp.trips.peak.api;

import com.kgp.trips.peak.dto.AttractionDTO;
import com.kgp.trips.peak.entity.DeprecatedAttraction;
import com.kgp.trips.peak.service.DeprecatedAttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/deprecated/attraction")
@RestController
@Deprecated
public class DeprecatedAttractionApi {

    @Autowired
    DeprecatedAttractionService deprecatedAttractionService;

    @GetMapping("")
    public ResponseEntity<Set<AttractionDTO>> getAllAttraction() {
        Set<AttractionDTO> allInfrastructureTypeDTO = deprecatedAttractionService.getAllAttractionDTO();
        return ResponseEntity.ok(allInfrastructureTypeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionDTO> getAttraction(@PathVariable Integer id) {
        return ResponseEntity.ok(deprecatedAttractionService.getAttractionDTO(id));
    }

    @PostMapping("")
    public AttractionDTO createAttraction(@RequestBody AttractionDTO attractionDTO) {
        return AttractionDTO.createAllFields(deprecatedAttractionService.createAttraction(attractionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAttraction(@PathVariable Integer id) {
        try {
            deprecatedAttractionService.deleteAttraction(id);
        } catch (DeprecatedAttractionService.AttractionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ResponseEntity.EMPTY);
    }

    @PutMapping("")
    public ResponseEntity<AttractionDTO> deleteAttraction(@RequestBody AttractionDTO attractionDTO) {
        DeprecatedAttraction deprecatedAttraction = null;
        try {
            deprecatedAttraction = deprecatedAttractionService.updateAttraction(attractionDTO);
        } catch (DeprecatedAttractionService.AttractionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AttractionDTO.createAllFields(deprecatedAttraction));
    }
}
