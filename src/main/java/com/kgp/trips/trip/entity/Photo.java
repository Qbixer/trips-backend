package com.kgp.trips.trip.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(schema = "trip")
public class Photo {

    @Id
    @GeneratedValue(generator = "trip.photo_id_seq")
    Integer id;

    @Column
    String name;

    @JsonIgnore
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Basic(fetch = FetchType.LAZY)
    byte[] photo;
}
