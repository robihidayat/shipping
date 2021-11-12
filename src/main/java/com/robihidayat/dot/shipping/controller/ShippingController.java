package com.robihidayat.dot.shipping.controller;


import com.robihidayat.dot.shipping.manager.ShippingManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

  private final ShippingManager shippingManager;

  public ShippingController(ShippingManager shippingManager) {
    this.shippingManager = shippingManager;
  }

  @PostMapping("/create")
  public ResponseEntity sigIn() {
    shippingManager.create();
    return ResponseEntity.noContent().build();
  }

}
