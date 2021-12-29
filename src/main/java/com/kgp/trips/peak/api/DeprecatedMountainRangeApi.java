package com.kgp.trips.peak.api;

import com.kgp.trips.peak.dto.MountainRangeDTO;
import com.kgp.trips.peak.service.DeprecatedMountainRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/deprecated/mountainRange")
@RestController
@Deprecated
public class DeprecatedMountainRangeApi {

    @Autowired
    DeprecatedMountainRangeService deprecatedMountainRangeService;

    @GetMapping("")
    public ResponseEntity<Set<MountainRangeDTO>> getAllMountainRange() {
        Set<MountainRangeDTO> allMountainRangeDTO = deprecatedMountainRangeService.getAllMountainRangeDTO();
        return ResponseEntity.ok(allMountainRangeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MountainRangeDTO> getMountainRange(@PathVariable Integer id)  {
        return ResponseEntity.ok(deprecatedMountainRangeService.getMountainRangeDTO(id));
    }
}
