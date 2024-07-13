package com.cbccafe.service.implementation;

import com.cbccafe.entity.SingleItem;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.repository.SingleItemRepository;
import com.cbccafe.service.SingleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SingleItemServiceImpl implements SingleItemService {
    @Autowired
    SingleItemRepository singleItemRepo;

    ResponseMessage responseMessage;

    @Override
    public ResponseEntity<ResponseMessage> createItem(SingleItem singleItem){
        Integer countByName = singleItemRepo.countByItemName(singleItem.getItemName());
        if(countByName > 0){
            responseMessage = new ResponseMessage("Sorry! Item Name already exists");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            singleItem.setActiveStatus(1);
            singleItem.setApprovalStatus(1);
            singleItem.setEnteredAt(LocalDate.now());

            singleItemRepo.save(singleItem);

            responseMessage = new ResponseMessage("Item Name saved successfully");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<SingleItem>> list(){
        List<SingleItem> items = singleItemRepo.findAll();
        return new ResponseEntity<List<SingleItem>>(items, HttpStatus.OK);
    }

    @Override
    public Optional<SingleItem> singleItemById(Long id){
        Optional<SingleItem> singleItem = singleItemRepo.findById(id);
        return singleItem;
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteItem(Long id){
        if(singleItemRepo.countById(id).equals(0)){
            responseMessage = new ResponseMessage("Sorry! Invalid ID");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        singleItemRepo.deleteById(id);
        responseMessage = new ResponseMessage("Item deleted successfully");
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessage> updateSingleItemById(SingleItem singleItem, Long id){
        Integer itemExistence = singleItemRepo.countByItemNameAndIdNot(singleItem.getItemName(),id);
        if(itemExistence > 0){
            responseMessage = new ResponseMessage("Sorry! Item already exists.");
            return new ResponseEntity<ResponseMessage>(responseMessage,HttpStatus.BAD_REQUEST);
        } else {
            Optional<SingleItem> existingSingleItem = singleItemRepo.findById(id);
            if(existingSingleItem.isPresent()){
                SingleItem item = existingSingleItem.get();

                item.setItemName(singleItem.getItemName());
                item.setPrice(singleItem.getPrice());
                item.setApprovalStatus(singleItem.getApprovalStatus());
                item.setActiveStatus(singleItem.getActiveStatus());
                item.setUpdatedAt(LocalDate.now());
                item.setUpdatedBy(singleItem.getUpdatedBy());

                singleItemRepo.save(item);

                responseMessage = new ResponseMessage("Item saved successfully");
                return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
            } else {
                ResponseMessage responseMessage = new ResponseMessage("Sorry! Invalid ID Number");
                return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
