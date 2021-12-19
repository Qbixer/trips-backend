package com.kgp.trips.trip.repository;

import com.kgp.trips.trip.entity.Attraction;
import com.kgp.trips.trip.entity.Peak;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PeakRepository extends JpaRepository<Peak,Integer> {

}
