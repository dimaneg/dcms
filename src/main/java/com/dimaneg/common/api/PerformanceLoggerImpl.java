package com.dimaneg.common.api;

import org.slf4j.Logger;

public class PerformanceLoggerImpl extends MyLoggerWrapper implements PerformanceLogger {

	public PerformanceLoggerImpl(Logger delegate) {
		super(delegate);
	}

	@Override
	public void opTime(String op, long time) {
		super.debug("Operation " + op + " took " + time + " ms");
	}

	@Override
	public void opHit(String op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void methodTime(long time) {
		String methodName = "UNDEFINED";
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		if(stElements != null && stElements.length >= 3) {
			methodName = stElements[2].getClassName() + "." + stElements[2].getMethodName();
		}
		
		super.debug("Method " + methodName + " took " + time + " ms");
	}

}
