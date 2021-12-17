package com.kgp.trips.peak.api;

import com.kgp.trips.peak.dto.RegionDTO;
import com.kgp.trips.peak.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/deprecatedRegion")
@RestController
@Deprecated
public class RegionApi {

    @Autowired
    RegionService regionService;

    @GetMapping("")
    public ResponseEntity<Set<RegionDTO>> getAllRegion() {
        Set<RegionDTO> allRegionDTO = regionService.getAllRegionDTO();
        return ResponseEntity.ok(allRegionDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getMountainRange(@PathVariable Integer id)  {
        return ResponseEntity.ok(regionService.getRegionDTO(id));
    }
}
