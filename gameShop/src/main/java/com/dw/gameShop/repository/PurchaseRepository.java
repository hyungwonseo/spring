package com.dw.gameShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.gameShop.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
