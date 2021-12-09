package com.kgp.trips.peak.api;

import com.kgp.trips.peak.dto.AttractionDTO;
import com.kgp.trips.peak.entity.Attraction;
import com.kgp.trips.peak.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/attraction")
@RestController
public class AttractionApi {

    @Autowired
    AttractionService attractionService;

    @GetMapping("")
    public ResponseEntity<Set<AttractionDTO>> getAllAttraction() {
        Set<AttractionDTO> allInfrastructureTypeDTO = attractionService.getAllAttractionDTO();
        return ResponseEntity.ok(allInfrastructureTypeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionDTO> getAttraction(@PathVariable Integer id) {
        return ResponseEntity.ok(attractionService.getAttractionDTO(id));
    }

    @PostMapping("")
    public AttractionDTO createAttraction(@RequestBody AttractionDTO attractionDTO) {
        return AttractionDTO.createAllFields(attractionService.createAttraction(attractionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAttraction(@PathVariable Integer id) {
        try {
            attractionService.deleteAttraction(id);
        } catch (AttractionService.AttractionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ResponseEntity.EMPTY);
    }

    @PutMapping("")
    public ResponseEntity<AttractionDTO> deleteAttraction(@RequestBody AttractionDTO attractionDTO) {
        Attraction attraction = null;
        try {
            attraction = attractionService.updateAttraction(attractionDTO);
        } catch (AttractionService.AttractionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AttractionDTO.createAllFields(attraction));
    }
}
