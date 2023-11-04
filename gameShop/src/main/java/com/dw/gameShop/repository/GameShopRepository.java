package com.dw.gameShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.gameShop.model.Game;

public interface GameShopRepository extends JpaRepository<Game, Long> {

}
