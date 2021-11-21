package com.kgp.trips.peak.service;

import com.kgp.trips.peak.dto.MountainRangeDTO;
import com.kgp.trips.peak.dto.PeakDTO;
import com.kgp.trips.peak.dto.RegionDTO;
import com.kgp.trips.peak.entity.MountainRange;
import com.kgp.trips.peak.entity.Peak;
import com.kgp.trips.peak.repository.MountainRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
                PeakDTO peakDTO = PeakDTO.builder()
                        .id(peak.getId())
                        .name(peak.getName())
                        .build();
                peaks.add(peakDTO);
            }
            RegionDTO regionDTO = RegionDTO.builder()
                    .id(mountainRange.getRegion().getId())
                    .name(mountainRange.getRegion().getName())
                    .build();

            MountainRangeDTO mountainRangeDTO = MountainRangeDTO.builder()
                    .id(mountainRange.getId())
                    .description(mountainRange.getDescription())
                    .name(mountainRange.getName())
                    .peaks(peaks)
                    .region(regionDTO)
                    .build();

            result.add(mountainRangeDTO);
        }
        return result;
    }

}
