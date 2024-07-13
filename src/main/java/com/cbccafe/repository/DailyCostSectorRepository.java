package com.cbccafe.repository;

import com.cbccafe.entity.DailyCostSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyCostSectorRepository extends JpaRepository<DailyCostSector, Long> {
    Integer countBySectorName(String sectorName);
    Integer countById(Long id);
    Integer countBySectorNameAndIdNot(String sectorName,Long id);
}
