package com.dw.gameShop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.gameShop.model.Game;
import com.dw.gameShop.model.Purchase;
import com.dw.gameShop.service.GameShopService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins="http://localhost:3000", 
	methods= {RequestMethod.GET, RequestMethod.POST})
public class GameShopController {
	
	private GameShopService gameShopService;
	@Autowired
	public GameShopController(GameShopService gameShopService) {
		super();
		this.gameShopService = gameShopService;
	}
	
	@PostMapping()
	public ResponseEntity<Game> saveGame(@RequestBody Game game) {
		return new ResponseEntity<Game>(
				gameShopService.saveGame(game), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Game>> getAllGames() {
		return new ResponseEntity<List<Game>>(
				gameShopService.getAllGames(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Game> getGameById(@PathVariable long id) {
		return new ResponseEntity<Game>(
				gameShopService.getGameById(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Game> updateGameById(
			@RequestBody Game game, @PathVariable long id) {
		return new ResponseEntity<Game>(
				gameShopService.updateGameById(game, id), HttpStatus.OK);				
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteGameById(@PathVariable long id) {
		return new ResponseEntity<String>(
				"Successfully deleted!", HttpStatus.OK);
	}
	
	@PostMapping("purchase")
	public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {
		return new ResponseEntity<Purchase>(
				gameShopService.savePurchase(purchase), HttpStatus.OK);
	}
	
	@PostMapping("purchaselist")
	public ResponseEntity<List<Purchase>> savePurchaseList(
			@RequestBody List<Purchase> purchaseList) {
		List<Purchase> savedPurchaseList = new ArrayList<Purchase>();
		for (Purchase purchase : purchaseList) {
			savedPurchaseList.add(gameShopService.savePurchase(purchase));
		}
		return new ResponseEntity<List<Purchase>>(savedPurchaseList, HttpStatus.OK);
	}
	
	@GetMapping("purchase")
	public ResponseEntity<List<Purchase>> getAllPurchase() {
		return new ResponseEntity<List<Purchase>>(
				gameShopService.getAllPurchase(), HttpStatus.OK);
	}
	
	@GetMapping("purchase/{loginId}")
	public ResponseEntity<List<Purchase>> getPurchaseById(@PathVariable String loginId) {
		return new ResponseEntity<List<Purchase>>(
				gameShopService.getPurchaseById(loginId), HttpStatus.OK);
	}
	
}










