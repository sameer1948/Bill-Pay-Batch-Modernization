package com.bank.bpbm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOB")
public class Job {
	
	@Id
	@Column(name="JOB_ID")
	private Integer jobId;
	
	@Column(name="T_TYPE")
	private String transactionType;
	
	@Column(name="JOB_START_TIME")
	private String jobStartTime;
	
	@Column(name="JOB_END_TIME")
	private String jobEndTime;
	
	@Column(name="JOB_STATUS")
	private String jobStatus;
	
	public Job() {
		super();
	
	}
	
	public Job(Integer jobId, String transactionType, String jobStartTime, String jobEndTime, String jobStatus) {
		super();
		this.jobId = jobId;
		this.transactionType = transactionType;
		this.jobStartTime = jobStartTime;
		this.jobEndTime = jobEndTime;
		this.jobStatus = jobStatus;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getJobStartTime() {
		return jobStartTime;
	}

	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public String getJobEndTime() {
		return jobEndTime;
	}

	public void setJobEndTime(String jobEndTime) {
		this.jobEndTime = jobEndTime;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", transactionType=" + transactionType + ", jobStartTime=" + jobStartTime
				+ ", jobEndTime=" + jobEndTime + ", jobStatus=" + jobStatus + "]";
	}
	
}
