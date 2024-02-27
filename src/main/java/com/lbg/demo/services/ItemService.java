package com.lbg.demo.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.demo.domain.Item;
import com.lbg.demo.repo.ItemRepo;

@Service
public class ItemService {

	private ItemRepo repo;

	public ItemService(ItemRepo repo) {
		super();
		this.repo = repo;
	}

	public ResponseEntity<Item> createItem(Item newItem) {
		Item created = this.repo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public List<Item> getItems() {
		return this.repo.findAll();
	}

}
