package com.cbccafe.controller;

import com.cbccafe.entity.BazaarItem;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.service.BazaarItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bazaaItem")
public class BazaarItemController {

    @Autowired
    BazaarItemService bazaarItemService;

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addBazaarItem(@Valid @RequestBody BazaarItem bazaarItem){
        return bazaarItemService.addBazaarItem(bazaarItem);
    }

    @GetMapping("/list")
    public ResponseEntity<List<BazaarItem>> list(){
        return bazaarItemService.list();
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<Optional<BazaarItem>> byid(@PathVariable("id") Long id){
       return bazaarItemService.itemById(id);
    }

    @PutMapping("/edit/byid/{id}")
    public ResponseEntity<ResponseMessage> editById(@RequestBody BazaarItem bazaarItem,@PathVariable("id") Long id){
        return bazaarItemService.editById(bazaarItem,id);
    }

    @DeleteMapping("/delete/byid/{id}")
    public ResponseEntity<ResponseMessage> deleteById(@PathVariable("id") Long id){
        return bazaarItemService.deleteById(id);
    }

}
