package com.bank.bpbm.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.bank.bpbm.entity.Transaction;

public class TransactionFileReader extends FlatFileItemReader<Transaction>{
	
	public FlatFileItemReader<Transaction> fileReader(){
		FlatFileItemReader<Transaction> flatFileItemReader= new FlatFileItemReader<Transaction>();
		flatFileItemReader.setName("CSVReader");
		flatFileItemReader.setResource(new ClassPathResource("transactions.csv"));
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		
		return flatFileItemReader;
	}

	private LineMapper<Transaction> lineMapper(){
		DefaultLineMapper<Transaction> defaultLineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames("firstName","lastName","gender","email","phone","branch","accountType","accountBalance");
		
		BeanWrapperFieldSetMapper<Transaction> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(Transaction.class);
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return defaultLineMapper;
		
	}
	
}
