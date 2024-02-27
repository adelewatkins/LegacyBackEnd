package com.lbg.demo.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbg.demo.domain.Cart;
import com.lbg.demo.services.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

	private CartService service;

	public CartController(CartService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Cart> create(@RequestBody Cart newCart) {
		return this.service.createCart(newCart);
	}

	@GetMapping("/get")
	public List<Cart> getCarts() {
		return this.service.getCarts();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Cart> getCarts(@PathVariable int id) {
		return this.service.getCarts(id);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Cart> editCart(@PathVariable int id, @RequestBody Cart newCart) {
		return this.service.editCart(id, newCart);
	}

}
