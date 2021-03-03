package com.hcl.training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hcl.training.model.Account;
import com.hcl.training.model.TransferAmount;
import com.hcl.training.service.ServiceImpl;

 

 
@SpringBootTest
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class })
class BankingApplicationTests {

	Account account;
	TransferAmount transferAmount;
	@Autowired
	ServiceImpl service;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setUp() {
		account = new Account();
		transferAmount = new TransferAmount();
	}

	@Test
	public void testAccount() {
		account.setAccountNumber("1234567890");
		account.setAccountHolderName("s");
		account.setBalance(5000);
		service.saveAccount(account);
	}

	@Test
	public void testAccountAlreadyExists() {
		account.setAccountNumber("1234567890");
		account.setAccountHolderName("s");
		account.setBalance(5000);
		service.saveAccount(account);
	}

	@Test
	public void testAccountwithWrongnumber() {
		account.setAccountNumber("1234567899");
		account.setAccountHolderName("s");
		account.setBalance(5000);
		service.saveAccount(account);
	}

	@Test
	public void testAccountByNumber() {
		service.getAccountByNumber("1234567890");
	}

	@Test
	public void testAccountByWrongNumber() {
		service.getAccountByNumber("1234567899");
	}

	@Test
	public void testAccountById() {
		service.getAccountById(1);
	}

	@Test
	public void testAccountByWrongId() {
		service.getAccountById(24);
	}

	@Test
	public void testTransferFund() {
		transferAmount.setToAccount("1234567890");
		transferAmount.setFromAccount("1234512345");
		transferAmount.setAmount(1000);
		service.transferAmount(transferAmount);
	}

	@Test
	public void testTransferFundWithNoSender() {
		transferAmount.setFromAccount("1243567899");
		transferAmount.setToAccount("1234512345");
		transferAmount.setAmount(5000);
		service.transferAmount(transferAmount);
	}

	@Test
	public void testTransferFundWithNoReciever() {
		transferAmount.setFromAccount("1234512345");
		transferAmount.setToAccount("1234567890");
		transferAmount.setAmount(1000);
		service.transferAmount(transferAmount);
	}

	@Test
	public void testTransferFundWithNLessFunds() {
		transferAmount.setFromAccount("1234567890");
		transferAmount.setToAccount("1234512345");
		transferAmount.setAmount(100000);
		service.transferAmount(transferAmount);
	}

	@Test
	public void testTransferFundWithNegativeValue() {
		transferAmount.setFromAccount("1234567890");
		transferAmount.setToAccount("1234512345");
		transferAmount.setAmount(-10000);
		service.transferAmount(transferAmount);
	}

    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @Test
    public void testIt() {
        ServletInitializer servletInitializer = new ServletInitializer();
        when(springApplicationBuilder.sources(BankingApplication.class)).thenReturn(springApplicationBuilder);
       SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);
        verify(springApplicationBuilder).sources(BankingApplication.class);
        assertEquals(springApplicationBuilder, result);
    }
}