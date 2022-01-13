package com.kgp.trips.trip.api;

import com.kgp.trips.trip.dto.PhotoDTO;
import com.kgp.trips.trip.entity.Photo;
import com.kgp.trips.trip.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/photo")
@RestController
public class PhotoApi {

    @Autowired
    PhotoService photoService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity fileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(photoService.createPhoto(name,file));
        } catch (PhotoService.PhotoUploadException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDTO> getPhoto(@PathVariable Integer id)  {
        return ResponseEntity.ok(photoService.getPhoto(id));
    }

    @GetMapping(value = "/url/{id}",   produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhotoImage(@PathVariable Integer id)  {
        Photo rawPhoto = photoService.getRawPhoto(id);
        if(rawPhoto != null) {
            return rawPhoto.getPhoto();
        }
        return null;
    }
}
