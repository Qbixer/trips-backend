package com.kgp.trips.trip.api;

import com.kgp.trips.trip.dto.MountainRangeDTO;
import com.kgp.trips.trip.service.MountainRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/mountainRange")
@RestController
public class MountainRangeApi {

    @Autowired
    MountainRangeService mountainRangeService;

    @GetMapping("")
    public ResponseEntity<Set<MountainRangeDTO>> getAllMountainRange() {
        Set<MountainRangeDTO> allMountainRangeDTO = mountainRangeService.getAllMountainRangeDTO();
        return ResponseEntity.ok(allMountainRangeDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Set<MountainRangeDTO>> getAllMountainRangeWithoutPhoto() {
        Set<MountainRangeDTO> allMountainRangeDTO = mountainRangeService.getAllMountainRangeDTOOnlyBasicFields();
        return ResponseEntity.ok(allMountainRangeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MountainRangeDTO> getMountainRange(@PathVariable Integer id)  {
        return ResponseEntity.ok(mountainRangeService.getMountainRangeDTO(id));
    }

    @PostMapping("")
    public MountainRangeDTO createMountainRange(@RequestBody MountainRangeDTO mountainRangeDTO) {
        return mountainRangeService.createMountainRange(mountainRangeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMountainRange(@PathVariable Integer id) {
        try {
            mountainRangeService.deleteMountainRange(id);
        } catch (MountainRangeService.MountainRangeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ResponseEntity.EMPTY);
    }

    @PutMapping("")
    public ResponseEntity<MountainRangeDTO> updateMountainRange(@RequestBody MountainRangeDTO mountainRangeDTO) {
        try {
            return ResponseEntity.ok(mountainRangeService.updateMountainRange(mountainRangeDTO));
        } catch (MountainRangeService.MountainRangeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
