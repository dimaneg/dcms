package com.dimaneg.common.cdi;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dimaneg.common.api.Log;
import com.dimaneg.common.api.PerformanceLog;

public class LoggerProducer {

	@Produces
	@Log
	public Logger createLog(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
	
	@Produces
	@PerformanceLog
	public Logger createPerformanceLog(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger("perf_" + injectionPoint.getMember().getDeclaringClass().getName());
	}
}
