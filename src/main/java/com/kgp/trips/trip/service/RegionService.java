package com.kgp.trips.trip.service;

import com.kgp.trips.trip.dto.RegionDTO;
import com.kgp.trips.trip.entity.Region;
import com.kgp.trips.trip.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RegionService {

    public class RegionNotFoundException extends Exception {

    }

    @Autowired
    RegionRepository regionRepository;

    public Set<RegionDTO> getAllRegionDTO() {
        List<Region> all = regionRepository.findAll();
        Set<RegionDTO> regionDTOSet = new HashSet<>();

        for(Region region : all) {
            regionDTOSet.add(RegionDTO.createRegionDTOFromRegion(region));
        }
        return regionDTOSet;
    }

    public RegionDTO getRegionDTO(Integer id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if(optionalRegion.isPresent()) {
            Region region = optionalRegion.get();
            return RegionDTO.createRegionDTOFromRegion(region);
        } else {
            return null;
        }
    }

    public RegionDTO createRegion(RegionDTO regionDTO) {
        Region region = Region.createRegionFromRegionDTO(regionDTO);
        Region save = regionRepository.save(region);
        return RegionDTO.createRegionDTOFromRegion(save);
    }

    public void deleteRegion(Integer id) throws RegionService.RegionNotFoundException {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if(optionalRegion.isEmpty()) {
            throw new RegionService.RegionNotFoundException();
        }
        Region region = optionalRegion.get();
        regionRepository.delete(region);
    }

    public RegionDTO updateRegion(RegionDTO regionDTO) throws RegionService.RegionNotFoundException {
        Optional<Region> optionalRegion = regionRepository.findById(regionDTO.getId());
        if(optionalRegion.isEmpty()) {
            throw new RegionService.RegionNotFoundException();
        }
        Region region = Region.createRegionFromRegionDTO(regionDTO);
        return RegionDTO.createRegionDTOFromRegion(regionRepository.save(region));
    }
}
