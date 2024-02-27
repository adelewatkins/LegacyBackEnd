package com.lbg.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbg.demo.domain.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
