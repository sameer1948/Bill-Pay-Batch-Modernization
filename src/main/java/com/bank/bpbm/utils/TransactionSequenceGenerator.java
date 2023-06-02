package com.bank.bpbm.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TransactionSequenceGenerator implements IdentifierGenerator{
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		String prefix = "TRANS";
		String suffix = "";
		String sql = "select count(*) from Transactions";
			
		try {
			Connection connection = session.connection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				int nextValue = resultSet.getInt(1);
				suffix = String.valueOf(nextValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return prefix+System.currentTimeMillis()+suffix;
		
	}
	
}