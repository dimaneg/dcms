package com.dimaneg.common.cdi;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dimaneg.common.api.Log;
import com.dimaneg.common.api.PerformanceLog;
import com.dimaneg.common.api.PerformanceLoggerImpl;
import com.dimaneg.common.api.PerformanceLogger;

public class LoggerProducer {

	@Produces
	@Log
	public Logger createLog(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

	@Produces
	@PerformanceLog
	public PerformanceLogger createPerformanceLog(InjectionPoint injectionPoint) {
		return new PerformanceLoggerImpl(
				LoggerFactory.getLogger("perf_" + injectionPoint.getMember().getDeclaringClass().getName()));
	}
}
