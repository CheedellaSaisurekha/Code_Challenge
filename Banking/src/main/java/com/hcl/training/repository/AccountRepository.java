package com.hcl.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.training.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from Account a where a.accountNumber=:accountNumber")
	Optional<Account> getAccountDetails(@Param("accountNumber")String accountNumber);
    
    @Modifying
    @Query("update Account a set a.balance=:amount where a.accountNumber=:fromAccount")
	void debitAmount(@Param("fromAccount")String fromAccount,@Param("amount")long amount);
    @Modifying
    @Query("update Account a set a.balance=:l where a.accountNumber=:toAccount")
	void creditAmount(@Param("toAccount")String toAccount,@Param("l")long l);

}
