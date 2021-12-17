package com.kgp.trips.peak.repository;

import com.kgp.trips.peak.entity.DeprecatedMountainRange;
import org.springframework.data.jpa.repository.JpaRepository;


@Deprecated
public interface MountainRangeRepository extends JpaRepository<DeprecatedMountainRange,Integer> {

}
