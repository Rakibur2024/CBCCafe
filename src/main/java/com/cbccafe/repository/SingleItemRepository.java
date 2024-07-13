package com.cbccafe.repository;

import com.cbccafe.entity.SingleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleItemRepository extends JpaRepository<SingleItem, Long> {
    Integer countByItemName(String singleItemName);
    Integer countById(Long id);
    Integer countByItemNameAndIdNot(String itemName,Long id);
}
