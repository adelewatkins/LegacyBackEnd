package com.lbg.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.demo.domain.Cart;
import com.lbg.demo.repo.CartRepo;

@Service
public class CartService {

	private CartRepo repo;

	public CartService(CartRepo repo) {
		super();
		this.repo = repo;
	}

	public Cart create(Cart newCart) {
		return this.repo.save(newCart);
	}

	public List<Cart> readAll() {

		return this.repo.findAll();
	}

	public ResponseEntity<Cart> read(int id) {

		Optional<Cart> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(found.get());
	}

	public ResponseEntity<Cart> update(int id, Cart newCart) {
		Optional<Cart> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Cart existing = found.get();

		existing.setName(newCart.getName());

		Cart body = this.repo.save(existing);

		return ResponseEntity.ok(body);
	}

	public boolean delete(int id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}
}
