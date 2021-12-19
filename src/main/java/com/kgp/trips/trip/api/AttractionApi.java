package com.kgp.trips.trip.api;

import com.kgp.trips.trip.dto.AttractionDTO;
import com.kgp.trips.trip.service.AttractionService;
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
        Set<AttractionDTO> allAttractionDTO = attractionService.getAllAttractionDTO();
        return ResponseEntity.ok(allAttractionDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionDTO> getAttraction(@PathVariable Integer id) {
        return ResponseEntity.ok(attractionService.getAttractionDTO(id));
    }

    @PostMapping("")
    public AttractionDTO createAttraction(@RequestBody AttractionDTO attractionDTO) {
        return attractionService.createAttraction(attractionDTO);
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
    public ResponseEntity<AttractionDTO> updateAttraction(@RequestBody AttractionDTO attractionDTO) {
        try {
            return ResponseEntity.ok(attractionService.updateAttraction(attractionDTO));
        } catch (AttractionService.AttractionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
