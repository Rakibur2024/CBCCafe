package com.cbccafe.controller;

import com.cbccafe.entity.SingleItem;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.service.SingleItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/single-item")
public class SingleItemController {

    @Autowired
    SingleItemService singleItemService;

    ResponseMessage responseMessage;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createItem(@Valid @RequestBody SingleItem singleItem){
        if(singleItem.getPrice() == null || singleItem.getPrice().equals(0.0) || singleItem.getPrice().isNaN()){
            responseMessage = new ResponseMessage("price is required");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        return singleItemService.createItem(singleItem);
    }

    @GetMapping("/list")
    public ResponseEntity<List<SingleItem>> list(){
        return singleItemService.list();
    }

    @GetMapping("/byid/{id}")
    public Optional<SingleItem> singleItemById(@Param("id") Long id){
        return singleItemService.singleItemById(id);
    }

    @PutMapping("/update/byid/{id}")
    public ResponseEntity<ResponseMessage> updateEmployeeById(@Valid @RequestBody SingleItem singleItem, @Param("id") Long id){
        return singleItemService.updateSingleItemById(singleItem,id);
    }

    @DeleteMapping("/delete/byid/{id}")
    public ResponseEntity<ResponseMessage> deleteItem(@PathVariable("id") Long id){
        return singleItemService.deleteItem(id);
    }

}
