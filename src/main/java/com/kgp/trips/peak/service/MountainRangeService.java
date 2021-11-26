package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.MountainRangeDTO;
import com.kgp.trips.peak.dto.PeakDTO;
import com.kgp.trips.peak.dto.RegionDTO;
import com.kgp.trips.peak.dto.RouteDTO;
import com.kgp.trips.peak.entity.MountainRange;
import com.kgp.trips.peak.entity.Peak;
import com.kgp.trips.peak.entity.Route;
import com.kgp.trips.peak.repository.MountainRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MountainRangeService {

    @Autowired
    MountainRangeRepository mountainRangeRepository;

    public Set<MountainRangeDTO> getAllMountainRangeDTO() {
        List<MountainRange> all = mountainRangeRepository.findAll();
        Set<MountainRangeDTO> result = new HashSet<>();
        for(MountainRange mountainRange: all) {
            Set<PeakDTO> peaks = new HashSet<>();
            for(Peak peak: mountainRange.getPeaks()) {
                PeakDTO peakDTO = PeakDTO.createOnlyBasicFields(peak);
                peaks.add(peakDTO);
            }
            RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(mountainRange.getRegion());

            MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(mountainRange);
            mountainRangeDTO.setPeaks(peaks);
            mountainRangeDTO.setRegion(regionDTO);

            result.add(mountainRangeDTO);
        }
        return result;
    }

    public MountainRangeDTO getMountainRangeDTO(Integer id) {
        Optional<MountainRange> optionalMountainRange = mountainRangeRepository.findById(id);
        if(optionalMountainRange.isPresent()) {
            MountainRange mountainRange = optionalMountainRange.get();
            Set<PeakDTO> peakDTOSet = new HashSet<>();
            for(Peak peak: mountainRange.getPeaks()) {
                PeakDTO peakDTO = PeakDTO.createOnlyBasicFields(peak);
                Set<RouteDTO> routeDTOSet = new HashSet<>();
                for(Route route: peak.getRoutes()) {
                    RouteDTO routeDTO = RouteDTO.createOnlyBasicFields(route);
                    routeDTOSet.add(routeDTO);
                }
                peakDTO.setRoutes(routeDTOSet);
            }

            RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(mountainRange.getRegion());

            MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(mountainRange);
            mountainRangeDTO.setPeaks(peakDTOSet);
            mountainRangeDTO.setRegion(regionDTO);
            return mountainRangeDTO;
        } else {
            return null;
        }

    }

}
