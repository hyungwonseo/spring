package com.dw.gameShopBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.gameShopBackEnd.model.Game;

public interface GameShopRepository extends JpaRepository<Game, Long> {

}
