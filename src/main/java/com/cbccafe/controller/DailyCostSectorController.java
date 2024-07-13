package com.cbccafe.controller;

import com.cbccafe.entity.DailyCostSector;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.service.DailyCostSectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/daily-cost-sector")
public class DailyCostSectorController {
    @Autowired
    DailyCostSectorService dailyCostSectorService;

    ResponseMessage responseMessage;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createSector(@Valid @RequestBody DailyCostSector dailyCostSector){
        return dailyCostSectorService.createSector(dailyCostSector);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DailyCostSector>> list(){
        return dailyCostSectorService.list();
    }

    @GetMapping("/byid/{id}")
    public Optional<DailyCostSector> dailyCostById(@Param("id") Long id){
        return dailyCostSectorService.sectorById(id);
    }

    @PutMapping("/update/byid/{id}")
    public ResponseEntity<ResponseMessage> updateEmployeeById(@Valid @RequestBody DailyCostSector dailyCostSector, @Param("id") Long id){
        return dailyCostSectorService.updateSectorById(dailyCostSector,id);
    }

    @DeleteMapping("/delete/byid/{id}")
    public ResponseEntity<ResponseMessage> deleteSector(@PathVariable("id") Long id){
        return dailyCostSectorService.deleteSector(id);
    }
}
