package com.kgp.trips.trip.api;

import com.kgp.trips.trip.dto.TripDTO;
import com.kgp.trips.trip.service.PhotoService;
import com.kgp.trips.trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/addPhoto", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity addPhotoToTrip(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(tripService.addNewPhotoToTrip(id,name,file));
        } catch (PhotoService.PhotoUploadException | TripService.TripNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}/photo/{photoId}")
    public ResponseEntity deleteTripPhoto(@PathVariable Integer id, @PathVariable Integer photoId) {
        try {
            boolean deleteTripPhoto = tripService.deleteTripPhoto(id, photoId);
            if(deleteTripPhoto) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (TripService.TripNotFoundException | PhotoService.PhotoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
