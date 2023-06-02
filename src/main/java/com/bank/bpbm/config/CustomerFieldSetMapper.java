package com.bank.bpbm.config;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.bank.bpbm.dto.NewCustomerRequest;
import com.bank.bpbm.entity.Account;
import com.bank.bpbm.entity.Customer;

@Component
public class CustomerFieldSetMapper implements FieldSetMapper<NewCustomerRequest>{
	
	@Override
	public NewCustomerRequest mapFieldSet(FieldSet fieldSet) throws BindException {
		System.out.println("In Reader "+ fieldSet.toString());
		
		
		Customer customer = new Customer();
		customer.setCustomerName(fieldSet.readString("firstName")+" "+fieldSet.readString("lastName"));
		customer.setGender(fieldSet.readString("gender"));
		customer.setEmail(fieldSet.readString("email"));
		customer.setPhone(fieldSet.readString("phone"));
		customer.setBranch(fieldSet.readString("branch"));
		
		Account account   = new Account();
		account.setAccountBalance(fieldSet.readDouble("accountBalance"));
		account.setAccountType(fieldSet.readString("accountType"));
		
		return new NewCustomerRequest(customer,account);
	}

}
