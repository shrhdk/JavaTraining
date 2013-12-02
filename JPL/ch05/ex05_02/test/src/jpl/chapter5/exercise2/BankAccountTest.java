package jpl.chapter5.exercise2;

import org.junit.*;
import static org.junit.Assert.*;

public class BankAccountTest {

	@Before
	public void setup() {
		BankAccount.resetCount();
	}

	@Test
	public void testBankAccount() {
		BankAccount bankAccount = new BankAccount();
		
		assertEquals(0, bankAccount.number);
		assertEquals(0, bankAccount.showBalance());
		assertEquals(false, bankAccount.history().hasNext());

	}

	@Test
	public void testShowBalance() {
		BankAccount bankAccount = new BankAccount();
		
		bankAccount.deposit(10);
		assertEquals(10, bankAccount.showBalance());
		
		bankAccount.deposit(15);
		assertEquals(25, bankAccount.showBalance());
	}

	@Test
	public void testDeposit() {
		BankAccount bankAccount = new BankAccount();
		
		bankAccount.deposit(10);
		assertEquals(10, bankAccount.showBalance());
		
		bankAccount.deposit(15);
		assertEquals(25, bankAccount.showBalance());
	}
	
	@Test
	public void testWithdraw() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.deposit(30);
		assertEquals(30, bankAccount.showBalance());
		bankAccount.withdraw(10);
		
		assertEquals(20, bankAccount.showBalance());
	}

	@Test
	public void transfer() {
		BankAccount bankAccount1 = new BankAccount();
		BankAccount bankAccount2 = new BankAccount();
		bankAccount1.deposit(60);
		bankAccount2.deposit(30);
		bankAccount2.transfer(bankAccount1, 10);
		
		assertEquals(50, bankAccount1.showBalance());
		assertEquals(40, bankAccount2.showBalance());

	}
	
	@Test
	public void hisotry() {
		BankAccount bankAccount1 = new BankAccount();
		BankAccount bankAccount2 = new BankAccount();
		bankAccount1.deposit(60);
		bankAccount2.deposit(30);
		bankAccount2.transfer(bankAccount1, 10);
		bankAccount2.withdraw(40);
		
		BankAccount.History history1 = bankAccount1.history();
		BankAccount.History history2 = bankAccount2.history();

		assertEquals(true, history1.hasNext());
		assertEquals("0: deposit 60", history1.next().toString());
		assertEquals("0: withdraw 10", history1.next().toString());
		assertEquals(null, history1.next());

		assertEquals(true, history2.hasNext());
		assertEquals("1: deposit 30", history2.next().toString());
		assertEquals("1: deposit 10", history2.next().toString());
		assertEquals("1: withdraw 40", history2.next().toString());
		assertEquals(false, history2.hasNext());
		assertEquals(null, history2.next());
	}
	
}
