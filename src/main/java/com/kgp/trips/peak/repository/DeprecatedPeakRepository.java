package com.kgp.trips.peak.repository;

import com.kgp.trips.peak.entity.DeprecatedPeak;
import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
public interface DeprecatedPeakRepository extends JpaRepository<DeprecatedPeak,Integer> {


}
