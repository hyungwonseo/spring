package com.dw.gameShopBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.gameShopBackEnd.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
