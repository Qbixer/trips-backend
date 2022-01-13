package com.kgp.trips.trip.service;

import com.kgp.trips.trip.dto.PeakDTO;
import com.kgp.trips.trip.dto.TripDTO;
import com.kgp.trips.trip.entity.Peak;
import com.kgp.trips.trip.entity.Trip;
import com.kgp.trips.trip.repository.PeakRepository;
import com.kgp.trips.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PeakService {

    public class PeakNotFoundException extends Exception {

    }
    
    @Autowired
    PeakRepository peakRepository;

    @Autowired
    TripRepository tripRepository;

    public Set<PeakDTO> getAllPeakDTO() {
        List<Peak> all = peakRepository.findAll();
        Set<PeakDTO> peakDTOSet = new HashSet<>();

        for(Peak peak : all) {
            peakDTOSet.add(PeakDTO.createPeakDTOFromPeak(peak));
        }
        return peakDTOSet;
    }

    public PeakDTO getPeakDTO(Integer id) {
        Optional<Peak> optionalPeak = peakRepository.findById(id);
        if(optionalPeak.isPresent()) {
            Peak peak = optionalPeak.get();
            return PeakDTO.createPeakDTOFromPeak(peak);
        } else {
            return null;
        }
    }

    public PeakDTO createPeak(PeakDTO peakDTO) {
        Peak peak = Peak.createPeakFromPeakDTO(peakDTO);
        Peak save = peakRepository.save(peak);
        return PeakDTO.createPeakDTOFromPeak(save);
    }

    public void deletePeak(Integer id) throws PeakService.PeakNotFoundException {
        Optional<Peak> optionalPeak = peakRepository.findById(id);
        if(optionalPeak.isEmpty()) {
            throw new PeakService.PeakNotFoundException();
        }
        Peak peak = optionalPeak.get();
        peakRepository.delete(peak);
    }

    public PeakDTO updatePeak(PeakDTO peakDTO) throws PeakService.PeakNotFoundException {
        Optional<Peak> optionalPeak = peakRepository.findById(peakDTO.getId());
        if(optionalPeak.isEmpty()) {
            throw new PeakService.PeakNotFoundException();
        }

        Peak peak = Peak.createPeakFromPeakDTO(peakDTO);
        if(peakDTO.getTrips() != null) {
            Set<Trip> trips = new HashSet<>();
            for (TripDTO tripDTO : peakDTO.getTrips()) {
                Trip trip = tripRepository.getById(tripDTO.getId());
                trips.add(trip);
            }
            peak.setTrips(trips);
        }
        return PeakDTO.createPeakDTOFromPeak(peakRepository.save(peak));
    }
}
