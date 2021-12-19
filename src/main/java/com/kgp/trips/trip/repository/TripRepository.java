package com.kgp.trips.trip.repository;

import com.kgp.trips.trip.entity.Region;
import com.kgp.trips.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TripRepository extends JpaRepository<Trip,Integer> {

}
