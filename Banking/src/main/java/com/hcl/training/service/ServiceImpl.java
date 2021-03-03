package com.hcl.training.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.training.model.Account;
import com.hcl.training.model.TransferAmount;
import com.hcl.training.repository.AccountRepository;

@Service
@Transactional
public class ServiceImpl implements ServiceInf{
private static final Object lock = new Object();
@Autowired
AccountRepository accountRepo;
	@Override
	public String saveAccount(Account account) {
		if(account.getAccountNumber().length()==10) {
		Optional<Account>op=accountRepo.getAccountDetails(account.getAccountNumber());
		if(op.isPresent())
			return "Accountnumber already exists";
		else {
			accountRepo.save(account);
			return "Account Created Sucessfully";
		}
		}
		else
			return "Accountnumber must contain 10 digits";
		
	}
	@Override
	public Optional<Account> getAccountByNumber(String accountNumber) {
		return accountRepo.getAccountDetails(accountNumber);
	}
	@Override
	public String transferAmount(TransferAmount transfer) {
	synchronized (lock) {
		if(transfer.getAmount()>0) {
		Optional<Account>op=accountRepo.getAccountDetails(transfer.getFromAccount());
		if(!op.isPresent())
			return "Sender Account Doesn't exists";		
		if(op.get().getBalance()>=transfer.getAmount()) {
			Optional<Account>op1=accountRepo.getAccountDetails(transfer.getToAccount());
			if(op1.isPresent()) {
				accountRepo.debitAmount(transfer.getFromAccount(),op.get().getBalance()-transfer.getAmount());
				accountRepo.creditAmount(transfer.getToAccount(),op1.get().getBalance()+transfer.getAmount());
				return "Amount Transferred Successfully";
			}
			else
				return "Receiver Account Doesn't exists";
		}
		else
		return "InSufficient Funds in Sender Account";
	}
	else
		return "Amount is Not Valid";
	}
	}
	@Override
	public Optional<Account> getAccountById(int id) {
		
		return accountRepo.findById(id);
	}

}
