package com.bank.bpbm.dao;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bpbm.dto.NewCustomerRequest;
import com.bank.bpbm.entity.Account;
import com.bank.bpbm.entity.CronJob;
import com.bank.bpbm.entity.Customer;
import com.bank.bpbm.entity.Status;
import com.bank.bpbm.entity.Transaction;
import com.bank.bpbm.exceptions.CustomerAlreadyExsistsException;
import com.bank.bpbm.exceptions.InSufficientFundsException;
import com.bank.bpbm.exceptions.InValidAccountNoException;
import com.bank.bpbm.repository.AccountRepository;
import com.bank.bpbm.repository.CronJobRepository;
import com.bank.bpbm.repository.CustomerRepository;
import com.bank.bpbm.repository.StatusRepository;
import com.bank.bpbm.repository.TransactionRepository;
import com.bank.bpbm.utils.Constants;

@Service
public class CustomerServices {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private CronJobRepository cornJobRepository;
	
	public NewCustomerRequest createNewCustomer(NewCustomerRequest newCustomerRequest) {
		
		Customer customer = newCustomerRequest.getCustomer();
		Account account   = newCustomerRequest.getAccount();
		
		if(validateCustomer(customer)) {
			customer = customerRepository.save(customer);
		
			account.setCustomerId(customer.getCustomerId());		
			account  = accountRepository.save(account);
		
			return new NewCustomerRequest(customer,account);
		}else {
			throw new CustomerAlreadyExsistsException(customer.getCustomerName() +" Already Exsists in Database...!");
		}
	
	}
	
	public void createNewCustomer(List<? extends NewCustomerRequest> newCustomerRequests) {
		newCustomerRequests.forEach((newCustomerRequest)->{
			Customer customer = newCustomerRequest.getCustomer();
			Account account   = newCustomerRequest.getAccount();
			
			if(validateCustomer(customer)) {
				customer = customerRepository.save(customer);
			
				account.setCustomerId(customer.getCustomerId());		
				account  = accountRepository.save(account);
			
				//return new NewCustomerRequest(customer,account);
				System.out.println("  "+new NewCustomerRequest(customer,account));
			}else {
				throw new CustomerAlreadyExsistsException(customer.getCustomerName() +" Already Exsists in Database...!");
			}	
		});
		
		
	
	}
	
	public boolean validateCustomer(Customer customer) {
		List<Customer> customers= customerRepository.validateCustomer(customer.getCustomerName(), customer.getGender(), customer.getEmail(), customer.getPhone(), customer.getBranch());
				customers.stream().forEach(System.out::println);
		return customers.size() == 0 ? true : false;
	}

	
	public Transaction doDebit(Transaction transaction) {
		
		Account account = null;
		
		try{
			account = balanceEnquiry(transaction.getAccountNo());
		}catch(NoSuchElementException e) {
			throw new InValidAccountNoException("Account No: "+transaction.getAccountNo()+" is Invalid , Please check and try again...!");
		}
		
		if(account.getAccountBalance() >= transaction.getTransactionAmount()) {
			double tAmount = transaction.getTransactionAmount();
			transaction.setTransactionTimestamp(new Date().toLocaleString());
			transaction.setCustomerId(account.getCustomerId());
			transaction.setBeforeBalance(account.getAccountBalance());
			transaction.setTransactionAmount(tAmount);
			transaction.setAfterBalance(account.getAccountBalance() - tAmount);
			
			withdraw(transaction.getAccountNo(),tAmount);
			Transaction completedTransaction = transactionRepository.save(transaction);
			
			Status status = new Status();
			status.setAccountNo(transaction.getAccountNo());
			status.setStatus(Constants.COMPLETED);
			status.setTransactionId(transaction.getTransactionId());
			status.setTransactionType(transaction.getTransactionType());
			
			statusRepository.save(status);
			
			
			return completedTransaction;
			
		}else
			throw new InSufficientFundsException("InSufficientFunds to make this Transaction ");
			
	}

	public void docredit(Transaction transaction) {
		Account account = null;
		
		try{
			account = balanceEnquiry(transaction.getAccountNo());
		}catch(NoSuchElementException e) {
			throw new InValidAccountNoException("Account No: "+transaction.getAccountNo()+" is Invalid , Please check and try again...!");
		}
		
		double tAmount = transaction.getTransactionAmount();
		transaction.setTransactionTimestamp(new Date().toLocaleString());
		transaction.setCustomerId(account.getCustomerId());
		//transaction.setBeforeBalance(account.getAccountBalance());
		transaction.setTransactionAmount(tAmount);
		//transaction.setAfterBalance(account.getAccountBalance() - tAmount);
		
		//withdraw(transaction.getAccountNo(),tAmount);
		Transaction completedTransaction = transactionRepository.save(transaction);
		
		Status status = new Status();
		status.setAccountNo(completedTransaction.getAccountNo());
		status.setStatus(Constants.PENDING);
		status.setTransactionId(completedTransaction.getTransactionId());
		status.setTransactionType(completedTransaction.getTransactionType());
		
		statusRepository.save(status);
		
		
	}
	
	public Account balanceEnquiry(Integer accountNo) throws NoSuchElementException{
		return accountRepository.findById(accountNo).get();
	}
	
	public Account withdraw(Integer accountNo,Double amount) throws NoSuchElementException{
		Account account = accountRepository.findById(accountNo).get();
		account.setAccountBalance(account.getAccountBalance() - amount);
		return accountRepository.saveAndFlush(account);
	}
	
	public CronJob runJob() {
		
		CronJob cronJob = new CronJob();
		cronJob.setJobStartTime(""+System.currentTimeMillis());
		cronJob.setTransactionType(Constants.DEPOSIT);
		
		cronJob = cornJobRepository.save(cronJob);
		
		long count = runDeposits(cronJob.getJobId());
		
		cronJob.setJobEndTime(""+System.currentTimeMillis());
		cronJob.setJobStatus(count + " records processed successfully");
		
		cronJob = cornJobRepository.saveAndFlush(cronJob);
		
		return cronJob;
	}
	
	public long runDeposits(String jobId) {
		List<Status> statuses = statusRepository.findByStatus(Constants.PENDING);
		statuses.stream().forEach(System.out::println);
		
		Long count = statuses.stream().count();
		
		statuses.stream().forEach((status)->{
			
			Transaction transaction = transactionRepository.findById(status.getTransactionId()).get();
			Account account = accountRepository.findById(transaction.getAccountNo()).get();
			
			double tAmount = transaction.getTransactionAmount();
			transaction.setTransactionTimestamp(new Date().toLocaleString());
			transaction.setCustomerId(account.getCustomerId());
			transaction.setBeforeBalance(account.getAccountBalance());
			transaction.setTransactionAmount(tAmount);
			transaction.setAfterBalance(account.getAccountBalance() + tAmount);
			
			transactionRepository.saveAndFlush(transaction);
			
			status.setJobId(jobId);
			status.setStatus(Constants.COMPLETED);
			
			statusRepository.saveAndFlush(status);
			
		});
		
		return count; 
		
	}
}
