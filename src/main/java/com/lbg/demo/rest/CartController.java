package com.lbg.demo.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public Cart create(@RequestBody Cart newCart) {
		return this.service.create(newCart);
	}

	@GetMapping("/get")
	public List<Cart> readAll() {
		return this.service.readAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Cart> read(@PathVariable int id) {
		return this.service.read(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Cart> update(@PathVariable int id, @RequestBody Cart newCart) {
		return this.service.update(id, newCart);
	}

	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable int id) {
		return this.service.delete(id);
	}
}
