package com.cbccafe.service;

import com.cbccafe.entity.SingleItem;
import com.cbccafe.payload.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SingleItemService {
    public ResponseEntity<ResponseMessage> createItem(SingleItem singleItem);
    public ResponseEntity<List<SingleItem>> list();
    public ResponseEntity<ResponseMessage> deleteItem(Long id);
    public Optional<SingleItem> singleItemById(Long id);
    public ResponseEntity<ResponseMessage> updateSingleItemById(SingleItem singleItem, Long id);
}
