package com.kgp.trips.trip.repository;

import com.kgp.trips.trip.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttractionRepository extends JpaRepository<Attraction,Integer> {

}
