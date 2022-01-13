package com.kgp.trips.trip.dto;

import com.kgp.trips.trip.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {

    Integer id;
    String name;
    String photo64;

    public static PhotoDTO createOnlyBasicFields(Photo photo) {
        return PhotoDTO.builder()
                .id(photo.getId())
                .name(photo.getName())
                .build();
    }

    public static PhotoDTO createPhotoDTOFromPhoto(Photo photo) {
        PhotoDTO photoDTO = PhotoDTO.createOnlyBasicFields(photo);
        photoDTO.setPhoto64(Base64.getEncoder().encodeToString(photo.getPhoto()));
        return photoDTO;
    }
}
