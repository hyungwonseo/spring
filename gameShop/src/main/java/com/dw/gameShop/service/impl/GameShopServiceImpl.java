package com.dw.gameShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.gameShop.model.Game;
import com.dw.gameShop.model.Purchase;
import com.dw.gameShop.repository.GameShopRepository;
import com.dw.gameShop.repository.PurchaseRepository;
import com.dw.gameShop.service.GameShopService;

@Service
public class GameShopServiceImpl implements GameShopService{
	
	private GameShopRepository gameShopRepository;
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	public GameShopServiceImpl(GameShopRepository gameShopRepository,
			PurchaseRepository purchaseRepository) {
		super();
		this.gameShopRepository = gameShopRepository;
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public Game saveGame(Game game) {
		return gameShopRepository.save(game);
	}

	@Override
	public List<Game> getAllGames() {
		return gameShopRepository.findAll();
	}

	@Override
	public Game getGameById(long id) {
		return gameShopRepository.findById(id).orElseThrow(()->null);
	}

	@Override
	public Game updateGameById(Game game, long id) {
		Game existingGame = gameShopRepository.findById(id).orElseThrow(()->null);
		existingGame.setGenre(game.getGenre());
		existingGame.setImage(game.getImage());
		existingGame.setPrice(game.getPrice());
		existingGame.setText(game.getText());
		existingGame.setTitle(game.getTitle());
		gameShopRepository.save(existingGame);
		return existingGame;
	}

	@Override
	public void deleteGameById(long id) {
		gameShopRepository.findById(id).orElseThrow(()->null);
		gameShopRepository.deleteById(id);
	}

	@Override
	public Purchase savePurchase(Purchase purchase) {
		return purchaseRepository.save(purchase);
	}

	@Override
	public List<Purchase> getAllPurchase() {
		return purchaseRepository.findAll();
	}

	@Override
	public List<Purchase> getPurchaseById(String loginId) {
		return purchaseRepository.findByLoginId(loginId);
	}

}










