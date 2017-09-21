package com.dimaneg.common.api;

import org.slf4j.Logger;

public interface PerformanceLogger extends Logger {

	public void opTime(String op, long time);
	
	public void opHit(String op);
	
	public void methodTime(long time);
}
