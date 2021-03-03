package com.hcl.training.service;

import java.util.Optional;

import com.hcl.training.model.Account;
import com.hcl.training.model.TransferAmount;

public interface ServiceInf {

	String saveAccount(Account account);

	Optional<Account> getAccountByNumber(String accountNumber);

	Optional<Account> getAccountById(int id);

	String transferAmount(TransferAmount transfer);

}
