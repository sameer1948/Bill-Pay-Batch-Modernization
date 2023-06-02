package com.bank.bpbm.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.bank.bpbm.dto.NewCustomerRequest;
import com.bank.bpbm.entity.Transaction;
import com.bank.bpbm.exceptions.TransactionSkipPolicy;
import com.bank.bpbm.utils.TransactionListener;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean(name = "customerFileReader")
	@StepScope
	public FlatFileItemReader<NewCustomerRequest> customerFileReader(){
		FlatFileItemReader<NewCustomerRequest> flatFileItemReader= new FlatFileItemReader<NewCustomerRequest>();
		flatFileItemReader.setName("CSVReader");
		flatFileItemReader.setResource(new ClassPathResource("customers.csv"));
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(customerLineMapper());
		
		return flatFileItemReader;
	}


	private LineMapper<NewCustomerRequest> customerLineMapper(){
		DefaultLineMapper<NewCustomerRequest> defaultLineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames("firstName","lastName","gender","email","phone","branch","accountType","accountBalance");
		
		//BeanWrapperFieldSetMapper<NewCustomerRequest> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		//beanWrapperFieldSetMapper.setTargetType(NewCustomerRequest.class);
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(customerFieldMapper());
		
		return defaultLineMapper;
		
	}
	
	@Bean(name = "customerFieldMapper")
	public CustomerFieldSetMapper customerFieldMapper(){
		return new CustomerFieldSetMapper();
	}
	
	@Bean(name = "customerProcessor")
	public ItemProcessor<NewCustomerRequest, NewCustomerRequest> customerProcessor(){
		return cust->cust;
	}
	
	@Bean(name = "customerWriter")
	public DataWriter customerWriter(){
		return new DataWriter(); 
	}
	
	@Bean(name = "customerStep")
	public Step customerStep() {
		
		return stepBuilderFactory.get("CustomerStep")
		                         .<NewCustomerRequest,NewCustomerRequest>chunk(10)
		                         .reader(customerFileReader())
		                         .processor(customerProcessor())
		                         .writer(customerWriter())
		                         .build();
	}
	
	@Bean(name="customerJob")
	public Job customerJob() {
		
		return jobBuilderFactory.get("CustomersJob")
								.flow(customerStep())
								.end()
								.build();
	}
	
	
	
	@Bean(name = "TransactionFileReader")
	@StepScope
	public FlatFileItemReader<Transaction> TransactionFileReader(){
		System.out.println("Reader In starred");
		FlatFileItemReader<Transaction> flatFileItemReader= new FlatFileItemReader<Transaction>();
		flatFileItemReader.setName("CSVReader");
		flatFileItemReader.setResource(new ClassPathResource("transactions.csv"));
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(transactionLineMapper());
		
		return flatFileItemReader;
	}

	public LineMapper<Transaction> transactionLineMapper(){
		DefaultLineMapper<Transaction> defaultLineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames("accountNo","transactionAmount","transactionType");
		
		BeanWrapperFieldSetMapper<Transaction> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(Transaction.class);
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return defaultLineMapper;
	}

	@Bean(name = "TransactionProcessor")
	public TransactionProcessor transactionProcessor() {
		return new TransactionProcessor();
	}
	
	@Bean(name = "TransactionWriter")
	public TransactionWriter transactoinWriter() {
		return new TransactionWriter();
	}
	
	@Bean(name = "TransactionSkipPolicy")
	public TransactionSkipPolicy myTransactionSkipPolicy() {
		return	new TransactionSkipPolicy();
	}
	
	@Bean(name = "TransactionListener")
	public TransactionListener myTransactionListener() {
		return	new TransactionListener();
	}
	
	@Bean(name = "TransactionStep")
	public Step transactionStep() {
		
		return stepBuilderFactory.get("step1")
		                         .<Transaction,Transaction>chunk(10)
		                         .reader(TransactionFileReader())
		                         .processor(transactionProcessor())
		                         .writer(transactoinWriter())
		                         .faultTolerant()
		                         .skipLimit(10)
		                         .listener(myTransactionListener())
		                         .skipPolicy(myTransactionSkipPolicy())
		                         .build();
	}
	
	@Bean(name = "TransactionJob")
	public Job transactionJob() {
		
		return jobBuilderFactory.get("TransactionJob")
								.flow(transactionStep())
								.end()
								.build();
	}
}
