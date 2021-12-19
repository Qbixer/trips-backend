package com.kgp.trips.trip.service;

import com.kgp.trips.trip.dto.AttractionDTO;
import com.kgp.trips.trip.dto.MountainRangeDTO;
import com.kgp.trips.trip.dto.RegionDTO;
import com.kgp.trips.trip.dto.TripDTO;
import com.kgp.trips.trip.entity.Attraction;
import com.kgp.trips.trip.entity.MountainRange;
import com.kgp.trips.trip.entity.Region;
import com.kgp.trips.trip.entity.Trip;
import com.kgp.trips.trip.repository.AttractionRepository;
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
    AttractionRepository attractionRepository;

    @Autowired
    RegionRepository regionRepository;

    public Set<RegionDTO> getAllRegionDTO() {
        List<Region> all = regionRepository.findAll();
        Set<RegionDTO> regionDTOSet = new HashSet<>();

        for(Region region : all) {
            regionDTOSet.add(createRegionDTOFromRegion(region));
        }
        return regionDTOSet;
    }

    public RegionDTO getRegionDTO(Integer id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if(optionalRegion.isPresent()) {
            Region region = optionalRegion.get();
            return createRegionDTOFromRegion(region);
        } else {
            return null;
        }
    }

    public RegionDTO createRegion(RegionDTO regionDTO) {
        Region region = createRegionFromRegionDTO(regionDTO);
        Region save = regionRepository.save(region);
        return createRegionDTOFromRegion(save);
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
        Region region = createRegionFromRegionDTO(regionDTO);
        return createRegionDTOFromRegion(regionRepository.save(region));
    }

    private RegionDTO createRegionDTOFromRegion(Region region) {
        RegionDTO regionDTO = RegionDTO.createOnlyBasicFields(region);

        Set<AttractionDTO> attractionDTOSet = new HashSet<>();
        if(region.getAttractions() != null) {
            for (Attraction attraction : region.getAttractions()) {
                AttractionDTO attractionDTO = AttractionDTO.createOnlyBasicFields(attraction);
                attractionDTOSet.add(attractionDTO);
            }
        }
        regionDTO.setAttractions(attractionDTOSet);

        Set<TripDTO> tripDTOSet = new HashSet<>();
        if(region.getTrips() != null) {
            for(Trip trip : region.getTrips()) {
                TripDTO tripDTO = TripDTO.createOnlyBasicFields(trip);
                tripDTOSet.add(tripDTO);
            }
        }
        regionDTO.setTrips(tripDTOSet);

        Set<MountainRangeDTO> mountainRangeDTOSet = new HashSet<>();
        if(region.getMountainRanges() != null) {
            for(MountainRange mountainRange : region.getMountainRanges()) {
                MountainRangeDTO mountainRangeDTO = MountainRangeDTO.createOnlyBasicFields(mountainRange);
                mountainRangeDTOSet.add(mountainRangeDTO);
            }
        }
        regionDTO.setMountainRanges(mountainRangeDTOSet);
        return regionDTO;
    }

    private Region createRegionFromRegionDTO(RegionDTO regionDTO) {
        Region region = Region.builder()
                .id(regionDTO.getId())
                .name(regionDTO.getName())
                .description(regionDTO.getDescription())
                .build();

        if(regionDTO.getAttractions() != null) {
            Set<Attraction> attractions = new HashSet<>();
            for(AttractionDTO attractionDTO : regionDTO.getAttractions()) {
                Attraction attraction = new Attraction();
                attraction.setId(attractionDTO.getId());
                attractions.add(attraction);
            }
            region.setAttractions(attractions);
        }

        if(regionDTO.getTrips() != null) {
            Set<Trip> trips = new HashSet<>();
            for(TripDTO tripDTO : regionDTO.getTrips()) {
                Trip trip = new Trip();
                trip.setId(tripDTO.getId());
                trips.add(trip);
            }
            region.setTrips(trips);
        }

        if(regionDTO.getMountainRanges() != null) {
            Set<MountainRange> mountainRanges = new HashSet<>();
            for(MountainRangeDTO mountainRangeDTO : regionDTO.getMountainRanges()) {
                MountainRange mountainRange = new MountainRange();
                mountainRange.setId(mountainRangeDTO.getId());
                mountainRanges.add(mountainRange);
            }
            region.setMountainRanges(mountainRanges);
        }

        return region;
    }


}
