package com.cbccafe.service;

import com.cbccafe.entity.BazaarItem;
import com.cbccafe.payload.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BazaarItemService {
    public ResponseEntity<ResponseMessage> addBazaarItem(BazaarItem bazaarItem);
    public ResponseEntity<List<BazaarItem>> list();
    public ResponseEntity<Optional<BazaarItem>> itemById(Long id);
    public ResponseEntity<ResponseMessage> deleteById(Long id);
    public ResponseEntity<ResponseMessage> editById(BazaarItem bazaarItem,Long id);
}
