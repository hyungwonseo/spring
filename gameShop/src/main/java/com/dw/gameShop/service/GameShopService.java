package com.dw.gameShop.service;

import java.util.List;

import com.dw.gameShop.model.Game;
import com.dw.gameShop.model.Purchase;

public interface GameShopService {
	
	Game saveGame(Game game);
	List<Game> getAllGames();
	Game getGameById(long id);
    Game updateGameById(Game game, long id);
    void deleteGameById(long id);
    Purchase savePurchase(Purchase purchase);
    List<Purchase> getAllPurchase();
    List<Purchase> getPurchaseById(String loginId);
}
