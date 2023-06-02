package com.bank.bpbm.exceptions;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class TransactionSkipPolicy implements SkipPolicy{

	@Override
	public boolean shouldSkip(Throwable throwable, int skipCount) throws SkipLimitExceededException {
		
		return (throwable instanceof InvalidDetailsException) 	||
		       (throwable instanceof InValidAccountNoException)	||
		       (throwable instanceof InSufficientFundsException );
	}

}
