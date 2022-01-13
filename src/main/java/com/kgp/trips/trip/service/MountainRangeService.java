package com.kgp.trips.trip.service;

import com.kgp.trips.trip.dto.MountainRangeDTO;
import com.kgp.trips.trip.dto.PeakDTO;
import com.kgp.trips.trip.dto.TripDTO;
import com.kgp.trips.trip.entity.MountainRange;
import com.kgp.trips.trip.entity.Peak;
import com.kgp.trips.trip.entity.Trip;
import com.kgp.trips.trip.repository.MountainRangeRepository;
import com.kgp.trips.trip.repository.PeakRepository;
import com.kgp.trips.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MountainRangeService {

    public class MountainRangeNotFoundException extends Exception {

    }
    
    @Autowired
    MountainRangeRepository mountainRangeRepository;

    @Autowired
    PeakRepository peakRepository;

    @Autowired
    TripRepository tripRepository;

    public Set<MountainRangeDTO> getAllMountainRangeDTO() {
        List<MountainRange> all = mountainRangeRepository.findAll();
        Set<MountainRangeDTO> mountainRangeDTOSet = new HashSet<>();

        for(MountainRange mountainRange : all) {
            mountainRangeDTOSet.add(MountainRangeDTO.createMountainRangeDTOFromMountainRange(mountainRange));
        }
        return mountainRangeDTOSet;
    }

    public Set<MountainRangeDTO> getAllMountainRangeDTOOnlyBasicFields() {
        List<MountainRange> all = mountainRangeRepository.findAll();
        Set<MountainRangeDTO> mountainRangeDTOSet = new HashSet<>();

        for(MountainRange mountainRange : all) {
            mountainRangeDTOSet.add(MountainRangeDTO.createOnlyBasicFields(mountainRange));
        }
        return mountainRangeDTOSet;
    }

    public MountainRangeDTO getMountainRangeDTO(Integer id) {
        Optional<MountainRange> optionalMountainRange = mountainRangeRepository.findById(id);
        if(optionalMountainRange.isPresent()) {
            MountainRange mountainRange = optionalMountainRange.get();
            return MountainRangeDTO.createMountainRangeDTOFromMountainRange(mountainRange);
        } else {
            return null;
        }
    }

    public MountainRangeDTO createMountainRange(MountainRangeDTO mountainRangeDTO) {
        MountainRange mountainRange = MountainRange.createMountainRangeFromMountainRangeDTO(mountainRangeDTO);
        MountainRange save = mountainRangeRepository.save(mountainRange);
        return MountainRangeDTO.createMountainRangeDTOFromMountainRange(save);
    }

    public void deleteMountainRange(Integer id) throws MountainRangeService.MountainRangeNotFoundException {
        Optional<MountainRange> optionalMountainRange = mountainRangeRepository.findById(id);
        if(optionalMountainRange.isEmpty()) {
            throw new MountainRangeService.MountainRangeNotFoundException();
        }
        MountainRange mountainRange = optionalMountainRange.get();
        mountainRangeRepository.delete(mountainRange);
    }

    public MountainRangeDTO updateMountainRange(MountainRangeDTO mountainRangeDTO) throws MountainRangeService.MountainRangeNotFoundException {
        Optional<MountainRange> optionalMountainRange = mountainRangeRepository.findById(mountainRangeDTO.getId());
        if(optionalMountainRange.isEmpty()) {
            throw new MountainRangeService.MountainRangeNotFoundException();
        }
        MountainRange mountainRange = MountainRange.createMountainRangeFromMountainRangeDTO(mountainRangeDTO);
        if(mountainRangeDTO.getPeaks() != null) {
            Set<Peak> peaks = new HashSet<>();
            for (PeakDTO peakDTO : mountainRangeDTO.getPeaks()) {
                Peak peak = peakRepository.getById(peakDTO.getId());
                peaks.add(peak);
            }
            mountainRange.setPeaks(peaks);
        }
        if(mountainRangeDTO.getTrips() != null) {
            Set<Trip> trips = new HashSet<>();
            for (TripDTO tripDTO : mountainRangeDTO.getTrips()) {
                Trip trip = tripRepository.getById(tripDTO.getId());
                trips.add(trip);
            }
            mountainRange.setTrips(trips);
        }
        return MountainRangeDTO.createMountainRangeDTOFromMountainRange(mountainRangeRepository.save(mountainRange));
    }
}
