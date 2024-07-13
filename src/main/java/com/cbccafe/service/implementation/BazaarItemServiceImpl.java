package com.cbccafe.service.implementation;

import com.cbccafe.entity.BazaarItem;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.repository.BazaarItemRepository;
import com.cbccafe.service.BazaarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BazaarItemServiceImpl implements BazaarItemService {
    @Autowired
    BazaarItemRepository bazaarItemRepo;

    ResponseMessage responseMessage;

    @Override
    public ResponseEntity<ResponseMessage> addBazaarItem(BazaarItem bazaarItem){
        Integer countByName = bazaarItemRepo.countByName(bazaarItem.getName());
        if(countByName > 0){
            responseMessage = new ResponseMessage("Sorry! Item name already exists.");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            bazaarItem.setEnteredAt(LocalDate.now());
            bazaarItemRepo.save(bazaarItem);

            responseMessage = new ResponseMessage("Item name saved successfully.");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<BazaarItem>> list(){
        List<BazaarItem> list = bazaarItemRepo.findAll();
        return new ResponseEntity<List<BazaarItem>>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<BazaarItem>> itemById(Long id){
        Optional<BazaarItem> itemById = bazaarItemRepo.findById(id);
        if(itemById.isPresent()){
            return new ResponseEntity<Optional<BazaarItem>>(itemById, HttpStatus.OK);
        } else return null;
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteById(@PathVariable("id") Long id){
        if(bazaarItemRepo.countById(id).equals(0)){
            responseMessage = new ResponseMessage("Sorry! Invalid ID.");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            bazaarItemRepo.deleteById(id);
            responseMessage = new ResponseMessage("Item deleted successfully.");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage> editById(BazaarItem bazaarItem,Long id){
        Integer countByNameAndIdNot = bazaarItemRepo.countByNameAndIdNot(bazaarItem.getName(),id);
        if(countByNameAndIdNot > 0){
            responseMessage = new ResponseMessage("Sorry! Item name already exists.");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            Optional<BazaarItem> existingItemOptional = bazaarItemRepo.findById(id);
            if(existingItemOptional.isPresent()){
                BazaarItem existingBazaarItem = existingItemOptional.get();

                existingBazaarItem.setName(bazaarItem.getName());
                existingBazaarItem.setUpdatedAt(LocalDate.now());
                existingBazaarItem.setUpdatedBy(bazaarItem.getUpdatedBy());

                bazaarItemRepo.save(existingBazaarItem);

                responseMessage = new ResponseMessage("Item saved successfully.");
                return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
            } else {
                responseMessage = new ResponseMessage("Sorry! Invalid ID.");
                return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
