package com.robihidayat.dot.shipping.controller;


import com.robihidayat.dot.shipping.manager.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountManager manager;

  @Autowired
  public AccountController(AccountManager manager) {
    this.manager = manager;
  }

  @PostMapping("/signIn")
  public ResponseEntity sigIn() {
    manager.signIn();
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/signUp")
  public ResponseEntity signUp(){
    manager.signUp();
    return ResponseEntity.noContent().build();
  }
}
