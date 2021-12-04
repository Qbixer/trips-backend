package com.kgp.trips.peak.repository;

import com.kgp.trips.peak.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttractionRepository extends JpaRepository<Attraction,Integer> {

}
