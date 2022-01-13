package com.kgp.trips.trip.service;

import com.kgp.trips.trip.dto.MountainRangeDTO;
import com.kgp.trips.trip.dto.PeakDTO;
import com.kgp.trips.trip.dto.PhotoDTO;
import com.kgp.trips.trip.dto.TripDTO;
import com.kgp.trips.trip.entity.MountainRange;
import com.kgp.trips.trip.entity.Peak;
import com.kgp.trips.trip.entity.Photo;
import com.kgp.trips.trip.entity.Trip;
import com.kgp.trips.trip.repository.MountainRangeRepository;
import com.kgp.trips.trip.repository.PeakRepository;
import com.kgp.trips.trip.repository.PhotoRepository;
import com.kgp.trips.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TripService {

    public class TripNotFoundException extends Exception {

    }
    
    @Autowired
    TripRepository tripRepository;

    @Autowired
    MountainRangeRepository mountainRangeRepository;

    @Autowired
    PeakRepository peakRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    PhotoService photoService;

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
            TripDTO tripDTO = TripDTO.createTripDTOFromTrip(trip);
            addPhotosToTripDTO(tripDTO,trip);
            return tripDTO;
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

    public boolean deleteTripPhoto(Integer id, Integer photoId) throws TripService.TripNotFoundException, PhotoService.PhotoNotFoundException {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if(optionalTrip.isEmpty()) {
            throw new TripService.TripNotFoundException();
        }
        Trip trip = optionalTrip.get();

        Optional<Photo> photoOptional = trip.getPhotos().stream().filter(photo -> photo.getId().equals(photoId)).findFirst();
        if(photoOptional.isPresent() && photoService.deletePhoto(photoId)) {
            trip.getPhotos().remove(photoOptional.get());
            tripRepository.save(trip);
            return true;
        } else {
            return false;
        }
    }

    public TripDTO updateTrip(TripDTO tripDTO) throws TripService.TripNotFoundException {
        Optional<Trip> optionalTrip = tripRepository.findById(tripDTO.getId());
        if(optionalTrip.isEmpty()) {
            throw new TripService.TripNotFoundException();
        }
        Trip trip = Trip.createTripFromTripDTO(tripDTO);
        if(tripDTO.getMountainRanges() != null) {
            Set<MountainRange> mountainRanges = new HashSet<>();
            for (MountainRangeDTO mountainRangeDTO : tripDTO.getMountainRanges()) {
                MountainRange mountainRange = mountainRangeRepository.getById(mountainRangeDTO.getId());
                mountainRanges.add(mountainRange);
            }
            trip.setMountainRanges(mountainRanges);
        }
        if(tripDTO.getPeaks() != null) {
            Set<Peak> peaks = new HashSet<>();
            for (PeakDTO peakDTO : tripDTO.getPeaks()) {
                Peak peak = peakRepository.getById(peakDTO.getId());
                peaks.add(peak);
            }
            trip.setPeaks(peaks);
        }
        if(optionalTrip.get().getPhotos() != null) {
            trip.setPhotos(optionalTrip.get().getPhotos());
        }
        trip = tripRepository.save(trip);
        TripDTO result = TripDTO.createTripDTOFromTrip(trip);
        addPhotosToTripDTO(result,trip);
        return result;
    }

    public TripDTO addNewPhotoToTrip(Integer tripId, String name, MultipartFile file) throws TripNotFoundException, PhotoService.PhotoUploadException {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);
        if(optionalTrip.isEmpty()) {
            throw new TripNotFoundException();
        }
        Trip trip = optionalTrip.get();

        Photo photo = photoService.saveNewPhoto(name, file);
        if(trip.getPhotos() != null) {
            trip.getPhotos().add(photo);
        } else {
            Set<Photo> photos = new HashSet<>();
            photos.add(photo);
            trip.setPhotos(photos);
        }

        trip = tripRepository.save(trip);
        TripDTO tripDTO = TripDTO.createTripDTOFromTrip(trip);
        addPhotosToTripDTO(tripDTO,trip);
        return tripDTO;
    }


    private void addPhotosToTripDTO(TripDTO tripDTO, Trip trip) {
        Set<PhotoDTO> photoDTOSet = new HashSet<>();
        if(trip.getPhotos() != null) {
            for(Photo photo : trip.getPhotos()) {
                PhotoDTO photoDTO = PhotoDTO.createPhotoDTOFromPhoto(photo);
                photoDTOSet.add(photoDTO);
            }
        }
        tripDTO.setPhotos(photoDTOSet);
    }
}
