package com.hcl.training.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.training.model.Account;

import com.hcl.training.service.ServiceInf;

@RestController
@RequestMapping("/banking")
public class AccountController {
	@Autowired
	ServiceInf service;

	@PostMapping("/saveAccount")
	public @ResponseBody String saveAccount(@RequestBody Account account) {
		return service.saveAccount(account);

	}

	@GetMapping("/getbyAccount/{accountNumber}")
	public @ResponseBody Optional getAccountByNumber(@PathVariable String accountNumber) {
		Optional<Account> op = service.getAccountByNumber(accountNumber);
		if (op.isPresent())
			return op;
		else {
			Optional<String> op1 = Optional.ofNullable("Account Not Available");
			return op1;
		}
	}
@GetMapping("/getAccountbyId/{id}")
	public @ResponseBody Optional getAccountById(@PathVariable int id) {
		Optional<Account> op = service.getAccountById(id);
		if (op.isPresent())
			return op;
		else {
			Optional<String> op1 = Optional.ofNullable("Account Not Available");
			return op1;
		}
	}
}
