package com.kgp.trips.peak.repository;

import com.kgp.trips.peak.entity.DeprecatedRegion;
import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
public interface RegionRepository extends JpaRepository<DeprecatedRegion,Integer> {


}
