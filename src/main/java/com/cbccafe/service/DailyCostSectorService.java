package com.cbccafe.service;

import com.cbccafe.entity.DailyCostSector;
import com.cbccafe.payload.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DailyCostSectorService {
    public ResponseEntity<ResponseMessage> createSector(DailyCostSector dailyCostSector);
    public ResponseEntity<List<DailyCostSector>> list();
    public ResponseEntity<ResponseMessage> deleteSector(Long id);
    public Optional<DailyCostSector> sectorById(Long id);
    public ResponseEntity<ResponseMessage> updateSectorById(DailyCostSector dailyCostSector, Long id);
}
