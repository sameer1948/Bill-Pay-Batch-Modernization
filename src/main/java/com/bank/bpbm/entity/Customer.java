package com.bank.bpbm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERS")
@SequenceGenerator(name="CUST_SEQ", sequenceName="CUST_SEQ", initialValue=11111, allocationSize=1)
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUST_SEQ")
	@Column(name="CUSTOMER_ID")	
	private Integer customerId;
	
	@Column(name="CUSTOMER_NAME")	
	private String customerName;
	
	@Column(name="GENDER")	
	private String gender;
	
	@Column(name="EMAIL")	
	private String email;
	
	@Column(name="PHONE")	
	private String phone;
	
	@Column(name="BRANCH")	
	private String branch;

	public Customer() {
		super();
	}
	
	public Customer(Integer customerId, String customerName, String gender, String email, String phone, String branch) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.branch = branch;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", gender=" + gender
				+ ", email=" + email + ", phone=" + phone + ", branch=" + branch + "]";
	}	

}
