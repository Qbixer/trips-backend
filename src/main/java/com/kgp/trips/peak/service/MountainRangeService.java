package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.MountainRangeDTO;
import com.kgp.trips.peak.dto.PeakDTO;
import com.kgp.trips.peak.dto.RegionDTO;
import com.kgp.trips.peak.dto.RouteDTO;
import com.kgp.trips.peak.entity.DeprecatedMountainRange;
import com.kgp.trips.peak.entity.DeprecatedPeak;
import com.kgp.trips.peak.entity.Route;
import com.kgp.trips.peak.repository.DeprecatedMountainRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Deprecated
public class MountainRangeService {

    @Autowired
    DeprecatedMountainRangeRepository deprecatedMountainRangeRepository;

    public Set<MountainRangeDTO> getAllMountainRangeDTO() {
        List<DeprecatedMountainRange> all = deprecatedMountainRangeRepository.findAll();
        Set<MountainRangeDTO> result = new HashSet<>();
        for(DeprecatedMountainRange deprecatedMountainRange : all) {
            Set<PeakDTO> peaks = new HashSet<>();
            for(DeprecatedPeak deprecatedPeak : deprecatedMountainRange.getDeprecatedPeaks()) {
                PeakDTO peakDTO = PeakDTO.createOnlyBasicFields(deprecatedPeak);
                peaks.add(peakDTO);
            }
            RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(deprecatedMountainRange.getDeprecatedRegion());

            MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(deprecatedMountainRange);
            mountainRangeDTO.setPeaks(peaks);
            mountainRangeDTO.setRegion(regionDTO);

            result.add(mountainRangeDTO);
        }
        return result;
    }

    public MountainRangeDTO getMountainRangeDTO(Integer id) {
        Optional<DeprecatedMountainRange> optionalMountainRange = deprecatedMountainRangeRepository.findById(id);
        if(optionalMountainRange.isPresent()) {
            DeprecatedMountainRange deprecatedMountainRange = optionalMountainRange.get();
            Set<PeakDTO> peakDTOSet = new HashSet<>();
            for(DeprecatedPeak deprecatedPeak : deprecatedMountainRange.getDeprecatedPeaks()) {
                PeakDTO peakDTO = PeakDTO.createOnlyBasicFields(deprecatedPeak);
                Set<RouteDTO> routeDTOSet = new HashSet<>();
                for(Route route: deprecatedPeak.getRoutes()) {
                    RouteDTO routeDTO = RouteDTO.createOnlyBasicFields(route);
                    routeDTOSet.add(routeDTO);
                }
                peakDTO.setRoutes(routeDTOSet);
            }

            RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(deprecatedMountainRange.getDeprecatedRegion());

            MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(deprecatedMountainRange);
            mountainRangeDTO.setPeaks(peakDTOSet);
            mountainRangeDTO.setRegion(regionDTO);
            return mountainRangeDTO;
        } else {
            return null;
        }

    }

}
