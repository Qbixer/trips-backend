package com.kgp.trips.trip.api;

import com.kgp.trips.trip.dto.PeakDTO;
import com.kgp.trips.trip.service.PeakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/peak")
@RestController
public class PeakApi {

    @Autowired
    PeakService peakService;

    @GetMapping("")
    public ResponseEntity<Set<PeakDTO>> getAllPeak() {
        Set<PeakDTO> allPeakDTO = peakService.getAllPeakDTO();
        return ResponseEntity.ok(allPeakDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeakDTO> getPeak(@PathVariable Integer id)  {
        return ResponseEntity.ok(peakService.getPeakDTO(id));
    }

    @PostMapping("")
    public PeakDTO createPeak(@RequestBody PeakDTO peakDTO) {
        return peakService.createPeak(peakDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePeak(@PathVariable Integer id) {
        try {
            peakService.deletePeak(id);
        } catch (PeakService.PeakNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ResponseEntity.EMPTY);
    }

    @PutMapping("")
    public ResponseEntity<PeakDTO> updatePeak(@RequestBody PeakDTO peakDTO) {
        try {
            return ResponseEntity.ok(peakService.updatePeak(peakDTO));
        } catch (PeakService.PeakNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
