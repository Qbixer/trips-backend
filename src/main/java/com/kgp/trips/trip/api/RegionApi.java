package com.kgp.trips.trip.api;

import com.kgp.trips.trip.dto.RegionDTO;
import com.kgp.trips.trip.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/region")
@RestController
public class RegionApi {

    @Autowired
    RegionService regionService;

    @GetMapping("")
    public ResponseEntity<Set<RegionDTO>> getAllRegion() {
        Set<RegionDTO> allRegionDTO = regionService.getAllRegionDTO();
        return ResponseEntity.ok(allRegionDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getRegion(@PathVariable Integer id)  {
        return ResponseEntity.ok(regionService.getRegionDTO(id));
    }

    @PostMapping("")
    public RegionDTO createRegion(@RequestBody RegionDTO regionDTO) {
        return regionService.createRegion(regionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRegion(@PathVariable Integer id) {
        try {
            regionService.deleteRegion(id);
        } catch (RegionService.RegionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ResponseEntity.EMPTY);
    }

    @PutMapping("")
    public ResponseEntity<RegionDTO> updateRegion(@RequestBody RegionDTO regionDTO) {
        try {
            return ResponseEntity.ok(regionService.updateRegion(regionDTO));
        } catch (RegionService.RegionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
