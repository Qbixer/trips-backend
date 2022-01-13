package com.kgp.trips.trip.service;

import com.kgp.trips.trip.dto.PhotoDTO;
import com.kgp.trips.trip.dto.RegionDTO;
import com.kgp.trips.trip.dto.TripDTO;
import com.kgp.trips.trip.entity.Photo;
import com.kgp.trips.trip.entity.Region;
import com.kgp.trips.trip.entity.Trip;
import com.kgp.trips.trip.repository.PhotoRepository;
import com.kgp.trips.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PhotoService {

    public class PhotoNotFoundException extends Exception {

    }

    public class PhotoUploadException extends Exception {

    }
    
    @Autowired
    PhotoRepository photoRepository;

    public Photo saveNewPhoto(String name, MultipartFile file) throws PhotoUploadException {
        byte[] image;
        try {
            image = file.getBytes();
        } catch (IOException e) {
            throw new PhotoUploadException();
        }
        Photo photo = Photo.builder()
                .name(name)
                .photo(image)
                .build();
        return photoRepository.save(photo);
    }

    public PhotoDTO createPhoto(String name, MultipartFile file) throws PhotoUploadException {
        Photo photo = saveNewPhoto(name, file);
        return PhotoDTO.createPhotoDTOFromPhoto(photo);
    }

    public PhotoDTO getPhoto(Integer id) {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        if(optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();
            return PhotoDTO.createPhotoDTOFromPhoto(photo);
        } else {
            return null;
        }
    }

    public Photo getRawPhoto(Integer id) {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        return optionalPhoto.orElse(null);
    }

    public boolean deletePhoto(Integer id) throws PhotoNotFoundException {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        if(optionalPhoto.isPresent()) {
            photoRepository.delete(optionalPhoto.get());
            return true;
        } else {
            throw new PhotoNotFoundException();
        }
    }
}
