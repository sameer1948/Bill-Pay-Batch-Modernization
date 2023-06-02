package com.bank.bpbm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="STATUS")
public class Status {
	
	@Id
	@GenericGenerator(name = "stats_seq" , strategy = "com.bank.bpbm.utils.StatusSequenceGenerator")
	@GeneratedValue(generator = "stats_seq")
	@Column(name="S_NO")
	private String sNo;
	
	@Column(name="T_ID")
	private String transactionId;
	
	@Column(name="ACCOUNT_NO")
	private Integer accountNo;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="T_TYPE")
	private String transactionType;
	
	@Column(name="JOB_ID")
	private String jobId;

	public Status() {
		super();
	}
	
	public Status(String sNo, String transactionId, Integer accountNo, String status, String transactionType,
			String jobId) {
		super();
		this.sNo = sNo;
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.status = status;
		this.transactionType = transactionType;
		this.jobId = jobId;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@Override
	public String toString() {
		return "Status [sNo=" + sNo + ", transactionId=" + transactionId + ", accountNo=" + accountNo + ", status="
				+ status + ", transactionType=" + transactionType + ", jobId=" + jobId + "]";
	}

}
