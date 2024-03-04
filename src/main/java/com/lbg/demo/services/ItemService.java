package com.lbg.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.demo.domain.Cart;
import com.lbg.demo.domain.Item;
import com.lbg.demo.repo.CartRepo;
import com.lbg.demo.repo.ItemRepo;

@Service
public class ItemService {

	private ItemRepo repo;
	private CartRepo cartRepo;

	public ItemService(ItemRepo repo, CartRepo cartRepo) {
		super();
		this.repo = repo;
		this.cartRepo = cartRepo;
	}

	public ResponseEntity<Item> createItem(Item newItem) {
		Item created = this.repo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public List<Item> getItems() {
		return this.repo.findAll();
	}

	public ResponseEntity<Item> getItems(int id) {
		Optional<Item> found = this.repo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item body = found.get();
		return ResponseEntity.ok(body);
	}

	public ResponseEntity<Item> editItem(int id, Item newItem) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item existing = found.get();

		if (newItem.getName() != null) {
			existing.setName(newItem.getName());
		}
		if (newItem.getPrice() != 0) {
			existing.setPrice(newItem.getPrice());
		}
		if (newItem.getQuantity() != null) {
			existing.setQuantity(newItem.getQuantity());
		}

		Item edited = this.repo.save(existing);

		return ResponseEntity.ok(edited);
	}

	public ResponseEntity<Item> checkOut(int itemId, int cartId) {
		Optional<Item> toCheckOut = this.repo.findById(itemId);

		if (toCheckOut.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

		Item existing = toCheckOut.get();

		if (existing.getCart() != null) {
			return new ResponseEntity<Item>(HttpStatus.BAD_REQUEST);
		}

		Optional<Cart> customer = this.cartRepo.findById(cartId);

		if (customer.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

		existing.setCart(customer.get());
		existing.setQuantity(0);
		Item updated = this.repo.save(existing);

		return ResponseEntity.ok(updated);

	}

	public ResponseEntity<Item> checkIn(int itemId) {
		Optional<Item> toCheckOut = this.repo.findById(itemId);

		if (toCheckOut.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

		Item existing = toCheckOut.get();

		existing.setCart(null);
		existing.setQuantity(1);

		Item checkedIn = this.repo.save(existing);

		return ResponseEntity.ok(checkedIn);
	}

	public boolean delete(int id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}

}
