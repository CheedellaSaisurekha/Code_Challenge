package com.hcl.training.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferAmount {
	String fromAccount;
	String toAccount;
	long amount;
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}

}
