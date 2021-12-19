package com.kgp.trips.peak.repository;

import com.kgp.trips.peak.entity.DeprecatedAttraction;
import org.springframework.data.jpa.repository.JpaRepository;


@Deprecated
public interface DeprecatedAttractionRepository extends JpaRepository<DeprecatedAttraction,Integer> {

}
