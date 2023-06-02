package com.bank.bpbm.utils;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.batch.core.SkipListener;

import com.bank.bpbm.entity.Transaction;

public class TransactionListener implements SkipListener<Transaction, Object>{
	
	private FileWriter fileWriter;
	
	public TransactionListener() {
		try {
			this.fileWriter = new FileWriter("G:\\My_Work_Space\\Bill-Pay-Batch-Modernization\\src\\main\\resources\\output.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSkipInRead(Throwable t) {
		//System.out.println("OnRead:: "+t.toString());
		try {
			fileWriter.write("OnRead:: "+t.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSkipInWrite(Object item, Throwable t) {
		//System.out.println("OnWrite:: "+t.toString() +"  ->"+item.toString());
		try {
			fileWriter.write("OnWrite:: "+t.toString() +"  ->"+item.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onSkipInProcess(Transaction item, Throwable t) {
		//System.out.println("OnProcess:: "+t.toString() +"  ->"+item.toString());
		try {
			fileWriter.write("OnProcess:: "+t.toString() +"  ->"+item.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
