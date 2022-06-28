package com.example.envelopeservice;

import com.example.envelopeservice.models.Account;
import com.example.envelopeservice.models.AccountType;
import com.example.envelopeservice.models.Envelope;
import com.example.envelopeservice.repository.AccountRepository;
import com.example.envelopeservice.repository.EnvelopeRepository;
import com.example.envelopeservice.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class EnvelopeserviceApplicationTests {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	EnvelopeRepository envelopeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void canSaveNewAccount(){
		Account account = new Account("My Account", AccountType.CURRENT);
		accountRepository.save(account);
		List<Account> allAccounts = accountRepository.findAll();
		Assertions.assertEquals(2, allAccounts.size());
	}

	@Test
	public void canSaveEnvelope(){
		Account secondAccount = new Account("My Account2", AccountType.CURRENT);
		Envelope travel = new Envelope("Travel", secondAccount);
		accountRepository.save(secondAccount);
		envelopeRepository.save(travel);
		List<Envelope> allEnvelopes = envelopeRepository.findAll();
		Account foundAccount = accountRepository.findById(1L).get();
		assertEquals(foundAccount.getEnvelopes().size(), 1);
		Assertions.assertEquals(1, allEnvelopes.size());
	}

}
