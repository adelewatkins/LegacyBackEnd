package com.lbg.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbg.demo.domain.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
