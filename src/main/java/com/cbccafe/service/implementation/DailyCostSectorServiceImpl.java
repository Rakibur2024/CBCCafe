package com.cbccafe.service.implementation;

import com.cbccafe.entity.DailyCostSector;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.repository.DailyCostSectorRepository;
import com.cbccafe.service.DailyCostSectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DailyCostSectorServiceImpl implements DailyCostSectorService {
    @Autowired
    DailyCostSectorRepository dailyCostSectorRepo;
    
    ResponseMessage responseMessage;


    @Override
    public ResponseEntity<ResponseMessage> createSector(DailyCostSector dailyCostSector){
        Integer countByName = dailyCostSectorRepo.countBySectorName(dailyCostSector.getSectorName());
        if(countByName > 0){
            responseMessage = new ResponseMessage("Sorry! Sector Name already exists");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            dailyCostSector.setEnteredAt(LocalDate.now());

            dailyCostSectorRepo.save(dailyCostSector);

            responseMessage = new ResponseMessage("Sector Name saved successfully");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<DailyCostSector>> list(){
        List<DailyCostSector> sectors = dailyCostSectorRepo.findAll();
        return new ResponseEntity<List<DailyCostSector>>(sectors, HttpStatus.OK);
    }

    @Override
    public Optional<DailyCostSector> sectorById(Long id){
        Optional<DailyCostSector> DailyCostSector = dailyCostSectorRepo.findById(id);
        return DailyCostSector;
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteSector(Long id){
        if(dailyCostSectorRepo.countById(id).equals(0)){
            responseMessage = new ResponseMessage("Sorry! Invalid ID");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        dailyCostSectorRepo.deleteById(id);
        responseMessage = new ResponseMessage("Sector deleted successfully");
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessage> updateSectorById(DailyCostSector dailyCostSector, Long id){
        Integer sectorExistence = dailyCostSectorRepo.countBySectorNameAndIdNot(dailyCostSector.getSectorName(),id);
        if(sectorExistence > 0){
            responseMessage = new ResponseMessage("Sorry! Sector already exists.");
            return new ResponseEntity<ResponseMessage>(responseMessage,HttpStatus.BAD_REQUEST);
        } else {
            Optional<DailyCostSector> existingDailyCostSector = dailyCostSectorRepo.findById(id);
            if(existingDailyCostSector.isPresent()){
                DailyCostSector sector = existingDailyCostSector.get();

                sector.setSectorName(dailyCostSector.getSectorName());
                sector.setUpdatedAt(LocalDate.now());
                sector.setUpdatedBy(dailyCostSector.getUpdatedBy());

                dailyCostSectorRepo.save(sector);

                responseMessage = new ResponseMessage("sector saved successfully");
                return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
            } else {
                ResponseMessage responseMessage = new ResponseMessage("Sorry! Invalid ID Number");
                return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
            }
        }
    }
    
}
