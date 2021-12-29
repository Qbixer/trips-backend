package com.kgp.trips.trip.service;

import com.kgp.trips.trip.dto.TripDTO;
import com.kgp.trips.trip.entity.Trip;
import com.kgp.trips.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TripService {

    public class TripNotFoundException extends Exception {

    }
    
    @Autowired
    TripRepository tripRepository;

    public Set<TripDTO> getAllTripDTO() {
        List<Trip> all = tripRepository.findAll();
        Set<TripDTO> tripDTOSet = new HashSet<>();

        for(Trip trip : all) {
            tripDTOSet.add(TripDTO.createTripDTOFromTrip(trip));
        }
        return tripDTOSet;
    }

    public TripDTO getTripDTO(Integer id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if(optionalTrip.isPresent()) {
            Trip trip = optionalTrip.get();
            return TripDTO.createTripDTOFromTrip(trip);
        } else {
            return null;
        }
    }

    public TripDTO createTrip(TripDTO tripDTO) {
        Trip trip = Trip.createTripFromTripDTO(tripDTO);
        Trip save = tripRepository.save(trip);
        return TripDTO.createTripDTOFromTrip(save);
    }

    public void deleteTrip(Integer id) throws TripService.TripNotFoundException {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if(optionalTrip.isEmpty()) {
            throw new TripService.TripNotFoundException();
        }
        Trip trip = optionalTrip.get();
        tripRepository.delete(trip);
    }

    public TripDTO updateTrip(TripDTO tripDTO) throws TripService.TripNotFoundException {
        Optional<Trip> optionalTrip = tripRepository.findById(tripDTO.getId());
        if(optionalTrip.isEmpty()) {
            throw new TripService.TripNotFoundException();
        }
        Trip trip = Trip.createTripFromTripDTO(tripDTO);
        return TripDTO.createTripDTOFromTrip(tripRepository.save(trip));
    }
}
