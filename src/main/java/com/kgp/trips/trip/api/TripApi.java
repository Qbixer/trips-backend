package com.kgp.trips.trip.api;

import com.kgp.trips.trip.dto.TripDTO;
import com.kgp.trips.trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/trip")
@RestController
public class TripApi {

    @Autowired
    TripService tripService;

    @GetMapping("")
    public ResponseEntity<Set<TripDTO>> getAllTrip() {
        Set<TripDTO> allTripDTO = tripService.getAllTripDTO();
        return ResponseEntity.ok(allTripDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTrip(@PathVariable Integer id)  {
        return ResponseEntity.ok(tripService.getTripDTO(id));
    }

    @PostMapping("")
    public TripDTO createTrip(@RequestBody TripDTO tripDTO) {
        return tripService.createTrip(tripDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTrip(@PathVariable Integer id) {
        try {
            tripService.deleteTrip(id);
        } catch (TripService.TripNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ResponseEntity.EMPTY);
    }

    @PutMapping("")
    public ResponseEntity<TripDTO> updateTrip(@RequestBody TripDTO tripDTO) {
        try {
            return ResponseEntity.ok(tripService.updateTrip(tripDTO));
        } catch (TripService.TripNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
