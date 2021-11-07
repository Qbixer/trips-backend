package com.kgp.trips.peak.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/peak")
@RestController
public class PeakApiImpl implements PeakApi {
    @Override
    @GetMapping("")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.of(Optional.of("test"));
    }
}
