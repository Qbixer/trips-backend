package com.kgp.trips.peak.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class InfrastructureType {

    @Id
    String name;
}