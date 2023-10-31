package com.dw.gameShopBackEnd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.gameShopBackEnd.model.Game;
import com.dw.gameShopBackEnd.model.Purchase;
import com.dw.gameShopBackEnd.repository.GameShopRepository;
import com.dw.gameShopBackEnd.repository.PurchaseRepository;
import com.dw.gameShopBackEnd.service.GameShopService;

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

}










