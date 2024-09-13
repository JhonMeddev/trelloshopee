package com.trelloshopee.trelloshopee.repository;

import com.trelloshopee.trelloshopee.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item, Long> {
}
