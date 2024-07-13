package com.cbccafe.repository;

import com.cbccafe.entity.BazaarItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BazaarItemRepository extends JpaRepository<BazaarItem, Long> {
    Integer countByName(String name);
    Integer countById(Long id);
    Integer countByNameAndIdNot(String name, Long id);
}
