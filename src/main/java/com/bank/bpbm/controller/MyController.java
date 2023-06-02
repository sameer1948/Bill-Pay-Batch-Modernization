package com.bank.bpbm.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bpbm.dto.NewCustomerRequest;
import com.bank.bpbm.entity.Transaction;
import com.bank.bpbm.dao.CustomerServices;

@RestController
@RequestMapping("/v1/bpbm/")
public class MyController {
	
	@Autowired
	private CustomerServices customerServices;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("customerJob")
	private Job customerJob;
	
	@Autowired
	@Qualifier("TransactionJob")
	private Job transactionJob;
	
	@PostMapping("/newuser")
	public NewCustomerRequest createCustomer(@RequestBody NewCustomerRequest newCustomerRequest) {
		return customerServices.createNewCustomer(newCustomerRequest);
	}
		
	@PostMapping("/invoke")
	public String runMyJob() {
		
		JobParameters jobParameters = new JobParametersBuilder().addLong("Started At",System.currentTimeMillis()).toJobParameters();
		
		try {
			jobLauncher.run(customerJob, jobParameters);
			
		} catch (JobExecutionAlreadyRunningException e) {
		 
			e.printStackTrace();
		} catch (JobRestartException e) {
		 
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
		 
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
		 
			e.printStackTrace();
		}
		
		return "Done...!";
	}
	
	@PostMapping("/makepayment")
	public Transaction makePayment(@RequestBody Transaction transaction) {
		
		return customerServices.doDebit(transaction);
	}
	
	@PostMapping("/invoket")
	public String runTransactionsJob() {
		
		JobParameters jobParameters = new JobParametersBuilder().addLong("Started At",System.currentTimeMillis()).toJobParameters();
		System.out.println("Transations Started At: "+ System.currentTimeMillis());
		try {
			jobLauncher.run(transactionJob, jobParameters);
			
		} catch (JobExecutionAlreadyRunningException e) {
		 
			e.printStackTrace();
		} catch (JobRestartException e) {
		 
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
		 
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
		 
			e.printStackTrace();
		}
		
		return "Done...!";
	}
	
	//@PostMapping("/pending")
	@Scheduled(cron = "*/50 * * * * ?")
	public void runPendingTransactions() {
		System.out.println("----------------------------------------------------------------------------------------------\n"
				+ "JOB Started At: "+ System.currentTimeMillis());
		System.out.println(customerServices.runJob().toString());
		System.out.println("JOB Started At: "+ System.currentTimeMillis()+"\n----------------------------------------------------------------------------------------------");
	}
	

}
